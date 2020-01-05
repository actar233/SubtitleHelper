package io.github.actar233.subtitle_helper.subtitle;

import java.util.Objects;

public class SubtitleItem {
    private double from;
    private double to;
    private String content;

    public SubtitleItem() {
    }

    public SubtitleItem(double from, double to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubtitleItem that = (SubtitleItem) o;
        return Double.compare(that.from, from) == 0 &&
                Double.compare(that.to, to) == 0 &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, content);
    }

    @Override
    public String toString() {
        return "SubtitleItem{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                '}';
    }
}
