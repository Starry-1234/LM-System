package com.management.view;

import javax.swing.*;
import java.awt.*;

public class AdminManagementView extends JPanel {
    public AdminManagementView() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("管理员管理", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 这里可以添加管理员管理的具体内容
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("这里是管理员管理页面"));
        add(contentPanel, BorderLayout.CENTER);
    }
}