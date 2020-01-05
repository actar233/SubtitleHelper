package io.github.actar233.subtitle_helper.subtitle;

import io.github.actar233.subtitle_helper.subtitle.bcc.BCC;
import io.github.actar233.subtitle_helper.subtitle.vtt.VTT;

import java.util.ArrayList;
import java.util.List;

public abstract class Subtitle {

    private List<SubtitleItem> subtitles;

    public Subtitle() {
        subtitles = new ArrayList<>();
    }

    public void setSubtitles(List<SubtitleItem> subtitles) {
        this.subtitles = subtitles;
    }

    public List<SubtitleItem> getSubtitles() {
        return new ArrayList<>(subtitles);
    }

    public void add(SubtitleItem subtitle) {
        subtitles.add(subtitle);
    }

    public void clear() {
        subtitles.clear();
    }

    /**
     * 解析
     */
    public abstract void parse(String source);

    /**
     * 生成文件数据
     *
     * @return 文件数据
     */
    public abstract String stringify();

    /**
     * 转换为VTT
     *
     * @return VTT
     */
    public abstract VTT toVTT();

    /**
     * 转换为BCC
     *
     * @return BCC
     */
    public abstract BCC toBCC();

}
