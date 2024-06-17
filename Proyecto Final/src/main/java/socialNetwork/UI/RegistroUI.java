package socialNetwork.UI;

import org.springframework.stereotype.Component;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;
import socialNetwork.repository.UsuarioRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RegistroUI {
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;
    private JFrame registroFrame;
    private JButton volver;
    private JButton registrar;
    private JButton generarImagen;
    private JLabel imagenLabel;
    private JTextField nombreText;
    private JTextField apellidosText;
    private JTextField correoText;
    private JTextField fechaDeNacimientoText;
    private JTextField nombreUsuarioText;
    private JPasswordField passwordText;
    private String imagenPerfilUrl; //Esto sirve para extraer la URL de la imagen de perfil generada

    public RegistroUI(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    //Inicializamos los componentes de la ventana
    public void initComponent() {
        registroFrame = new JFrame("Registro");
        registroFrame.setSize(700, 700);
        registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        registroFrame.add(panel1);
        organizacion(panel1);
        registroFrame.setLocationRelativeTo(null);
        registroFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        // Todas las celdas de datos e imagen.
        panel1.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 10, 80, 25);
        panel1.add(nombreLabel);

        nombreText = new JTextField(20);
        nombreText.setBounds(100, 10, 160, 25);
        panel1.add(nombreText);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosLabel.setBounds(10, 40, 80, 25);
        panel1.add(apellidosLabel);

        apellidosText = new JTextField(20);
        apellidosText.setBounds(100, 40, 160, 25);
        panel1.add(apellidosText);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 70, 80, 25);
        panel1.add(correoLabel);

        correoText = new JTextField(20);
        correoText.setBounds(100, 70, 160, 25);
        panel1.add(correoText);

        JLabel fechaDeNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaDeNacimientoLabel.setBounds(10, 100, 150, 25);
        panel1.add(fechaDeNacimientoLabel);

        fechaDeNacimientoText = new JTextField(20);
        fechaDeNacimientoText.setBounds(160, 100, 160, 25);
        panel1.add(fechaDeNacimientoText);

        JLabel nombreUsuarioLabel = new JLabel("Username:");
        nombreUsuarioLabel.setBounds(10, 130, 80, 25);
        panel1.add(nombreUsuarioLabel);

        nombreUsuarioText = new JTextField(20);
        nombreUsuarioText.setBounds(100, 130, 160, 25);
        panel1.add(nombreUsuarioText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 160, 80, 25);
        panel1.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 160, 160, 25);
        panel1.add(passwordText);
        
        // Botón para mostrar la contraseña
        JButton mostrarContrasena = new JButton("Mostrar contraseña");
        mostrarContrasena.setBounds(270, 160, 160, 25);
        panel1.add(mostrarContrasena);

        // Acción para mostrar la contraseña
        mostrarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasena = new String(passwordText.getPassword());
                JOptionPane.showMessageDialog(registroFrame, contrasena);
            }
        });
        
        // Componentes para la imagen de perfil
        imagenLabel = new JLabel();
        imagenLabel.setBounds(100, 200, 300, 300);
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.add(imagenLabel);

        generarImagen = new JButton("Generar imagen");
        generarImagen.setBounds(100, 510, 160, 25);
        panel1.add(generarImagen);

        // Conexión con la API para generar una imagen de perfil aleatoria
        generarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("https://picsum.photos/300/300");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(false);
                    connection.connect();
                    // Obtiene la url final de la imagen (evitamos que muestre sólo imágenes aleatorias) y la asigna a la variable imagenPerfilUrl
                    String finalImageUrl = connection.getHeaderField("Location");
                    imagenPerfilUrl = finalImageUrl != null ? finalImageUrl : "https://picsum.photos/300/300";

                    Image image = ImageIO.read(new URL(imagenPerfilUrl));
                    ImageIcon icon = new ImageIcon(image);
                    imagenLabel.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        botones(panel1);
    }

    public void botones(JPanel panel1) {
        volver = new JButton("Login");
        volver.setBounds(10, 550, 160, 25);
        panel1.add(volver);

        registrar = new JButton("Registrarse");
        registrar.setBounds(200, 550, 160, 25);
        panel1.add(registrar);

        //Acción para volver a la ventana de inicio de sesión
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroFrame.dispose();
                LoginUI loginUI = new LoginUI(usuarioRepository, publicacionRepository);
                loginUI.initComponents();
            }
        });

        //Acción para registrar un nuevo usuario
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String apellidos = apellidosText.getText();
                String correo = correoText.getText();
                String fechaNacimiento = fechaDeNacimientoText.getText();
                String username = nombreUsuarioText.getText();
                String contrasena = new String(passwordText.getPassword());

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || fechaNacimiento.isEmpty() || username.isEmpty() || contrasena.isEmpty() || imagenPerfilUrl == null) {
                    JOptionPane.showMessageDialog(registroFrame, "Por favor completa todos los campos.");
                    return;
                }

                //Verifica si el nombre de usuario ya existe en la base de datos
                Usuario usuarioExistente = usuarioRepository.findByUsername(username);
                if (usuarioExistente != null) {
                    JOptionPane.showMessageDialog(registroFrame, "El nombre de usuario ya existe.");
                    return;
                }

                //Creación del nuevo usuario y actualización de la base de datos
                Usuario usuario = new Usuario(nombre, apellidos, correo, fechaNacimiento, username, contrasena, imagenPerfilUrl, false);
                usuarioRepository.save(usuario);

                JOptionPane.showMessageDialog(registroFrame, "Registro exitoso");
                registroFrame.dispose();
                LoginUI loginUI = new LoginUI(usuarioRepository, publicacionRepository);
                loginUI.initComponents();
            }
        });
    }
}
