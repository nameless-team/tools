package nameless.team.utils.string;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cujamin
 * @Date: 2019/3/20 14:43
 * @Description:
 */
public class StringUtil {
    private static final String openToken="${";
    private static final String closeToken ="}";

    /**
     * 使用Myabtis GenericTokenParser parse方法修改而成
     * @param text
     * @return
     */
    public static List<String> getParamList(String text){
        List<String> paramList = new LinkedList<>();

        if (text == null || text.isEmpty()) {
            return paramList;
        }
        char[] src = text.toCharArray();
        int offset = 0;
        // search open token
        int start = text.indexOf(openToken, offset);
        if (start == -1) {
            return paramList;
        }
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                // this open token is escaped. remove the backslash and continue.
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                // found open token. let's search close token.
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
                    expression.setLength(0);
                }
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        // this close token is escaped. remove the backslash and continue.
                        expression.append(src, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
                        expression.append(src, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }
                if (end == -1) {
                    // close token was not found.
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    paramList.add(expression.toString());
                    offset = end + closeToken.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }

        return paramList;
    }


    /**
     * 使用Myabtis GenericTokenParser parse方法修改而成
     * @param text
     * @param paramMap
     * @return
     */
    public static String replace(String text,Map<String,String> paramMap) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        char[] src = text.toCharArray();
        int offset = 0;
        // search open token
        int start = text.indexOf(openToken, offset);
        if (start == -1) {
            return text;
        }
//        结果String
        final StringBuilder builder = new StringBuilder();
//        需要替换的 old_String
        StringBuilder expression = null;
        while (start > -1) {
//            每次循环替换一个 String
            if (start > 0 && src[start - 1] == '\\') {
                // this open token is escaped. remove the backslash and continue.
//
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                // found open token. let's search close token.
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
//                    重置 expression
                    expression.setLength(0);
                }
//                将 offset 到 start 的字符串添加到 builder 中（即将不修改的部分添加到builder中）
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
//                重置需要替换String的end位置
                int end = text.indexOf(closeToken, offset);
//                查询需要替换的 old_String
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        // this close token is escaped. remove the backslash and continue.
//                        获取需要替换的old_String
                        expression.append(src, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
//                        获取需要替换的old_String
                        expression.append(src, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }

                if (end == -1) {
                    // close token was not found.
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
//                    添加需要替换的 new_String
                    builder.append(openToken);
                    builder.append(paramMap.get(expression.toString()));
                    builder.append(closeToken);
                    offset = end + closeToken.length();
                }
            }
//            重置需要替换String的start位置
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        return builder.toString();
    }
}
