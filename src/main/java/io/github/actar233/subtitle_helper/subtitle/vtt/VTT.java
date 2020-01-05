package io.github.actar233.subtitle_helper.subtitle.vtt;

import io.github.actar233.subtitle_helper.exception.SubtitleParseException;
import io.github.actar233.subtitle_helper.subtitle.Subtitle;
import io.github.actar233.subtitle_helper.subtitle.SubtitleItem;
import io.github.actar233.subtitle_helper.subtitle.bcc.BCC;
import io.github.actar233.subtitle_helper.utils.ArrayIterator;
import io.github.actar233.subtitle_helper.utils.StringUtil;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//WEBVTT
//Kind: captions
//Language: zh-CN
//
//00:00:00.161 --> 00:00:02.660
//♪ (音乐) ♪
//
//00:00:07.141 --> 00:00:11.080
//现今的智能设备已经不能
//提供给应用程序规整的矩形界面来运行了

public class VTT extends Subtitle {

    private String kind;

    private String language;

    public String getKind() {
        return kind;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public void parse(String source) {
        if (source == null) throw new SubtitleParseException("source 不能为 null");
        String[] lines = StringUtil.splitMultipleLines(source);
        parseByLines(lines);
    }

    private void parseByLines(String[] lines) {
        ArrayIterator<String> iterator = new ArrayIterator<>(lines);
        if (!iterator.next().toUpperCase().equals("WEBVTT")) {
            throw new SubtitleParseException("VTT文件头不正确");
        }
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.equals("")) break;
            String[] split = line.split(": ");
            if (split.length == 2) {
                String key = split[0];
                String value = split[1];
                switch (key.toUpperCase()) {
                    case "KIND":
                        kind = value;
                        break;
                    case "LANGUAGE":
                        language = value;
                        break;
                    default:
                        System.out.println(String.format("waring: 未知元数据, %s", line));
                        break;
                }
            } else {
                throw new SubtitleParseException("VTT元数据不正确");
            }
        }
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.equals("")) break;
            String[] split = line.split("-->");
            if (split.length == 2) {
                double from = conversionTime(split[0].trim());
                double to = conversionTime(split[1].trim());
                StringBuilder builder = new StringBuilder();
                while (iterator.hasNext()) {
                    String l2 = iterator.next();
                    if (l2.equals("")) {
                        break;
                    }
                    if (builder.length() > 0) {
                        builder.append("\r\n");
                    }
                    builder.append(l2);
                }
                String content = builder.toString();
                add(new SubtitleItem(from, to, content));
            } else {
                throw new SubtitleParseException(String.format("解析数据失败,未知行: %s", line));
            }
        }
    }

    @Override
    public String stringify() {
        StringBuilder builder = new StringBuilder();
        builder.append("WEBVTT");
        builder.append("\r\n");
        if (kind != null) {
            builder.append(String.format("Kind: %s", kind));
            builder.append("\r\n");
        }
        if (language != null) {
            builder.append(String.format("Language: %s", language));
            builder.append("\r\n");
        }
        builder.append("\r\n");
        List<SubtitleItem> subtitles = getSubtitles();
        for (int i = 0; i < subtitles.size(); i++) {
            SubtitleItem item = subtitles.get(i);
            builder.append(unConversionTime(item.getFrom()));
            builder.append(" --> ");
            builder.append(unConversionTime(item.getTo()));
            builder.append("\r\n");
            builder.append(item.getContent());
            if (i < subtitles.size() - 1) {
                builder.append("\r\n");
                builder.append("\r\n");
            }
        }
        return builder.toString();
    }

    private final static Pattern conversionTimePattern = Pattern.compile("(\\d\\d):(\\d\\d):(\\d\\d\\.?\\d*)");

    private static double conversionTime(String time) {
        Matcher matcher = conversionTimePattern.matcher(time);
        if (!matcher.find()) {
            throw new SubtitleParseException(String.format("格式化时间失败: %s", time));
        }
        String s_h = matcher.group(1);
        String s_m = matcher.group(2);
        String s_s_ms = matcher.group(3);
        double t = 0;
        t += Long.parseLong(s_h) * 60 * 60;
        t += Long.parseLong(s_m) * 60;
        t += Double.parseDouble(s_s_ms);
        return t;
    }

    public static String unConversionTime(double time) {
        long h = (long) time / (60 * 60);
        long m = ((long) time - (h * 60 * 60)) / 60;
        double ss = BigDecimal.valueOf(time).subtract(BigDecimal.valueOf(h * 60 * 60 + m * 60)).doubleValue();
        StringBuilder builder = new StringBuilder();
        if (h < 10) {
            builder.append('0');
        }
        builder.append(h);
        builder.append(':');
        if (m < 10) {
            builder.append('0');
        }
        builder.append(m);
        builder.append(':');
        if (ss < 10) {
            builder.append('0');
        }
        builder.append(ss);
        return builder.toString();
    }

    public static VTT parseBySource(String source) {
        VTT vtt = new VTT();
        vtt.parse(source);
        return vtt;
    }

    public static VTT parseByFile(File file) throws IOException {
        VTT vtt = new VTT();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            list.add(line);
        }
        String[] lines = list.toArray(new String[]{});
        vtt.parseByLines(lines);
        return vtt;
    }

    @Override
    public VTT toVTT() {
        return this;
    }

    @Override
    public BCC toBCC() {
        BCC bcc = new BCC();
        bcc.setSubtitles(getSubtitles());
        return bcc;
    }
}
