import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
                registerUser(regLogin, regPassword); // Вызов метода записи в файл
            }
        });
    }

    private void registerUser(String login, String password) {
        // Сначала, проверьте, существует ли уже логин
        if (userExists(login)) {
            JOptionPane.showMessageDialog(app.frame, "Уже есть такой, думай.");
            return;
        }

        // Если логин новый, запишите его в файл
        try (FileWriter fw = new FileWriter("user.txt", true)) {
            fw.write(login + ":" + password + "\n");
            fw.flush();
            JOptionPane.showMessageDialog(app.frame, "Добро пожаловать в сити 17.");
            app.switchToLogin(); // Переключение на экран логина после успешной регистрации
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean userExists(String login) {
        // Ребята, для простоты сначала предполагается, что никакой логики
        // для проверки существующих пользователей нет. Реализуйте эту функцию чтобы прочитать файл user.txt
        // и проверить, существует ли логин
        try (BufferedReader br = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length == 2 && credentials[0].equals(login)) {
                    return true; // Логин уже существует
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Логин не найден
    }
}