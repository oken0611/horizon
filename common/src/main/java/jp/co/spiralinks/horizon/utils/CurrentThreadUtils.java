package jp.co.spiralinks.horizon.utils;

import org.apache.commons.lang3.StringUtils;

public class CurrentThreadUtils {

    /**
     * このメソッドを呼び出したメソッド名、ファイル名、行番号の情報を取得します。
     * 出力フォーマットは下記です。
     * "メソッド名(ファイル名:行番号)"
     *
     * @return メソッド名、ファイル名、行番号の情報文字列
     */
    public static String calledAt() {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        StringBuilder sb = new StringBuilder();
        sb.append(ste.getMethodName())      // メソッド名取得
                .append("(")
                .append(ste.getFileName())      // ファイル名取得
                .append(":")
                .append(ste.getLineNumber())    // 行番号取得
                .append(")");
        return sb.toString();
    }

    /**
     * このメソッドを呼び出したメソッドの呼び出し元のメソッド名、ファイル名、行番号の情報を取得します。
     * 出力フォーマットは下記です。該当するメソッドがない場合は空文字を返します。
     * "メソッド名(ファイル名:行番号)"
     *
     * @return メソッド名、ファイル名、行番号の情報文字列
     */
    public static String calledFrom() {
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        if (steArray.length <= 3) {
            return "";
        }
        StackTraceElement ste = steArray[3];
        StringBuilder sb = new StringBuilder();
        sb.append(ste.getMethodName())      // メソッド名取得
                .append("(")
                .append(ste.getFileName())      // ファイル名取得
                .append(":")
                .append(ste.getLineNumber())    // 行番号取得
                .append(")");
        return sb.toString();
    }

    /**
     * このメソッドを呼び出したメソッドの呼び出し元(Serviceクラス)のメソッド名、ファイル名、行番号の情報を取得します。
     * 出力フォーマットは下記です。該当するメソッドがない場合は[nothing]を返します。
     * "メソッド名(ファイル名:行番号)"
     *
     * @return メソッド名、ファイル名、行番号の情報文字列
     */
    public static String calledService() {
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        int idx = 0;
        for(StackTraceElement s : steArray){
            if(StringUtils.contains(s.getFileName(),"Service")){
                break;
            }
            idx++;
        }
        if (idx == 0 || idx == steArray.length) {
            return "nothing";
        }
        StackTraceElement ste = steArray[idx];
        StringBuilder sb = new StringBuilder();
        sb.append(ste.getMethodName())      // メソッド名取得
                .append("(")
                .append(ste.getFileName())      // ファイル名取得
                .append(":")
                .append(ste.getLineNumber())    // 行番号取得
                .append(")");
        return sb.toString();
    }
}
