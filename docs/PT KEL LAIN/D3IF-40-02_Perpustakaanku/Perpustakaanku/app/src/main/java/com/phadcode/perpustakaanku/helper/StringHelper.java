package com.phadcode.perpustakaanku.helper;

/**
 * Created by Hudio Hizari on 4/27/2018.
 */

public class StringHelper {

    public StringHelper() {
    }

    public String ucFirst(String word){
        word = word.toLowerCase();
        char[] chars = new char[1];
        word.getChars(0, 1, chars, 0);
        if (Character.isUpperCase(chars[0])) {
            return word;
        }
        else {
            StringBuilder buffer = new StringBuilder(word.length());
            buffer.append(Character.toUpperCase(chars[0]));
            buffer.append(word.toCharArray(), 1, word.length()-1);
            return buffer.toString();
        }
    }

    public String ucWord(String word){
        word = word.toLowerCase();
        StringBuffer stringBuffer = new StringBuffer();

        for (CharSequence charSequence: word.split(" ")) {
            stringBuffer.append(Character.toUpperCase(charSequence.charAt(0))).append(charSequence.subSequence(1, charSequence.length())).append(" ");
        }

        return stringBuffer.toString().trim();
    }
}
