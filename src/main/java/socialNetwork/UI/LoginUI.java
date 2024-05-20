package socialNetwork.UI;

import jakarta.persistence.JoinColumn;
import org.springframework.stereotype.Component;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.UsuarioRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginUI {
    private UsuarioRepository usuarioRepository;
    private JFrame loginFrame;
    private JTextField userText;
    private JPasswordField password;

    public LoginUI(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        initComponents();
    }

    public void initComponents() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 230);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        loginFrame.add(panel1);
        organizacion(panel1);
        botones(panel1);
        loginFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        //Etiqueta label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        panel1.add(userLabel);

        //Cuadro de texto
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel1.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel1.add(passwordLabel);

        password = new JPasswordField(20);
        password.setBounds(100, 40, 160, 25);
        panel1.add(password);
    }

    public void botones(JPanel panel1) {
        panel1.setLayout(null);
        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setBounds(10, 90, 160, 25);
        panel1.add(iniciarSesion);

        JButton registrar = new JButton("Registrarse");
        registrar.setBounds(200, 90, 160, 25);
        panel1.add(registrar);

        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                new RegistroUI(usuarioRepository);
            }
        });

        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = userText.getText();
                String contrasena = new String(password.getPassword());

                Usuario usuario = usuarioRepository.findByUsernameAndContrasena(nombreUsuario,contrasena);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrecta");
                }
            }
        });

    }
}
