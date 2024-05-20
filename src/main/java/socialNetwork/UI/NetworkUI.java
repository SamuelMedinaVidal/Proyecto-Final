package socialNetwork.UI;

import org.springframework.stereotype.Component;
import socialNetwork.repository.UsuarioRepository;

import javax.swing.*;

@Component
public class NetworkUI{
    private UsuarioRepository usuarioRepository;
    public NetworkUI(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        JFrame login = new JFrame("Login");
        login.setSize(400,300);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        login.add(panel1);
        organizacion(panel1);
        botones(panel1);
        login.setVisible(true);
    }

    public static void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        //Etiqueta label
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,10,80,25);
        panel1.add(userLabel);

        //Cuadro de texto
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel1.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,40,80,25);
        panel1.add(passwordLabel);

        JPasswordField password = new JPasswordField(20);
        password.setBounds(100,40,160,25);
        panel1.add(password);
    }

    public static void botones(JPanel panel1) {
        panel1.setLayout(null);
        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setBounds(10, 70, 160, 25);
        panel1.add(iniciarSesion);
        JButton registrar = new JButton("Regsitrarse");
        registrar.setBounds(200, 70, 160, 25);
        panel1.add(registrar);
    }
}
