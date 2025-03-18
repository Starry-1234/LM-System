package com.management.model;

import java.util.Date;

public class Announcement {
    private int id;
    private String title;
    private String content;
    private Date publishDate;

    // 构造方法
    public Announcement(int id, String title, String content, Date publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
}