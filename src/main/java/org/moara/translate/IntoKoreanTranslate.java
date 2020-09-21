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

import org.moara.translate.detect.KoreanLanguageDetection;

/**
 * 한국어로 번역
 * @author macle
 */
public class IntoKoreanTranslate {

    public static final String LANG_CODE ="ko";

    /**
     * 번역 결과 얻기
     * 한국어로 번역
     * format def html
     * @param text 텍스트
     * @return 번역 결과
     */
    public static TranslateResult translation(String text){
        return translation(text, "text");
    }

    /**
     * 번역 결과 얻기
     * 한국어로 번역
     *
     * Sets the format of the source text, in either HTML (default) or plain-text. A value of {@code
     * html} indicates HTML and a value of {@code text} indicates plain-text.
     * version 1.75
     *
     * @param text String 텍스트
     * @param format String (def html, text)
     * @return TranslateResult 번역 결과
     */
    public static TranslateResult translation(String text, String format){

        if(KoreanLanguageDetection.isKorean(text) || !GoogleTranslate.valid(text)){
            TranslateResult translateResult = new TranslateResult();
            translateResult.translate = text;
            translateResult.isTranslate = false;
            translateResult.langCodeDetection= LANG_CODE;
            translateResult.langCodeTranslate= LANG_CODE;
            return translateResult;
        }else{
            return GoogleTranslate.translation(text, LANG_CODE, format);
        }

    }


    public static void main(String[] args) {

//        TranslateTextRequest
        String tt = "서울에선 시그니엘이 맞습니다. ㅎㅎ\",\"가족 여행을 종종 다닙니다. \n" +
                "처와 딸이 호텔을 좋아해서요 ㅎㅎ\n" +
                "위치와 높이, 안락함과 안정성 저희 수준에 거의 모든 부분에 수준 높은 서비스를 받았습니다. 감사합니다. ㅎ";

//        String inputContents = "there is no virus";
//
//		String returns = GoogleTranslate.getTranslatedText("en", "ko", inputContents);
//		System.out.println(returns);

    }


}
