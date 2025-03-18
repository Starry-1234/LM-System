package com.management.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel sideBar;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        // 设置窗口属性
        setTitle("图书管理系统");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建内容区域（使用 CardLayout）
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // 添加页面到内容区域
        contentPanel.add(new BookManagementView(), "BookManagement"); // 书籍管理页面
        contentPanel.add(new BorrowRecordView(), "BorrowRecord"); // 借阅记录页面
        contentPanel.add(new UserManagementView(), "UserManagement"); // 用户管理页面
        contentPanel.add(new AdminManagementView(), "AdminManagement"); // 管理员管理页面
        contentPanel.add(new AnnouncementView(), "Announcement"); // 公告信息页面

        // 默认显示书籍管理页面
        cardLayout.show(contentPanel, "BookManagement");

        // 登录成功后显示侧边栏和内容区域
        showMainUI();
    }

    // 显示主界面（侧边栏 + 内容区域）
    public void showMainUI() {
        // 创建左侧边栏
        sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setBackground(new Color(240, 240, 240));
        sideBar.setPreferredSize(new Dimension(150, getHeight()));

        // 添加按钮到左侧边栏
        addSideBarButton("书籍管理", "BookManagement");
        addSideBarButton("借阅记录", "BorrowRecord");
        addSideBarButton("用户管理", "UserManagement");
        addSideBarButton("管理员管理", "AdminManagement");
        addSideBarButton("公告信息", "Announcement");

        // 添加组件到窗口
        add(sideBar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // 刷新界面
        revalidate();
        repaint();
    }

    // 添加侧边栏按钮
    private void addSideBarButton(String buttonText, String cardName) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(140, 40));
        button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, cardName); // 切换到对应的页面
            }
        });
        sideBar.add(button);
        sideBar.add(Box.createRigidArea(new Dimension(0, 10))); // 添加间距
    }

    // 切换页面
    public void showPage(String pageName) {
        cardLayout.show(contentPanel, pageName);
    }
}