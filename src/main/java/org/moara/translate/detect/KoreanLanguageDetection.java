package org.moara.translate.detect;
/**
 * <pre>
 *  파 일 명 : KoreanLanguageDetection.java
 *  설    명 : 한국어 감지
 *
 *  작 성 자 : macle(김용수)
 *  작 성 일 : 2019.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 ㈜모아라. All right reserved.
 */
public class KoreanLanguageDetection {

    /**
     * 한국어 여부
     * @param text 텍스트
     * @return 텍스트 한국어 여부
     */
    public static boolean isKorean(String text){

        int koreanCount = 0;

        int foreignCount = 0;

        String checkValue = text.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");

        char [] chars = checkValue.toCharArray();
        for(char ch : chars){

            if(ch ==' '){
                continue;
            }

            if(isNumber(ch)){
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

        return koreanCount > foreignCount;
    }


    /**
     * char가 숫자인지 체크
     * @param ch 체크할 케릭터
     * @return
     */
    public static boolean isNumber(char ch){
        if(ch > 57 || ch < 48)
            return false;

        return true;
    }

    public static void main(String[] args) {




    }

}
