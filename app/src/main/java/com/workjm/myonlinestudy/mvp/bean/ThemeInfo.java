package com.workjm.myonlinestudy.mvp.bean;

import java.util.ArrayList;

public class ThemeInfo {
    public static final int LOCAL_WALLPAPER_ID = -0x100;

    public static final String LOCAL_WALLPAPER_COVER_PIC_PATH = "local_wallpaper";

    public static final int DEFAULT_LAUNCHER_ID = -1;

    public int id;
    public int mediatype;
    public String name;
    public long size;
    public String author;
    public String createDate;
    public String note;
    public String corverPicPath;
    public ArrayList<String> picUrls = new ArrayList<String>();
    public int level;
    public int user_comment;
    public int newVersion;
    public int oldVersion;
    public String zipPath;
    public int localTheme;
    public int preview_state;
    public int used_state;
    public int scoreCount;
    public int downloadNum;
    public int launcher_id = DEFAULT_LAUNCHER_ID;
    public String defaultThemeName;


    public void setInfo(ThemeInfo info){
        this.author = info.author;
        this.corverPicPath = info.corverPicPath;
        this.createDate = info.createDate;
        this.downloadNum = info.downloadNum;
        this.id = info.id;
        this.level = info.level;
        this.localTheme = info.localTheme;
        this.mediatype = info.mediatype;
        this.name = info.name;
        this.newVersion = info.newVersion;
        this.note = info.note;
        this.oldVersion = info.oldVersion;
        this.preview_state = info.preview_state;
        this.scoreCount = info.scoreCount;
        this.size = info.size;
        this.used_state = info.used_state;
        this.user_comment = info.user_comment;
        this.zipPath = info.zipPath;
    }
}
