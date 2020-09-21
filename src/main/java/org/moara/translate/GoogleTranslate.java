/*
 * Copyright (C) 2020 Wigo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.moara.translate;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.moara.translate.detect.KoreanLanguageDetection;

/**
 * 구글 번역을 이용한 텍스트 번역
 * @author macle
 */
@SuppressWarnings("unused")
public class GoogleTranslate {

    public static final String REMOVE_REGEX = "[!@#$%^&*+(),.\\-=?\":{}|<>\\d\\s]";
    public static final String GOOGLE_TRANSLATE_FORMAT_CODE = "text";

    /**
     * 번역 결과 얻기
     * format text fix  text=\n 인식
     * @param text String 텍스트
     * @param langCode String 언어코드
     * @return TranslateResult 번역결과
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
     * @param text String 텍스트
     * @param langCode String 언어코드
     * @param format String html, text
     * @return TranslateResult 번역결과
     */
    public static TranslateResult translation(String text, String langCode, String format){
        Translate translate = TranslateOptions.getDefaultInstance().getService();


        if(!valid(text)){
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

    /**
     *
     * @param detectCode String 감지 언어코드
     * @param langCode String 번역 언어코드
     * @param text String
     * @param format String
     * @return String
     */
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
     * @param text String
     * @return boolean 유효성
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean valid(String text){
        String checkValue = text.replaceAll(REMOVE_REGEX, "");

        char [] chars = checkValue.toCharArray();

        for(char ch : chars){
            if(ch != ' ' && !isNumber(ch)){
                return true;
            }
        }
        return false;
    }

    /**
     * char가 숫자인지 체크
     * @param ch char 체크할 케릭터
     * @return boolean isNumber
     */
    public static boolean isNumber(char ch){
        return ch <= 57 && ch >= 48;
    }


}
