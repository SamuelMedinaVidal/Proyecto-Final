package socialNetwork.UI;

import socialNetwork.Entity.Publicacion;
import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;
import socialNetwork.repository.UsuarioRepository;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainUI {
    private JFrame MainFrame;
    private Usuario usuario;
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;
    private JPanel panel;
    private JPanel panelPublicaciones;
    private JToolBar toolBar;
    private Box box;
    private Map<Long, Boolean> likeStatusMap;//Extraído por IA, es un mapa para rastrear los likes de las publicaciones

    public MainUI(Usuario usuario, UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        this.usuario = usuario;
        this.likeStatusMap = new HashMap<>();
        initComponent();
        mostrarPublicaciones();
    }

    //Inicializamos los componentes de la ventana
    public void initComponent() {
        MainFrame = new JFrame("Página Principal");
        MainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new BorderLayout());
        botones(panel);
        MainFrame.add(panel);
        MainFrame.setVisible(true);
    }

    public void botones(JPanel panel) {
        JButton btnMiPerfil = new JButton("Mi Perfil");
        JButton btnLogOut = new JButton("Cerrar Sesión");
        JButton btnNuevaPublicacion = new JButton("Nueva Publicación");

        btnMiPerfil.setBorderPainted(false);
        btnLogOut.setBorderPainted(false);
        btnNuevaPublicacion.setBorderPainted(false);

        btnMiPerfil.setFont(new Font("Arial", Font.BOLD, 20));
        btnLogOut.setFont(new Font("Arial", Font.BOLD, 20));
        btnNuevaPublicacion.setFont(new Font("Arial", Font.BOLD, 20));

        // Añadir botón "Funciones de administrador" si el usuario es administrador
        JButton btnAdminFunciones = null;
        if (usuario.getEs_administrador()) {
            btnAdminFunciones = new JButton("Funciones de administrador");
            btnAdminFunciones.setBorderPainted(false);
            btnAdminFunciones.setFont(new Font("Arial", Font.BOLD, 20));

            // Añadir acción al botón de funciones de administrador
            btnAdminFunciones.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FuncionesAdminUI(usuarioRepository, publicacionRepository);
                }
            });
        }

        //Acción para cerrar sesión y volver a la ventana de LoginUI
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.dispose();
                LoginUI loginUI = new LoginUI(usuarioRepository, publicacionRepository);
                loginUI.initComponents();
            }
        });

        //Acción para abrir la ventana de perfil de usuario
        btnMiPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerfilUI(usuario, usuarioRepository, publicacionRepository);
            }
        });

        //Acción para abrir la ventana de NuevaPublicacionUI
        btnNuevaPublicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NuevaPublicacionUI(publicacionRepository, usuario, MainUI.this);
            }
        });

        createToolBar(panel, btnLogOut, btnMiPerfil, btnNuevaPublicacion, btnAdminFunciones);
    }

    //Creación de la barra de herramientas en la ventana
    public void createToolBar(JPanel panel, JButton btnLogOut, JButton btnMiPerfil, JButton btnNuevaPublicacion, JButton btnAdminFunciones) {
        toolBar = new JToolBar();
        box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(btnLogOut);
        box.add(btnMiPerfil);
        box.add(btnNuevaPublicacion);
        if (btnAdminFunciones != null) {
            box.add(btnAdminFunciones);
        }
        box.add(Box.createHorizontalGlue());

        toolBar.add(box);
        panel.add(toolBar, BorderLayout.NORTH);
    }

    public void mostrarPublicaciones() {
        panelPublicaciones = new JPanel();
        panelPublicaciones.setLayout(new BoxLayout(panelPublicaciones, BoxLayout.Y_AXIS));

        actualizarPanelPublicaciones();

        JScrollPane scrollPane = new JScrollPane(panelPublicaciones);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public void actualizarPanelPublicaciones() {
        panelPublicaciones.removeAll();

        List<Publicacion> publicaciones = publicacionRepository.findAll();

        for (Publicacion publicacion : publicaciones) {
            JPanel publi = crearPanelPublicacion(publicacion);
            panelPublicaciones.add(publi);
            panelPublicaciones.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        panelPublicaciones.revalidate();
        panelPublicaciones.repaint();
    }

    public JPanel crearPanelPublicacion(Publicacion publicacion) {
        JPanel panelPubli = new JPanel();
        panelPubli.setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tituloLabel = new JLabel(publicacion.getTitulo());
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelTitulo.add(tituloLabel);
        panelPubli.add(panelTitulo, BorderLayout.NORTH);

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        JLabel textoLabel = new JLabel(publicacion.getTexto());
        textoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelContenido.add(textoLabel);

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

        JLabel likesLabel = new JLabel("Likes: " + publicacion.getLikes());
        likesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panelContenido.add(likesLabel);

        JLabel usuarioLabel = new JLabel("Usuario: " + publicacion.getUsuario().getUsername());
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usuarioLabel.setForeground(Color.BLUE);
        usuarioLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        usuarioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UsuarioPublicacionesUI(publicacion.getUsuario(), usuarioRepository, publicacionRepository);
            }
        });
        panelContenido.add(usuarioLabel);

        //Muestra los comentarios de la publicación
        if (!publicacion.getComentarios().isEmpty()) {
            JPanel panelComentarios = new JPanel();
            panelComentarios.setLayout(new BoxLayout(panelComentarios, BoxLayout.Y_AXIS));

            TitledBorder border = BorderFactory.createTitledBorder("Comentarios");
            border.setTitleFont(new Font("Arial", Font.BOLD, 12));
            panelComentarios.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            for (socialNetwork.Entity.Comentario comentario : publicacion.getComentarios()) {
                JLabel comentarioLabel = new JLabel(comentario.getUsuario().getUsername() + ": " + comentario.getComentario());
                comentarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                panelComentarios.add(comentarioLabel);
            }
            panelContenido.add(panelComentarios);
        }

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnAgregarComentario = new JButton("Añadir Comentario");
        JButton btnLike = new JButton("Like");

        //Acción para añadir un comentario a la publicación
        btnAgregarComentario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoComentario = JOptionPane.showInputDialog(MainFrame, "Escribe tu comentario:");
                if (nuevoComentario != null && !nuevoComentario.isEmpty()) {
                    socialNetwork.Entity.Comentario comentario = new socialNetwork.Entity.Comentario(nuevoComentario, usuario, publicacion);
                    publicacion.agregarComentario(comentario);
                    publicacionRepository.save(publicacion);
                    actualizarPanelPublicaciones();
                }
            }
        });

        //Acción para dar like a la publicación
        btnLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean haDadoLike = likeStatusMap.getOrDefault(publicacion.getId_publicacion(), false);

                if (haDadoLike) {
                    publicacion.setLikes(publicacion.getLikes() - 1);
                    likeStatusMap.put(publicacion.getId_publicacion(), false);
                } else {
                    publicacion.setLikes(publicacion.getLikes() + 1);
                    likeStatusMap.put(publicacion.getId_publicacion(), true);
                }

                // Guardar la publicación actualizada
                publicacionRepository.save(publicacion);

                // Actualizar el panel de publicaciones para reflejar los cambios
                actualizarPanelPublicaciones();
            }
        });

        panelBotones.add(btnAgregarComentario);
        panelBotones.add(btnLike);

        panelPubli.add(panelContenido, BorderLayout.CENTER);
        panelPubli.add(panelBotones, BorderLayout.SOUTH);

        return panelPubli;
    }

    public void actualizarPublicaciones() {
        actualizarPanelPublicaciones();
    }
}
