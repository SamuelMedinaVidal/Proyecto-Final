package socialNetwork.UI;

import org.springframework.stereotype.Component;
import socialNetwork.repository.UsuarioRepository;

import javax.swing.*;

public class RegistroUI {
    private UsuarioRepository usuarioRepository;
    private JFrame registroFrame;

    public RegistroUI(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        initComponent();
    }

    public void initComponent() {
        registroFrame = new JFrame("Registro");
        registroFrame.setSize(400, 500);
        registroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        registroFrame.add(panel1);
        organizacion(panel1);
        registroFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        //Etiqueta label
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 10, 80, 25);
        panel1.add(nombreLabel);

        //Cuadro de texto
        JTextField nombreText = new JTextField(20);
        nombreText.setBounds(100, 10, 160, 25);
        panel1.add(nombreText);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosLabel.setBounds(10, 40, 80, 25);
        panel1.add(apellidosLabel);

        JTextField apellidosText = new JTextField(20);
        apellidosText.setBounds(100, 40, 160, 25);
        panel1.add(apellidosText);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 70, 80, 25);
        panel1.add(correoLabel);

        JTextField correoText = new JTextField(20);
        correoText.setBounds(100, 70, 160, 25);
        panel1.add(correoText);

        JLabel fechaDeNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaDeNacimientoLabel.setBounds(10, 100, 150, 25);
        panel1.add(fechaDeNacimientoLabel);

        JTextField fechaDeNacimientoText = new JTextField(20);
        fechaDeNacimientoText.setBounds(160, 100, 160, 25);
        panel1.add(fechaDeNacimientoText);

        JLabel nombreUsuarioLabel = new JLabel("Username:");
        nombreUsuarioLabel.setBounds(10, 130, 80, 25);
        panel1.add(nombreUsuarioLabel);

        JTextField nombreUsuarioText = new JTextField(20);
        nombreUsuarioText.setBounds(100, 130, 160, 25);
        panel1.add(nombreUsuarioText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 160, 80, 25);
        panel1.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 160, 160, 25);
        panel1.add(passwordText);
    }

    public void botones(JPanel panel1) {
        panel1.setLayout(null);
        JButton registarse = new JButton("Registrarse");
        registarse.setBounds(10, 90, 160, 25);
        panel1.add(registarse);

        JButton volver = new JButton("Volver");
        volver.setBounds(200, 90, 160, 25);
        panel1.add(volver);
    }
}
