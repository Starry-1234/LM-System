package com.management.controller;

import com.management.model.Announcement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncementController {
    private List<Announcement> announcementList;

    public AnnouncementController() {
        announcementList = new ArrayList<>();
        // 初始化一些测试数据
        announcementList.add(new Announcement(1, "系统升级通知", "系统将于2025年3月20日进行升级，请提前保存数据。", new Date()));
        announcementList.add(new Announcement(2, "新书到馆", "《Java编程思想》已到馆，欢迎借阅。", new Date()));
    }

    // 获取所有公告
    public List<Announcement> getAllAnnouncements() {
        return announcementList;
    }

    // 添加公告
    public void addAnnouncement(Announcement announcement) {
        announcement.setId(announcementList.size() + 1); // 自动生成ID
        announcementList.add(announcement);
    }

    // 根据ID查找公告
    public Announcement getAnnouncementById(int id) {
        for (Announcement announcement : announcementList) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }
}