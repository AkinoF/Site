# Site
Какой-то код на Java. Ничем не примечательный. Сделан при помощи интернет источников и не только.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterPanel extends JPanel {
private JTextField regLoginField;
private JPasswordField regPasswordField;
private Window app;

    public RegisterPanel(Window app) {
        this.app = app;
        setLayout(new GridLayout(3, 2));
        regLoginField = new JTextField();
        regPasswordField = new JPasswordField();
        JButton saveButton = new JButton("Сохранить");

        add(new JLabel("Логин:"));
        add(regLoginField);
        add(new JLabel("Пароль:"));
        add(regPasswordField);
        add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regLogin = regLoginField.getText();
                String regPassword = new String(regPasswordField.getPassword());
                if (!app.users.containsKey(regLogin)) {
                    app.addUser(regLogin, regPassword);
                    JOptionPane.showMessageDialog(app.frame, "Добро пожаловать в сити 17.");
                    app.switchToLogin(); // Переход к экрану авторизации после успешной регистрации
                } else {
                    JOptionPane.showMessageDialog(app.frame, "Будь оригинальным.");
                }
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class LoginPanel extends JPanel {
private JTextField loginField;
private JPasswordField passwordField;
private Window app;
private Preferences prefs;

    public LoginPanel(Window app) {
        this.app = app;
        prefs = Preferences.userNodeForPackage(LoginPanel.class);

        setLayout(new GridLayout(3, 2));
        loginField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Войти");
        JButton registerButton = new JButton("Регистрация");

        // Загрузка сохраненных логина и пароля
        String savedLogin = prefs.get("login", "");
        String savedPassword = prefs.get("password", "");
        loginField.setText(savedLogin);
        passwordField.setText(savedPassword);

        add(new JLabel("Login:"));
        add(loginField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                if (app.authenticate(login, password)) {
                    // Сохранение логина и пароля
                    prefs.put("login", login);
                    prefs.put("password", password);
                    JOptionPane.showMessageDialog(app.frame, "Молодец.");
                } else {
                    JOptionPane.showMessageDialog(app.frame, "Переписывай.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchToRegister();
            }
        });
    }
}