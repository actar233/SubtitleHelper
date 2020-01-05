package io.github.actar233.subtitle_helper.utils;

import java.util.Arrays;

class StringUtilTest {

    public static void main(String[] args) {
        StringUtilTest test = new StringUtilTest();
        test.splitMultipleLines();
    }

    void splitMultipleLines() {

        String source = "WEBVTT\n" +
                "Kind: captions\n" +
                "Language: zh-CN\n" +
                "\n" +
                "00:00:00.161 --> 00:00:02.660\n" +
                "♪ (音乐) ♪\n" +
                "\n" +
                "00:00:07.141 --> 00:00:11.080\n" +
                "现今的智能设备已经不能\n" +
                "提供给应用程序规整的矩形界面来运行了\n" +
                "\n" +
                "00:00:11.080 --> 00:00:13.750\n" +
                "消息提示栏总是不断闪出";

        String[] lines = StringUtil.splitMultipleLines("");
        System.out.println(lines.length);
        System.out.println(Arrays.toString(lines));
    }
}