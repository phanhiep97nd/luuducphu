/*
 * Copyright(C) 2007 Luvina Software Company
 *
 * $ID:Validator, Sep 11, 2007, hoangnnExp 
 */
package jp.co.tads.csms.util;

import java.util.regex.Pattern;

/**
 * データのチェック関数を保存するクラス
 * 
 * <p>
 * <a href="Validator.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author hoangnn <a href="mailto:hoangnn@luvina.net">&lt;hoangnn@luvina.net&gt;</a>
 * @version Revision: 1.0   
 */
public class Validator {
    
    /**
     * char型のデータをチェックする。
     * 
     * @param c チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isChar(char c) {
        return Character.isLetter(c);
    }

    /**
     * char型のデータをチェックする。
     * 
     * @param c チェックされるStringオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isChar(String s) {
        if (isNull(s)) {
            return false;
        }
    
        char[] c = s.toCharArray();
    
        for (int i = 0; i < c.length; i++) {
            if (!isChar(c[i])) {
            return false;
            }
        }
    
        return true;
    }

    /**
     * 数値型のデータをチェックする。
     * 
     * @param c チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isDigit(char c) {
        int x = (int) c;
    
        if ((x >= 48) && (x <= 57)) {
            return true;
        }
    
        return false;
    }

    /**
     * 数値型のデータをチェックする。
     * 
     * @param c チェックされるStringオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isDigit(String s) {
        if (isNull(s)) {
            return false;
        }
    
        char[] c = s.toCharArray();
    
        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i])) {
            return false;
            }
        }
    
        return true;
    }

    /**
     * Nullの値をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }

        s = s.trim();

        if ((s.equals(StringPool.NULL)) || (s.equals(StringPool.BLANK))) {
            return true;
        }

        return false;
    }

    /**
     * Not Nullの値をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */
    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    /**
     * カタカナをチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isKatakana(String text) {
        char[] c = text.toCharArray();
    
            for (int i = 0; i < c.length; i++) {
                if ((Character.UnicodeBlock.of(c[i]) != Character.UnicodeBlock.KATAKANA) && (!isDigit(c[i]))
                        && (!Character.isWhitespace(c[i]))) {
    
            return false;
            }
        }
        return true;
    }

    /**
     * 半角文字をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isHalfsize(String text) {
    	return Pattern.matches("[0-9]+", text);
    }
    
    /**
     * 英字をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isEnglish(String text) {
        //return Pattern.matches("[a-zA-Z0-9]+", text);
    	int count = 0;
        char[] c = text.toCharArray();
    
        for (int i = 0; i < c.length; i++) {
            try {
                count = (String.valueOf(c[i])).getBytes().length;
                
            } catch (Exception e) {
                count = 0;
            }
            if (!Character.isWhitespace(c[i]) && count >= 2) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 平仮名をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isHiragana(char c) {
        if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA)
            return true;
        //      hiragana
        if (c >= '\u3040' && c <= '\u309f')
            return true;
        //      KangXi (kanji)
        if (c >= '\u2f00' && c <= '\u2fdf')
            return true;
        return false;
    }

    /**
     * 平仮名をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isHiragana(String text) {
        char[] c = text.toCharArray();
    
        for (int i = 0; i < c.length; i++) {
            //             if ((Character.UnicodeBlock.of(c[i])!= Character.UnicodeBlock.HIRAGANA) &&
            if (!isHiragana(c[i]) && (!isDigit(c[i]))
                && (!Character.isWhitespace(c[i]))) {
    
            return false;
            }
        }
        return true;
    }

    /**
     * 2バイトの文字をチェックする。
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isTwoByteCharater(String text) {
        char[] c = text.toCharArray();
    
        for (int i = 0; i < c.length; i++) {
            if (!isJapanese(c[i]) &&
            /*(!isDigit(c[i])) &&*/
            (!Character.isWhitespace(c[i]))) {
    
            return false;
            }
        }
        return true;
    }

    /**
     * 日本語文字をチェック
     * 
     * @param s チェックされるオブジェクト
     * @return チェックが正常終了した場合、Trueを返す。
     */    
    public static boolean isJapanese(char c) {
        // katakana:
        if (c >= '\u30a0' && c <= '\u30ff')
            return true;
        if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA)
            return true;
        // hiragana
        if (c >= '\u3040' && c <= '\u309f')
            return true;
        if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA)
            return true;
        // CJK Unified Ideographs
        if (c >= '\u4e00' && c <= '\u9fff')
            return true;
        // CJK symbols & punctuation
        if (c >= '\u3000' && c <= '\u303f')
            return true;
        // KangXi (kanji)
        if (c >= '\u2f00' && c <= '\u2fdf')
            return true;
        // KanBun
        if (c >= '\u3190' && c <= '\u319f')
            return true;
        // CJK Unified Ideographs Extension A
        if (c >= '\u3400' && c <= '\u4db5')
            return true;
        // CJK Compatibility Forms
        if (c >= '\ufe30' && c <= '\ufe4f')
            return true;
        // CJK Compatibility
        if (c >= '\u3300' && c <= '\u33ff')
            return true;
        // CJK Radicals Supplement
        if (c >= '\u2e80' && c <= '\u2eff')
            return true;
        // other character..  return false;
        return false;
    }

    /**
     * 日本語文字をチェック
     * 
     * @param str　入力されたストリング
     * @return　ＴＲＵＥは成功の場合
     */
    public static boolean isJapanese(String str) {
        if (StringUtil.isNullString(str)) {
            return false;
        }
        for(int i =0; i < str.length(); i ++) {
            if (!isJapanese(str.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }

}
