package socialNetwork.UI;

import socialNetwork.Entity.Usuario;
import socialNetwork.repository.PublicacionRepository;
import socialNetwork.repository.UsuarioRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FuncionesAdminUI {
    private JFrame adminFrame;
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;

    public FuncionesAdminUI(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        initComponent();
    }

    //Inicializamos los componentes de la ventana
    public void initComponent() {
        adminFrame = new JFrame("Funciones de Administrador");
        adminFrame.setSize(600, 400);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        adminFrame.add(panel);
        organizacion(panel);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }


    public void organizacion(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Funciones de Administrador");
        label.setBounds(10, 20, 200, 25);
        panel.add(label);

        // Botón para generar el bloc de notas con datos de usuarios
        JButton btnGenerarBloc = new JButton("Generar Lista Usuarios");
        btnGenerarBloc.setBounds(10, 60, 300, 25);
        panel.add(btnGenerarBloc);

        // Acción del botón para generar el bloc de notas
        btnGenerarBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarBlocDeNotas();
            }
        });
    }

    // Método para generar el archivo de bloc de notas con los datos de los usuarios de la base de datos
    //Utilizando FileWriter
    public void generarBlocDeNotas() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(adminFrame, "Bloc de notas generado exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(adminFrame, "Error al generar el bloc de notas: " + e.getMessage());
        }
    }
}
