package jp.co.spiralinks.horizon.utils;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 業務日付用ユーティリティ
 */


public class DateUtils  {

    public static LocalDate getBusinessDate(){
        DateUtilParts dateUtilParts = ApplicationContextProvider.getContext().getBean(DateUtilParts.class);
        return dateUtilParts.getBusinessDate();
    }

    /**
     * システム日時を返却します。
     * 業務日付とは異なります。ログ出力以外の使用厳禁！
     * @return
     */
    public static LocalDateTime getNowDateTime(){
        return LocalDateTime.now();
    }

    /**
     * 文字列の日付を、LocalDate型に変換します
     * @param date
     * @param formatter
     * @return
     */
    public static LocalDate convertStringToLocalDate(String date,DateTimeFormatter formatter) {

        return LocalDate.parse(date,formatter);

    }

}
