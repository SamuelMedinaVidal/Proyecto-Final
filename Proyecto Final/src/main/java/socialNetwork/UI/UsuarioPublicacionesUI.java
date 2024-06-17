package socialNetwork.UI;

import socialNetwork.Entity.Publicacion;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;
import socialNetwork.repository.UsuarioRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UsuarioPublicacionesUI {
    private JFrame perfilFrame;
    private Usuario usuario;
    private JLabel nombreUsuarioLabel;
    private JLabel imagenLabel;
    private JPanel publicacionesPanel;
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;

    public UsuarioPublicacionesUI(Usuario usuario, UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuario = usuario;
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        initComponent();
    }

    public void initComponent() {
        perfilFrame = new JFrame("Perfil de " + usuario.getUsername());
        perfilFrame.setSize(600, 800);
        perfilFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        perfilFrame.add(panel1);
        organizacion(panel1);
        perfilFrame.setLocationRelativeTo(null);
        perfilFrame.setVisible(true);
    }

    public void organizacion(JPanel panel1) {
        panel1.setLayout(null);

        nombreUsuarioLabel = new JLabel(usuario.getUsername());
        nombreUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nombreUsuarioLabel.setBounds(10, 10, 300, 25);
        panel1.add(nombreUsuarioLabel);

        imagenLabel = new JLabel();
        imagenLabel.setBounds(10, 50, 300, 300);
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

        publicacionesPanel = new JPanel();
        publicacionesPanel.setLayout(new BoxLayout(publicacionesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(publicacionesPanel);
        scrollPane.setBounds(10, 370, 560, 350);
        panel1.add(scrollPane);

        mostrarPublicaciones();
    }

    public void mostrarPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findByUsuario(usuario);
        publicacionesPanel.removeAll();

        for (Publicacion publicacion : publicaciones) {
            JPanel panelPublicacion = new JPanel();
            panelPublicacion.setLayout(new BorderLayout());

            JLabel titulo = new JLabel(publicacion.getTitulo());
            titulo.setFont(new Font("Arial", Font.BOLD, 16));
            panelPublicacion.add(titulo, BorderLayout.NORTH);

            JPanel panelContenido = new JPanel();
            panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

            JTextArea contenido = new JTextArea(publicacion.getTexto());
            contenido.setWrapStyleWord(true);
            contenido.setLineWrap(true);
            contenido.setEditable(false);
            panelContenido.add(new JScrollPane(contenido));

            // Añadir la imagen de la publicación
            if (publicacion.getImagen() != null) {
                try {
                    URL url = new URL(publicacion.getImagen());
                    Image image = ImageIO.read(url);
                    JLabel imagenLabel = new JLabel(new ImageIcon(image));
                    panelContenido.add(imagenLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            panelPublicacion.add(panelContenido, BorderLayout.CENTER);
            publicacionesPanel.add(panelPublicacion);
            publicacionesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        publicacionesPanel.revalidate();
        publicacionesPanel.repaint();
    }
}
