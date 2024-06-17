package socialNetwork.UI;

import socialNetwork.Entity.Publicacion;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NuevaPublicacionUI {
    private PublicacionRepository publicacionRepository;
    private Usuario usuario;
    private MainUI mainUI;
    private JFrame publiFrame;
    private JPanel panel;
    private JButton cancelar;
    private JButton publicar;
    private JButton generarImagen;
    private JTextField tituloText;
    private JTextArea contenidoText;
    private JLabel imagenLabel;
    private String imagenUrl;

    public NuevaPublicacionUI(PublicacionRepository publicacionRepository, Usuario usuario, MainUI mainUI) {
        this.publicacionRepository = publicacionRepository;
        this.usuario = usuario;
        this.mainUI = mainUI;
        initComponent();
    }

    public void initComponent() {
        publiFrame = new JFrame("Nueva Publicación");
        publiFrame.setSize(700, 750);
        publiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);
        publiFrame.add(panel);
        organizacion();
        botones();
        publiFrame.setLocationRelativeTo(null);
        publiFrame.setVisible(true);
    }

    public void organizacion() {
        panel.setLayout(null);

        JLabel tituloLabel = new JLabel("Titulo:");
        tituloLabel.setBounds(10, 10, 80, 25);
        panel.add(tituloLabel);

        tituloText = new JTextField(20);
        tituloText.setBounds(100, 10, 260, 25);
        panel.add(tituloText);

        JLabel contenidoLabel = new JLabel("Contenido:");
        contenidoLabel.setBounds(10, 40, 80, 25);
        panel.add(contenidoLabel);

        contenidoText = new JTextArea();
        contenidoText.setBounds(100, 40, 260, 250);
        contenidoText.setLineWrap(true);
        contenidoText.setWrapStyleWord(true);
        panel.add(contenidoText);

        // Componentes para la imagen
        imagenLabel = new JLabel();
        imagenLabel.setBounds(100, 300, 300, 300);
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(imagenLabel);

        generarImagen = new JButton("Generar imagen");
        generarImagen.setBounds(100, 610, 160, 25);
        panel.add(generarImagen);

        generarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("https://picsum.photos/300/300");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(false);
                    connection.connect();

                    String finalImageUrl = connection.getHeaderField("Location");
                    imagenUrl = finalImageUrl != null ? finalImageUrl : "https://picsum.photos/300/300";

                    Image image = ImageIO.read(new URL(imagenUrl));
                    ImageIcon icon = new ImageIcon(image);
                    imagenLabel.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void botones() {
        publicar = new JButton("Publicar");
        publicar.setBounds(300, 650, 160, 25);
        panel.add(publicar);

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(100, 650, 160, 25);
        panel.add(cancelar);

        publicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloText.getText();
                String contenido = contenidoText.getText();

                if (titulo.isEmpty() || contenido.isEmpty()) {
                    JOptionPane.showMessageDialog(publiFrame, "Por favor completa todos los campos.");
                } else {
                    Publicacion nuevaPubli = new Publicacion();
                    nuevaPubli.setTitulo(titulo);
                    nuevaPubli.setTexto(contenido);
                    nuevaPubli.setUsuario(usuario);
                    nuevaPubli.setLikes(0);
                    if (imagenUrl != null) {
                        nuevaPubli.setImagen(imagenUrl);
                    }

                    publicacionRepository.save(nuevaPubli);
                    JOptionPane.showMessageDialog(publiFrame, "Has creado una nueva publicación");
                    publiFrame.dispose();
                    mainUI.actualizarPublicaciones();
                }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publiFrame.dispose();
            }
        });
    }
}
