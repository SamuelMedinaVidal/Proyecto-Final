package socialNetwork.UI;

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

//Prácticamente igual a la clase de registro
public class EditarPerfilUI {
    private JFrame perfilFrame;
    private Usuario usuario;
    private JButton volver;
    private JButton guardar;
    private JButton mostarPassword;
    private JButton cambiarImagen;
    private JTextField nombreText;
    private JTextField apellidosText;
    private JTextField correoText;
    private JTextField fechaDeNacimientoText;
    private JTextField nombreUsuarioText;
    private JPasswordField passwordText;
    private JLabel imagenLabel;
    private String imagenPerfilUrl;
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;

    public EditarPerfilUI(Usuario usuario, UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuario = usuario;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        initComponent();
    }

    public void initComponent() {
        perfilFrame = new JFrame("Editar Perfil");
        perfilFrame.setSize(500, 700);
        perfilFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        perfilFrame.add(panel1);
        organizacion(panel1);
        perfilFrame.setLocationRelativeTo(null);
        perfilFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 10, 80, 25);
        panel1.add(nombreLabel);

        nombreText = new JTextField(usuario.getNombre());
        nombreText.setBounds(100, 10, 160, 25);
        panel1.add(nombreText);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosLabel.setBounds(10, 40, 80, 25);
        panel1.add(apellidosLabel);

        apellidosText = new JTextField(usuario.getApellidos());
        apellidosText.setBounds(100, 40, 160, 25);
        panel1.add(apellidosText);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 70, 80, 25);
        panel1.add(correoLabel);

        correoText = new JTextField(usuario.getCorreo());
        correoText.setBounds(100, 70, 160, 25);
        panel1.add(correoText);

        JLabel fechaDeNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaDeNacimientoLabel.setBounds(10, 100, 160, 25);
        panel1.add(fechaDeNacimientoLabel);

        fechaDeNacimientoText = new JTextField(usuario.getFecha_de_nacimiento());
        fechaDeNacimientoText.setBounds(170, 100, 160, 25);
        panel1.add(fechaDeNacimientoText);

        JLabel nombreUsuarioLabel = new JLabel("Username:");
        nombreUsuarioLabel.setBounds(10, 130, 80, 25);
        panel1.add(nombreUsuarioLabel);

        nombreUsuarioText = new JTextField(usuario.getUsername());
        nombreUsuarioText.setBounds(100, 130, 160, 25);
        panel1.add(nombreUsuarioText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 160, 80, 25);
        panel1.add(passwordLabel);

        passwordText = new JPasswordField(usuario.getContrasena());
        passwordText.setBounds(100, 160, 160, 25);
        panel1.add(passwordText);

        // Añadir componentes para la imagen de perfil
        imagenLabel = new JLabel();
        imagenLabel.setBounds(100, 200, 300, 300);
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.add(imagenLabel);
        try {
            URL url = new URL(usuario.getFoto_de_perfil());
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image);
            imagenLabel.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cambiarImagen = new JButton("Cambiar Imagen");
        cambiarImagen.setBounds(100, 510, 160, 25);
        panel1.add(cambiarImagen);

        cambiarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("https://picsum.photos/300/300");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(false);
                    connection.connect();
                    //Obtiene la url final de la imagen(evitamos que muestre sólo imágenes aleatorias) y la asigna a la variable imagenPerfilUrl
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
        volver = new JButton("Volver");
        volver.setBounds(10, 550, 160, 25);
        panel1.add(volver);

        guardar = new JButton("Guardar");
        guardar.setBounds(200, 550, 160, 25);
        panel1.add(guardar);

        mostarPassword = new JButton("Mostrar contraseña");
        mostarPassword.setBounds(270, 160, 190, 25);
        panel1.add(mostarPassword);

        mostarPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasena = new String(passwordText.getPassword());
                JOptionPane.showMessageDialog(null, contrasena);
            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perfilFrame.dispose();
                new PerfilUI(usuario, usuarioRepository, publicacionRepository);
            }
        });

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String apellidos = apellidosText.getText();
                String correo = correoText.getText();
                String fechaNacimiento = fechaDeNacimientoText.getText();
                String username = nombreUsuarioText.getText();
                String contrasena = new String(passwordText.getPassword());

                // Verificar campos vacíos
                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || fechaNacimiento.isEmpty() || username.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(perfilFrame, "Todos los campos deben ser completados.");
                    return;
                }

                usuario.setNombre(nombre);
                usuario.setApellidos(apellidos);
                usuario.setCorreo(correo);
                usuario.setFecha_de_nacimiento(fechaNacimiento);
                usuario.setUsername(username);
                usuario.setContrasena(contrasena);

                if (imagenPerfilUrl != null) {
                    usuario.setFoto_de_perfil(imagenPerfilUrl);
                }

                usuarioRepository.save(usuario);

                JOptionPane.showMessageDialog(perfilFrame, "Cambios realizados");
                perfilFrame.dispose();
                new PerfilUI(usuario, usuarioRepository, publicacionRepository);
            }
        });
    }
}
