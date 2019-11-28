package com.lab.edu.model;

/**
 * @author ruin
 * @date 2019/7/15-15:42
 */
public class Tag {
    private Integer tagId;
    private String tagName;
    private Integer tagNum;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagNum() {
        return tagNum;
    }

    public void setTagNum(Integer tagNum) {
        this.tagNum = tagNum;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagNum=" + tagNum +
                '}';
    }
}
