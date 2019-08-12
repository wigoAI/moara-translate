package org.moara.translate;

/**
 * <pre>
 *  파 일 명 : TranslateResult.java
 *  설    명 : 번역 결과
 *
 *  작 성 자 : macle(김용수)
 *  작 성 일 : 2019.08
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2019 ㈜모아라. All right reserved.
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
     * @return 번역 여뷰
     */
    public boolean isTranslate() {
        return isTranslate;
    }

    /**
     * 감지 언어 코드 얻기
     * @return 감지 언어 코드
     */
    public String getLangCodeDetection() {
        return langCodeDetection;
    }

    /**
     * 번역 언어 코드 얻기
     * @return 번역 언어 코드
     */
    public String getLangCodeTranslate() {
        return langCodeTranslate;
    }

    /**
     * 번역 결과 얻기
     * @return 번역 결과
     */
    public String getTranslate() {
        return translate;
    }
}

