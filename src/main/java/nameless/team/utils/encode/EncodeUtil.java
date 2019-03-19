package nameless.team.utils.encode;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: cujamin
 * @Date: 2019/3/5 17:43
 * @Description: ANSI、GBK、GB2312、UTF-8、GB18030和 UNICODE
 */
public class EncodeUtil {

    public static final String ASCII = "ASCII";

    public static final String UNICODE = "UNICODE";

    public static final String GB18030 = "GB18030";

    public static final String GB2312 = "GB2312";

//    public static final String ANSI="ANSI";
    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
    public static final String US_ASCII = "US-ASCII";

    /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /** 8 位 UCS 转换格式 */
    public static final String UTF_8 = "UTF-8";

    /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
    public static final String UTF_16BE = "UTF-16BE";

    /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
    public static final String UTF_16LE = "UTF-16LE";

    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
    public static final String UTF_16 = "UTF-16";

    /** 中文超大字符集 */
    public static final String GBK = "GBK";

    public static final String[] Charsets = {ASCII,UNICODE,GB18030,GB2312,US_ASCII,ISO_8859_1,UTF_8,UTF_16BE,UTF_16LE,UTF_16,GBK};

    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        String newStr = null;
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            newStr = new String(bs, newCharset);
        }
        return newStr;
    }


    public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        String newStr = null;
        if (str != null) {
            //用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            //用新的字符编码生成字符串
            newStr = new String(bs, newCharset);
        }
        return newStr;
    }

}
