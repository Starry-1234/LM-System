package login;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JTextField emailField;
    private JTextField resetUsernameField;
    private JTextField resetEmailField;

    public login() {
        // 设置窗口标题
        setTitle("图书管理系统");
        // 设置窗口大小
        setSize(400, 400);
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局为BorderLayout
        setLayout(new BorderLayout());
        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 初始化组件
        JLabel titleLabel = new JLabel("图书管理系统", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 5, 5));
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField = new JPasswordField(15);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton resetButton = new JButton("找回密码");
        resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);

        // 添加组件到窗口
        add(titleLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 添加按钮事件监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名和密码
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 数据库验证
                if (checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(login.this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(login.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 注册新用户
                registerUser();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 找回密码
                resetPassword();
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password");
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void registerUser() {
        // 注册新用户
        JDialog registerDialog = new JDialog(this, "注册新用户", true);
        registerDialog.setSize(300, 200);
        registerDialog.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel registerUsernameLabel = new JLabel("用户名：");
        registerUsernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerUsernameField = new JTextField(15);
        JLabel registerPasswordLabel = new JLabel("密码：");
        registerPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerPasswordField = new JPasswordField(15);
        JLabel emailLabel = new JLabel("邮箱：");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailField = new JTextField(15);

        registerDialog.add(registerUsernameLabel);
        registerDialog.add(registerUsernameField);
        registerDialog.add(registerPasswordLabel);
        registerDialog.add(registerPasswordField);
        registerDialog.add(emailLabel);
        registerDialog.add(emailField);

        JButton registerConfirmButton = new JButton("注册");
        registerConfirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 注册新用户到数据库
                String registerUsername = registerUsernameField.getText();
                String registerPassword = new String(registerPasswordField.getPassword());
                String email = emailField.getText();
                if (registerUsername.isEmpty() || registerPassword.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(registerDialog, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password");
                        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, registerUsername);
                        pstmt.setString(2, registerPassword);
                        pstmt.setString(3, email);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(registerDialog, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        registerDialog.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(registerDialog, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton registerCancelButton = new JButton("取消");
        registerCancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        registerCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerDialog.dispose();
            }
        });

        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.add(registerConfirmButton);
        registerButtonPanel.add(registerCancelButton);

        registerDialog.add(registerButtonPanel, BorderLayout.SOUTH);
        registerDialog.setVisible(true);
    }

    private void resetPassword() {
        // 找回密码
        JDialog resetDialog = new JDialog(this, "找回密码", true);
        resetDialog.setSize(300, 150);
        resetDialog.setLayout(new GridLayout(2, 2, 5, 5));

        JLabel resetUsernameLabel = new JLabel("用户名：");
        resetUsernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        resetUsernameField = new JTextField(15);
        JLabel resetEmailLabel = new JLabel("邮箱：");
        resetEmailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        resetEmailField = new JTextField(15);

        resetDialog.add(resetUsernameLabel);
        resetDialog.add(resetUsernameField);
        resetDialog.add(resetEmailLabel);
        resetDialog.add(resetEmailField);

        JButton resetConfirmButton = new JButton("重置");
        resetConfirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        resetConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 验证用户名和邮箱
                String resetUsername = resetUsernameField.getText();
                String resetEmail = resetEmailField.getText();
                if (resetUsername.isEmpty() || resetEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(resetDialog, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "password");
                        String sql = "SELECT * FROM users WHERE username = ? AND email = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, resetUsername);
                        pstmt.setString(2, resetEmail);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            // 这里可以发送邮件通知用户重置密码，这里仅做演示
                            JOptionPane.showMessageDialog(resetDialog, "重置密码成功，新密码已发送到您的邮箱", "提示", JOptionPane.INFORMATION_MESSAGE);
                            resetDialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(resetDialog, "用户名或邮箱错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(resetDialog, "重置失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton resetCancelButton = new JButton("取消");
        resetCancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        resetCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetDialog.dispose();
            }
        });

        JPanel resetButtonPanel = new JPanel();
        resetButtonPanel.add(resetConfirmButton);
        resetButtonPanel.add(resetCancelButton);

        resetDialog.add(resetButtonPanel, BorderLayout.SOUTH);
        resetDialog.setVisible(true);
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