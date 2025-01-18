import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private JTextField loginField;
    private JPasswordField passwordField;
    private Window app;

    public LoginPanel(Window app) {
        this.app = app;

        setLayout(new GridLayout(3, 2));
        loginField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Войти");
        JButton registerButton = new JButton("Регистрация");

        add(new JLabel("Логин:"));
        add(loginField);
        add(new JLabel("Пароль:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateUser(login, password)) {
                    JOptionPane.showMessageDialog(app.frame, "Зря ты сюда полез.");
                    System.out.println("Добро пожаловать в Сити 17. Сами вы его выбрали, или его выбрали за вас — это лучший город из оставшихся. Я такого высокого мнения о Сити 17, что решил разместить свое правительство здесь, в Цитадели, столь заботливо предоставленной нашими Покровителями. Я горжусь тем, что называю Сити 17 своим домом."); // Вывод фразы в консоль
                } else {
                    JOptionPane.showMessageDialog(app.frame, "НЕПРАВИЛЬНО.");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchToRegister(); // Метод для переключения на страницу регистрации
            }
        });
    }

    private boolean authenticateUser(String login, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length == 2) {
                    String storedLogin = credentials[0];
                    String storedPassword = credentials[1];
                    if (storedLogin.equals(login) && storedPassword.equals(password)) {
                        return true; // Успешная аутентификация
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Успешная аутентификация не была достигнута
    }
}