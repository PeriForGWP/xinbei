package com.czd.xinbei.entity;

public class PopEntity {
    private String content;
    private int show;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public PopEntity(String content, int show) {
        this.content = content;
        this.show = show;
    }
}
