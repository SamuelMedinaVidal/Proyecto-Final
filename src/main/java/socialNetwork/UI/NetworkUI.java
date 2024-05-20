package socialNetwork.UI;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class NetworkUI{
    public NetworkUI() {
        JFrame login = new JFrame("Login");
        login.setSize(300,150);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel log = new JPanel();
        login.add(log);


        login.setVisible(true);
    }

    public static void organizacion(JPanel log) {
        log.setLayout(null);
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(100,20,20,20);
    }
}
