package com.lab.edu.model;

/**
 * @author ruin
 * @date 2019/7/11-17:25
 */
public class Admin {
    private Integer adID;
    private String adName;
    private String adAccount;
    private String adPassWord;

    public Integer getAdID() {
        return adID;
    }

    public void setAdID(Integer adID) {
        this.adID = adID;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdAccount() {
        return adAccount;
    }

    public void setAdAccount(String adAccount) {
        this.adAccount = adAccount;
    }

    public String getAdPassWord() {
        return adPassWord;
    }

    public void setAdPassWord(String adPassWord) {
        this.adPassWord = adPassWord;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adID=" + adID +
                ", adName='" + adName + '\'' +
                ", adAccount='" + adAccount + '\'' +
                ", adPassWord='" + adPassWord + '\'' +
                '}';
    }
}
