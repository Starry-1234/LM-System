package com.management.view;

import com.management.view.LoginFrame;
import com.management.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginFrame loginFrame;

    public LoginPanel(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("图书管理系统", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, usernameField.getFontMetrics(usernameField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, passwordField.getFontMetrics(passwordField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton resetButton = new JButton("找回密码");
        resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);

        add(titleLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                    // 获取数据库连接并传递给MainFrame
                    Connection conn = getConnection();
                    if (conn != null) {
                        loginFrame.dispose(); // 关闭登录窗口
                        MainFrame mainFrame = new MainFrame(conn); // 使用带参构造函数
                        mainFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(LoginPanel.this, "无法获取数据库连接", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.showPanel("register");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.showPanel("resetPassword");
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        try (Connection conn = getConnection()) {
            if (conn == null) return false;

            String sql = "SELECT * FROM login WHERE 用户名 = ? AND 密码 = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Connection getConnection() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/db.properties")) {
            props.load(fis);
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            // 处理 IO 异常
            System.err.println("数据库配置文件读取失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // 处理其他异常（如 SQLException）
            System.err.println("数据库连接失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
