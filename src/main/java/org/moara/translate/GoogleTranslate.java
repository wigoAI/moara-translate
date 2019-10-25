package org.moara.translate;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.moara.translate.detect.KoreanLanguageDetection;

/**
 * <pre>
 *  파 일 명 : GoogleTranslate.java
 *  설    명 : 구글 번역을 이용한 텍스트 번역
 *
 *  작 성 자 : macle(김용수)
 *  작 성 일 : 2019.08
 *  버    전 : 1.1
 *  수정이력 : 2019.10.25
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 ㈜모아라. All right reserved.
 */
public class GoogleTranslate {

    /**
     * 번역 결과 얻기
     * @param text 텍스트
     * @param langCode 언어코드
     * @return 번역결과
     */
    public static TranslateResult translation(String text, String langCode){
        Translate translate = TranslateOptions.getDefaultInstance().getService();


        if(!isValid(text)){
            TranslateResult translateResult = new TranslateResult();
            translateResult.translate = text;
            translateResult.isTranslate = false;
            translateResult.langCodeDetection= langCode;
            translateResult.langCodeTranslate= langCode;
            return translateResult;
        }

        String detectionLangCode;
        if(KoreanLanguageDetection.isKorean(text)){
            detectionLangCode = IntoKoreanTranslate.LANG_CODE;
        }else{
            Detection detection = translate.detect(text);
            detectionLangCode = detection.getLanguage();
        }




        if(langCode.equals(detectionLangCode)){
            TranslateResult translateResult = new TranslateResult();
            translateResult.translate = text;
            translateResult.isTranslate = false;
            translateResult.langCodeDetection= langCode;
            translateResult.langCodeTranslate= langCode;
            return translateResult;
        }
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(detectionLangCode),
                        Translate.TranslateOption.targetLanguage(langCode));

        TranslateResult translateResult = new TranslateResult();
        translateResult.translate = translation.getTranslatedText();
        translateResult.isTranslate = true;
        translateResult.langCodeDetection= detectionLangCode;
        translateResult.langCodeTranslate= langCode;

        return translateResult;
    }

    /**
     * 유효성 체크
     * @return 유효성
     */
    private static boolean isValid(String text){
        String checkValue = text.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");

        char [] chars = checkValue.toCharArray();

        for(char ch : chars){
            if(ch == ' '){
                continue;
            }

            if(isNumber(ch)){
                continue;
            }


            return true;

        }


        return false;

    }

    /**
     * char가 숫자인지 체크
     * @param ch 체크할 케릭터
     * @return isNumber
     */
    public static boolean isNumber(char ch){
        return ch <= 57 && ch >= 48;
    }

    public static void main(String... args) throws Exception {

        String text ="난 한국이 좋아";

        TranslateResult translateResult = GoogleTranslate.translation(text, "en");

        //번역여부
        if(translateResult.isTranslate()){
            //번역됨
            //한국어이면 번영되지않음
            System.out.println("번역됨");
        }else{
            System.out.println("번역되지않음");
        }

        System.out.println("감지언어코드: " + translateResult.getLangCodeDetection() +", 번역언어코드: " +translateResult.getLangCodeTranslate() +", 번역내용: " + translateResult.translate );
    }
}
