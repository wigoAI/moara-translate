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
package org.moara.translate.detect;

import org.moara.translate.GoogleTranslate;

/**
 * 한국어 감지
 * @author macle
 */
public class KoreanLanguageDetection {

    /**
     * 한국어 여부
     * @param text String 텍스트
     * @return boolean 텍스트 한국어 여부
     */
    public static boolean isKorean(String text){

        int koreanCount = 0;

        int foreignCount = 0;

        String checkValue = text.replaceAll(GoogleTranslate.REMOVE_REGEX, "");

        char [] chars = checkValue.toCharArray();
        for(char ch : chars){

            if(GoogleTranslate.isNumber(ch)){
                continue;
            }

            Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);

            if (Character.UnicodeBlock.HANGUL_SYLLABLES == block
                    || Character.UnicodeBlock.HANGUL_JAMO == block
                    || Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == block

            ) {
                koreanCount ++;
            }else{
                foreignCount++;
            }

        }

        return koreanCount >= foreignCount;
    }


}
