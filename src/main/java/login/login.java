package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    public login() {
        // 设置窗口标题
        setTitle("学生管理系统");
        // 设置窗口大小
        setSize(300, 150);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为BorderLayout
        setLayout(new BorderLayout());

        // 初始化组件
        JLabel titleLabel = new JLabel("学生管理系统", JLabel.CENTER);
        JLabel usernameLabel = new JLabel("用户名：");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("密码：");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("登录");
        JButton resetButton = new JButton("重置");

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 添加组件到窗口
        add(titleLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        add(panel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 添加按钮事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名和密码
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 简单的登录验证
                if ("admin".equals(username) && "123".equals(password)) {
                    JOptionPane.showMessageDialog(login.this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(login.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 清空输入框
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        // 在事件分派线程中创建和显示GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
            }
        });
    }
}