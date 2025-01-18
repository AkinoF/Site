import javax.swing.*;
import java.awt.*;

public class Window {
    JFrame frame;
    LoginPanel loginPanel;
    RegisterPanel registerPanel;

    public Window() {
        frame = new JFrame("Панель Входа и Регистрации");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void switchToRegister() {
        frame.getContentPane().removeAll();
        frame.add(registerPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void switchToLogin() {
        frame.getContentPane().removeAll();
        frame.add(loginPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);
    }
}