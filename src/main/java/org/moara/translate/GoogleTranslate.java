package org.moara.translate;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
/**
 * <pre>
 *  파 일 명 : GoogleTranslate.java
 *  설    명 : 구글 번역을 이용한 텍스트 번역
 *
 *  작 성 자 : macle(김용수)
 *  작 성 일 : 2019.08
 *  버    전 : 1.0
 *  수정이력 :
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
        Detection detection = translate.detect(text);
        if(langCode.equals(detection.getLanguage())){
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
                        Translate.TranslateOption.sourceLanguage(detection.getLanguage()),
                        Translate.TranslateOption.targetLanguage(langCode));

        TranslateResult translateResult = new TranslateResult();
        translateResult.translate = translation.getTranslatedText();
        translateResult.isTranslate = true;
        translateResult.langCodeDetection= detection.getLanguage();
        translateResult.langCodeTranslate= langCode;

        return translateResult;
    }


    public static void main(String... args) throws Exception {

        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // The text to translate
        String text = "Hello, world!";

        Detection detection = translate.detect(text);
//        System.out.printf("Detection " + detection.getLanguage());
        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(detection.getLanguage()),
                        Translate.TranslateOption.targetLanguage("ko"));


        System.out.println("Text: " + text);
        System.out.println("Translation: " +  translation.getTranslatedText());
    }
}
