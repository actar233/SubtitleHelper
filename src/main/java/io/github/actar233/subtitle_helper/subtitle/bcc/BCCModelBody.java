package io.github.actar233.subtitle_helper.subtitle.bcc;

// {"from":0.15,"to":2.649,"location":2,"content":"<i>3</i>"}

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class BCCModelBody {
    @SerializedName("from")
    private Double from;
    @SerializedName("to")
    private Double to;
    @SerializedName("location")
    private Integer location = 2;
    @SerializedName("content")
    private String content;

    public BCCModelBody() {
    }

    public BCCModelBody(Double from, Double to, Integer location, String content) {
        this.from = from;
        this.to = to;
        this.location = location;
        this.content = content;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
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
        BCCModelBody that = (BCCModelBody) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(location, that.location) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, location, content);
    }

    @Override
    public String toString() {
        return "BCCModelBody{" +
                "from=" + from +
                ", to=" + to +
                ", location=" + location +
                ", content='" + content + '\'' +
                '}';
    }
}
