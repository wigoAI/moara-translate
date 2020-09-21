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

package org.moara.translate.example;

import org.moara.translate.IntoKoreanTranslate;
import org.moara.translate.TranslateResult;
import org.moara.translate.detect.KoreanLanguageDetection;

/**
 * @author macle
 */
public class IntoKoreanTranslateExample {

    public static void main(String[] args) {
        String text ="離地鐵站超級近而且一號出口有手扶梯\n" +
                "不管是要去機場還是要去各個景點都很方便\n" +
                "一進飯店就聞到舒服的香味\n" +
                "因為疫情關係\n" +
                "只要進飯店就會量體溫\n" +
                "飯店職員也很盡責把關\n" +
                "對面就是弘大商圈\n" +
                "要逛街買東西有相當方便\n" +
                "如果買東西太重也能先拿回飯店放\uD83D\uDE02\n" +
                "21樓就可換錢且匯率蠻好的\n" +
                "還有提供行李秤\n" +
                "房間裡的床蠻舒服的\n" +
                "空間雖然有點小但五臟具全\n" +
                "該有的都有\n" +
                "除了每天都有補礦泉水外\n" +
                "走廊也有飲水機";
//        System.out.println(Remove);
        TranslateResult translateResult = IntoKoreanTranslate.translation(text);
        System.out.println(KoreanLanguageDetection.isKorean(text) );
        //번역여부
        if(translateResult.isTranslate()){
            //번역됨
            //한국어이면 번영되지않음
            System.out.println("번역됨");
        }else{
            System.out.println("한국어");
        }

        System.out.println("감지언어코드: " + translateResult.getLangCodeDetection() +", 번역언어코드: " +translateResult.getLangCodeTranslate() +", 번역내용: " + translateResult.getTranslate() );
    }
}
