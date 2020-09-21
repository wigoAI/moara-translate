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


/**
 * 번역 결과
 * @author macle
 */
public class TranslateResult {
    /**
     * 번역여부
     */
    boolean isTranslate ;
    /**
     * 감지언어코드
     */
    String langCodeDetection;

    /**
     * 번역 언어 코드
     */
    String langCodeTranslate;

    /**
     * 번역결과
     */
    String translate;

    /**
     * 번역여부
     * 감지 언어코드와 번역언어 코드가 같으면 false 다르면 true
     * @return boolean 번역 여뷰
     */
    public boolean isTranslate() {
        return isTranslate;
    }

    /**
     * 감지 언어 코드 얻기
     * @return String 감지 언어 코드
     */
    public String getLangCodeDetection() {
        return langCodeDetection;
    }

    /**
     * 번역 언어 코드 얻기
     * @return String 번역 언어 코드
     */
    public String getLangCodeTranslate() {
        return langCodeTranslate;
    }

    /**
     * 번역 결과 얻기
     * @return String 번역 결과
     */
    public String getTranslate() {
        return translate;
    }
}

