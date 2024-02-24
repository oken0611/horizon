package jp.co.spiralinks.horizon.utils;

import org.apache.commons.lang3.StringUtils;

public class CoreUtils {

    /**
     * プログラム（クラス名.メソッド名）を取得する
     *
     * @return
     */
    public static String getProgramId(){

        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if( ste == null){
            return "unknown";
        }
        int idx = 0;
        for(StackTraceElement s : ste){
            if(StringUtils.indexOf(s.getClassName(),"Service") != -1){
                break;
            }
            idx++;
        }
        String className = ste[idx].getClassName();
        String methodName = ste[idx].getMethodName();

        String[] splitClassName = StringUtils.split(className, ".");
        if( splitClassName == null){
            return "unknown";
        }
        return StringUtils.join(splitClassName[splitClassName.length - 1],".",methodName);
    }
}
