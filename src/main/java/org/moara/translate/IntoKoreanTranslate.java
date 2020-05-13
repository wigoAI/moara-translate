package org.moara.translate;

import com.google.api.services.translate.model.TranslateTextRequest;
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
     * @param text 텍스트
     * @param format (def html, text)
     * @return 번역 결과
     */
    public static TranslateResult translation(String text, String format){

        if(KoreanLanguageDetection.isKorean(text) || !GoogleTranslate.isValid(text)){
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

        System.out.println("감지언어코드: " + translateResult.getLangCodeDetection() +", 번역언어코드: " +translateResult.getLangCodeTranslate() +", 번역내용: " + translateResult.translate );
//        String inputContents = "there is no virus";
//
//		String returns = GoogleTranslate.getTranslatedText("en", "ko", inputContents);
//		System.out.println(returns);

    }


}
