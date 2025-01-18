import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Window {
    JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    public Map<String, String> users = new HashMap<>();

    public Window() {
        frame = new JFrame("Авторизация");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Создаем панель авторизации
        JPanel loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "login");

        // Создаем панель регистрации
        JPanel registerPanel = new RegisterPanel(this);
        mainPanel.add(registerPanel, "register");

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
        cardLayout.show(mainPanel, "login");
    }

    public void addUser(String login, String password) {
        users.put(login, password);
        FileUtils.saveToFile(login, password);
    }

    public boolean authenticate(String login, String password) {
        return users.containsKey(login) && users.get(login).equals(password);
    }

    public void switchToRegister() {
        cardLayout.show(mainPanel, "register");
    }
    public void switchToLogin() {
        cardLayout.show(mainPanel, "login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}