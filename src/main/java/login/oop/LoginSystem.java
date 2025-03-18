package login.oop;

import javax.swing.*;
import java.awt.*;

public class LoginSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private ResetPasswordPanel resetPasswordPanel;

    public LoginSystem() {
        setTitle("图书管理系统");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        resetPasswordPanel = new ResetPasswordPanel(this);

        cardPanel.add(loginPanel, "login");
        cardPanel.add(registerPanel, "register");
        cardPanel.add(resetPasswordPanel, "resetPassword");

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "login");
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginSystem().setVisible(true);
            }
        });
    }
}
