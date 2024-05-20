package socialNetwork.UI;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class NetworkUI extends JFrame {
    public NetworkUI() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,600);
    }
}
