package com.management.view;

import javax.swing.*;
import java.awt.*;

public class AnnouncementView extends JPanel {
    public AnnouncementView() {
        setLayout(new BorderLayout());

        // 标题
        JLabel titleLabel = new JLabel("公告信息", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 公告内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 示例公告
        addAnnouncement(contentPanel, "2025-03-17", "系统升级通知", "系统将于2025年3月20日进行升级，请提前保存数据。");
        addAnnouncement(contentPanel, "2025-03-15", "新书到馆", "《Java编程思想》已到馆，欢迎借阅。");

        // 滚动面板
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    // 添加公告
    private void addAnnouncement(JPanel panel, String date, String title, String content) {
        JPanel announcementPanel = new JPanel();
        announcementPanel.setLayout(new BoxLayout(announcementPanel, BoxLayout.Y_AXIS));
        announcementPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        announcementPanel.setBackground(Color.WHITE);
        announcementPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // 日期
        JLabel dateLabel = new JLabel("日期: " + date);
        dateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        dateLabel.setForeground(Color.GRAY);

        // 标题
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

        // 内容
        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setBackground(Color.WHITE);

        // 添加到公告面板
        announcementPanel.add(dateLabel);
        announcementPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        announcementPanel.add(titleLabel);
        announcementPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        announcementPanel.add(contentArea);

        // 添加到内容面板
        panel.add(announcementPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
}