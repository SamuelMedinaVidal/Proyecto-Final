package socialNetwork.UI;

import org.springframework.stereotype.Component;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;
import socialNetwork.repository.UsuarioRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class LoginUI {
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;
    private JFrame loginFrame;
    public JTextField userText;
    private JPasswordField password;

    public LoginUI(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    //Inicializamos los componentes de la ventana
    public void initComponents() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(500, 230);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        loginFrame.add(panel1);
        organizacion(panel1);
        botones(panel1);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        panel1.add(userLabel);

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
        iniciarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        iniciarSesion.setBackground(new Color(70, 130, 180));
        iniciarSesion.setForeground(Color.WHITE);
        iniciarSesion.setFocusPainted(false);
        panel1.add(iniciarSesion);

        JButton registrar = new JButton("Registrarse");
        registrar.setBounds(200, 90, 160, 25);
        registrar.setFont(new Font("Arial", Font.BOLD, 14));
        registrar.setBackground(new Color(200, 0, 0));
        registrar.setForeground(Color.WHITE);
        registrar.setFocusPainted(false);
        panel1.add(registrar);

        JButton mostraContraena = new JButton("Mostrar contraseña");
        mostraContraena.setBounds(270, 40, 190, 25);
        panel1.add(mostraContraena);

        //Acción que muestra la contraseña a través de un mensaje
        mostraContraena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasena = new String(password.getPassword());
                JOptionPane.showMessageDialog(loginFrame, contrasena);
            }
        });

        //Acción para abirr la ventana de registro
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                RegistroUI registroUI = new RegistroUI(usuarioRepository, publicacionRepository);
                registroUI.initComponent();
            }
        });

        //Acción para iniciar sesión
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = userText.getText();
                String contrasena = new String(password.getPassword());

                //Busca el usuario en la base de datos
                Usuario usuario = usuarioRepository.findByUsernameAndContrasena(nombreUsuario, contrasena);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(loginFrame, "Usuario o contraseña incorrecta");
                } else {
                    loginFrame.dispose();
                    new MainUI(usuario, usuarioRepository, publicacionRepository);
                }
            }
        });
    }
}
