package com.management.view;

import javax.swing.*;
import java.awt.*;

public class BookManagementView extends JPanel {
    public BookManagementView() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("书籍管理", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 这里可以添加书籍管理的具体内容
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("这里是书籍管理页面"));
        add(contentPanel, BorderLayout.CENTER);
    }
}