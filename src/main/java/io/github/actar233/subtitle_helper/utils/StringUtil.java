package io.github.actar233.subtitle_helper.utils;

import java.util.ArrayList;

/**
 * String 工具类
 */
public class StringUtil {
    /**
     * 分割文本为多行
     *
     * @param source 输入
     * @return 输出
     */
    public static String[] splitMultipleLines(String source) {
        if (source.equals("")) return new String[]{""};
        ArrayList<String> list = new ArrayList<>();
        int length = source.length();
        StringBuilder builder = new StringBuilder();
        Character last = null;
        for (int i = 0; i < length; i++) {
            char c = source.charAt(i);
            switch (c) {
                case '\r':
                    list.add(builder.toString());
                    builder = new StringBuilder();
                    break;
                case '\n':
                    if (last != null && last == '\r') {
                        last = c;
                        continue;
                    } else {
                        list.add(builder.toString());
                        builder = new StringBuilder();
                    }
                    break;
                default:
                    builder.append(c);
                    break;
            }
            last = c;
        }
        if (last == null) return new String[]{""};
        String lastString = builder.toString();
        if (lastString.equals("")) {
            if (last == '\r' || last == '\n') {
                list.add("");
            }
        } else {
            list.add(lastString);
        }
        return list.toArray(new String[]{});
    }
}
