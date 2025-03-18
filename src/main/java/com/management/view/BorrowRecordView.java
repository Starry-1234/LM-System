package com.management.view;

import javax.swing.*;
import java.awt.*;

public class BorrowRecordView extends JPanel {
    public BorrowRecordView() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("借阅记录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 这里可以添加借阅记录的具体内容
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("这里是借阅记录页面"));
        add(contentPanel, BorderLayout.CENTER);
    }
}