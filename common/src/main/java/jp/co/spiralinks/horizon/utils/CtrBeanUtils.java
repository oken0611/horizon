package jp.co.spiralinks.horizon.utils;

import jp.co.spiralinks.horizon.exception.ApplicationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class CtrBeanUtils {

    private static final BeanUtilsBean LOCAL_BEAN_UTIL
            = new BeanUtilsBean(new ConvertUtilsBean(), BeanUtilsBean.getInstance().getPropertyUtils());

    static {
        LOCAL_BEAN_UTIL.getConvertUtils().register(new StringConverter(null), String.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new IntegerConverter(null), Integer.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new LongConverter(null), Long.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new DoubleConverter(null), Double.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new BooleanConverter(null), Boolean.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new LocalDateConverter(null), LocalDate.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new LocalDateTimeConverter(null), LocalDateTime.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new LocalTimeConverter(null), LocalTime.class);
        LOCAL_BEAN_UTIL.getConvertUtils().register(new BigDecimalConverter(null), BigDecimal.class);
    }

    /**
     * apache.commonsの
     * BeanUtils#copyProperties} を検査例外を出さず実行時例外にして、引数の順序を src, dest にしたもの.
     *
     * @param from  コピー元
     * @param to コピー先
     */
    public static void copyProps(Object from, Object to) {

        try {
            LOCAL_BEAN_UTIL.copyProperties(to, from);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException(e.getMessage(), e);
        }

    }

    /**
     * apache.commonsの
     * BeanUtils#describe} を検査例外を出さず実行時例外にしたもの.
     *
     * @param src
     * @return
     */
    public static Map<String, String> describe(Object src) {

        try {
            Map<String, String> describe = LOCAL_BEAN_UTIL.describe(src);
            return describe;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ApplicationException(e.getMessage(), e);
        }

    }

    /**
     * apache.commonsの
     * BeanUtils#setProperty} を検査例外を出さず実行時例外にしたもの.
     *
     * @param obj
     * @param name
     * @param value
     */
    public static void setProperty(Object obj, String name, Object value) {

        try {
            LOCAL_BEAN_UTIL.setProperty(obj, name, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException(e.getMessage(), e);
        }

    }

    /**
     * apache.commonsの
     * BeanUtils#getProperty} を検査例外を出さず実行時例外にしたもの.
     *
     * @param obj
     * @param name
     * @return
     */
    public static String getProperty(Object obj, String name) {

        try {
            return LOCAL_BEAN_UTIL.getProperty(obj, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ApplicationException(e.getMessage(), e);
        }

    }
}