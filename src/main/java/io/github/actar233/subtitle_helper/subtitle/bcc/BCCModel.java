package io.github.actar233.subtitle_helper.subtitle.bcc;

//{"font_size":0.4,
// "font_color":"#FFFFFF",
// "background_alpha":0.5,"
// background_color":"#9C27B0",
// "Stroke":"none",
// "body":[{"from":0.15,"to":2.649,"location":2,"content":"<i>3</i>"}]}

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class BCCModel {
    @SerializedName("font_size")
    private Double fontSize;
    @SerializedName("font_color")
    private String fontColor = "#FFFFFF";
    @SerializedName("background_alpha")
    private Double backgroundAlpha;
    @SerializedName("Stroke")
    private String stroke;
    @SerializedName("body")
    private BCCModelBody[] body;

    public BCCModel() {
    }

    public BCCModel(Double fontSize, String fontColor, Double backgroundAlpha, String stroke, BCCModelBody[] body) {
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.backgroundAlpha = backgroundAlpha;
        this.stroke = stroke;
        this.body = body;
    }

    public Double getFontSize() {
        return fontSize;
    }

    public void setFontSize(Double fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public Double getBackgroundAlpha() {
        return backgroundAlpha;
    }

    public void setBackgroundAlpha(Double backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public BCCModelBody[] getBody() {
        return body;
    }

    public void setBody(BCCModelBody[] body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BCCModel bccModel = (BCCModel) o;
        return Objects.equals(fontSize, bccModel.fontSize) &&
                Objects.equals(fontColor, bccModel.fontColor) &&
                Objects.equals(backgroundAlpha, bccModel.backgroundAlpha) &&
                Objects.equals(stroke, bccModel.stroke) &&
                Arrays.equals(body, bccModel.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fontSize, fontColor, backgroundAlpha, stroke);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "BCCModel{" +
                "fontSize=" + fontSize +
                ", fontColor='" + fontColor + '\'' +
                ", backgroundAlpha=" + backgroundAlpha +
                ", stroke='" + stroke + '\'' +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
