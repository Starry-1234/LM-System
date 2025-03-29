package com.management.view;

import com.management.controller.AnnouncementController;
import com.management.view.AnnouncementView;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnnouncementView extends JPanel {
    private AnnouncementController announcementController;

    public AnnouncementView() {
        announcementController = new AnnouncementController(); // 创建 AnnouncementController 实例
        setLayout(new BorderLayout());

        // 标题
        JLabel titleLabel = new JLabel("公告信息", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 获取所有公告
        List<com.management.model.Announcement> announcements = announcementController.getAllAnnouncements();

        // 表格模型
        String[] columnNames = {"ID", "标题", "内容", "发布日期"};
        Object[][] data = new Object[announcements.size()][4];
        for (int i = 0; i < announcements.size(); i++) {
            com.management.model.Announcement announcement = announcements.get(i);
            data[i][0] = announcement.getId();
            data[i][1] = announcement.getTitle();
            data[i][2] = announcement.getContent();
            data[i][3] = announcement.getPublishDate();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}