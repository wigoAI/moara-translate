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

    public static final String REMOVE_REGEX = "[!@#$%^&*+(),.\\-=?\":{}|<>\\d\\s]";
    public static final String GOOGLE_TRANSLATE_FORMAT_CODE = "text";

    /**
     * 번역 결과 얻기
     * format text fix  text=\n 인식
     * @param text 텍스트
     * @param langCode 언어코드
     * @return 번역결과
     */
    public static TranslateResult translation(String text, String langCode){
        return translation(text, langCode, GOOGLE_TRANSLATE_FORMAT_CODE);
    }

    /**
     * 번역 결과 얻기
     *
     * Sets the format of the source text, in either HTML (default) or plain-text. A value of {@code
     * html} indicates HTML and a value of {@code text} indicates plain-text.
     * version 1.75
     *
     * @param text 텍스트
     * @param langCode 언어코드
     * @param format html, text
     * @return 번역결과
     */
    public static TranslateResult translation(String text, String langCode, String format){
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
                        Translate.TranslateOption.targetLanguage(langCode),
                        Translate.TranslateOption.format(format)
                );

        TranslateResult translateResult = new TranslateResult();
        translateResult.translate = translation.getTranslatedText();
        translateResult.isTranslate = true;
        translateResult.langCodeDetection= detectionLangCode;
        translateResult.langCodeTranslate= langCode;

        return translateResult;
    }

    public static String getTranslatedText(String detectCode, String langCode, String text){
        return getTranslatedText(detectCode, langCode, text, GOOGLE_TRANSLATE_FORMAT_CODE);
    }

    public static String getTranslatedText(String detectCode, String langCode, String text, String format){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(detectCode),
                        Translate.TranslateOption.targetLanguage(langCode),
                        Translate.TranslateOption.format(format)
                );

        return translation.getTranslatedText();
    }

    /**
     * 유효성 체크
     * @return 유효성
     */
    static boolean isValid(String text){
        String checkValue = text.replaceAll(REMOVE_REGEX, "");

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

        System.out.println(isValid("    5+=------"));
    }
}
