package login.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private LoginSystem loginSystem;

    public RegisterPanel(LoginSystem loginSystem) {
        this.loginSystem = loginSystem;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("注册新用户", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);

        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, usernameField.getFontMetrics(usernameField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 0;
        registerPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, passwordField.getFontMetrics(passwordField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        registerPanel.add(passwordField, gbc);

        JLabel emailLabel = new JLabel("邮箱：");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(emailField.getPreferredSize().width, emailField.getFontMetrics(emailField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 2;
        registerPanel.add(emailField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton backButton = new JButton("返回");
        backButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        add(titleLabel, BorderLayout.NORTH);
        add(registerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
                        String sql = "INSERT INTO login (用户名, 密码, 邮箱) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.setString(3, email);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(RegisterPanel.this, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        loginSystem.showPanel("login");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(RegisterPanel.this, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginSystem.showPanel("login");
            }
        });
    }
}
