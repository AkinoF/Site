import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private JTextField loginField;
    private JPasswordField passwordField;
    private Window app;

    public RegisterPanel(Window app) {
        this.app = app;

        setLayout(new GridLayout(3, 2));
        loginField = new JTextField();
        passwordField = new JPasswordField();
        JButton registerButton = new JButton("Регистрация");
        JButton backButton = new JButton("Назад");

        add(new JLabel("Логин:"));
        add(loginField);
        add(new JLabel("Пароль:"));
        add(passwordField);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика регистрации здесь
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                FileUtils.saveToFile(login, password);
                JOptionPane.showMessageDialog(app.frame, "Регистрация успешна!");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchToLogin(); // Метод для переключения на страницу входа
            }
        });
    }
}