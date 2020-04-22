package org.moara.translate;

import org.moara.translate.detect.KoreanLanguageDetection;

/**
 * <pre>
 *  파 일 명 : IntoKoreanTranslate.java
 *  설    명 : 한국어로 번역하기
 *
 *  작 성 자 : macle(김용수)
 *  작 성 일 : 2019.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 ㈜모아라. All right reserved.
 */
public class IntoKoreanTranslate {

    public static final String LANG_CODE ="ko";

    /**
     * 번역 결과 얻기
     * 한국어로 번역
     * @param text 텍스트
     * @return 번역 결과
     */
    public static TranslateResult translation(String text){

        if(KoreanLanguageDetection.isKorean(text) || !GoogleTranslate.isValid(text)){
            TranslateResult translateResult = new TranslateResult();
            translateResult.translate = text;
            translateResult.isTranslate = false;
            translateResult.langCodeDetection= LANG_CODE;
            translateResult.langCodeTranslate= LANG_CODE;
            return translateResult;
        }else{
            return GoogleTranslate.translation(text, LANG_CODE);
        }

    }


    public static void main(String[] args) {


        String text ="Отель очень современный, отличные номера, шикарные завтраки. Всё время в нём останавливаюсь. Но персонал в состоянии испортить настроение даже в таком отеле. При заказе ужина в номер соединение идёт не с «румсервисом», а с ресепшн. Заказ принимает работник, принимающий гостей... а потом передаёт его на кухню.Я больше часа ждал своего заказа, оказалось, что цепочка не сработала... Пришлось ложиться спать голодным.Кстати, никто из персонала извинений не принёс...";
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

        System.out.println("감지언어코드: " + translateResult.getLangCodeDetection() +", 번역언어코드: " +translateResult.getLangCodeTranslate() +", 번역내용: " + translateResult.translate );
        String inputContents = "there is no virus";
		
		String returns = GoogleTranslate.getTranslatedText("en", "ko", inputContents);
		System.out.println(returns);

    }


}
