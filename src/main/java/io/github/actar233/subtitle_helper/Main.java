package io.github.actar233.subtitle_helper;

import io.github.actar233.subtitle_helper.subtitle.vtt.VTT;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        File root = new File("D:\\字幕");
        File[] files = root.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            File nFile = new File(root, getName(file.getName()) + ".bcc");
            VTT vtt = VTT.parseByFile(file);
            String result = vtt.toBCC().stringify();
            writeFile(nFile, result);
        }
    }

    public static String getName(String name) {
        int i = name.lastIndexOf(".");
        if (i == -1) return name;
        return name.substring(0, i);
    }

    public static void writeFile(File file, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        writer.write(text);
        writer.flush();
        writer.close();
    }

}
