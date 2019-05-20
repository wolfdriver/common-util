package wolfdriver.util.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的正则验证方式
 * @Author: wolf_z
 * @Date: 2018/6/5 17:13
 */
public class RegexUtil {

    /**
     * 手机号码正则表达式
     */
    public static final String MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";


    /**
     * 身份证正则表达式
     */
    public static final String IDCARD = "\\d{17}[\\d|x]|\\d{15}";

    /**
     * 逗号分隔数字
     */
    public static final String COMMA_DIGITAL = "^[0-9,]+$";

    /**
     * 验证手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        return regular(mobile, MOBILE);
    }

    /**
     * 验证身份证号码
     *
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) {
        return regular(idCard.toLowerCase(), IDCARD);
    }


    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     *
     * @param str     匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static boolean regular(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 逗号分隔的数字
     *
     * @param str
     * @return
     */
    public static boolean checkCommaDigital(String str) {
        return regular(str, COMMA_DIGITAL);
    }


}
