package com.management.view;

import javax.swing.*;
import java.awt.*;

public class UserManagementView extends JPanel {
    public UserManagementView() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("用户管理", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 这里可以添加用户管理的具体内容
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("这里是用户管理页面"));
        add(contentPanel, BorderLayout.CENTER);
    }
}