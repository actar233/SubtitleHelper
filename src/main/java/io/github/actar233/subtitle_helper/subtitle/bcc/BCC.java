package io.github.actar233.subtitle_helper.subtitle.bcc;

import com.google.gson.Gson;
import io.github.actar233.subtitle_helper.subtitle.Subtitle;
import io.github.actar233.subtitle_helper.subtitle.SubtitleItem;
import io.github.actar233.subtitle_helper.subtitle.vtt.VTT;

import java.util.ArrayList;
import java.util.List;

public class BCC extends Subtitle {

    private double fontSize = 0.4;

    private String fontColor = "#FFFFFF";

    private double backgroundAlpha = 0.5;

    private String stroke = "none";

    @Override
    public void parse(String source) {
        Gson gson = new Gson();
        BCCModel model = gson.fromJson(source, BCCModel.class);
        if (model.getFontSize() != null) {
            this.fontSize = model.getFontSize();
        }
        if (model.getFontColor() != null) {
            this.fontColor = model.getFontColor();
        }
        if (model.getBackgroundAlpha() != null) {
            this.backgroundAlpha = model.getBackgroundAlpha();
        }
        if (model.getStroke() != null) {
            this.stroke = model.getStroke();
        }
        BCCModelBody[] body = model.getBody();
        for (BCCModelBody item : body) {
            SubtitleItem subtitleItem = new SubtitleItem(item.getFrom(), item.getTo(), item.getContent());
            add(subtitleItem);
        }
    }

    @Override
    public String stringify() {
        BCCModel model = new BCCModel();
        model.setFontSize(fontSize);
        model.setFontColor(fontColor);
        model.setBackgroundAlpha(backgroundAlpha);
        model.setStroke(stroke);
        ArrayList<BCCModelBody> list = new ArrayList<>();
        List<SubtitleItem> items = getSubtitles();
        for (SubtitleItem item : items) {
            BCCModelBody body = new BCCModelBody(item.getFrom(), item.getTo(), 2, item.getContent());
            list.add(body);
        }
        model.setBody(list.toArray(new BCCModelBody[]{}));
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    @Override
    public VTT toVTT() {
        VTT vtt = new VTT();
        vtt.setSubtitles(getSubtitles());
        return vtt;
    }

    @Override
    public BCC toBCC() {
        return this;
    }
}
