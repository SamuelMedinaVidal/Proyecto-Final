package socialNetwork.UI;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private JFrame MainFrame;
    private JButton Perfil;
    public MainUI() {
        initComponent();
    }

    public void initComponent() {

        MainFrame = new JFrame("Main");
        MainFrame.setSize(600, 400);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelUsuario = new JLabel("Usuario: ");
        Perfil = new JButton("Mi perfil");
        panelUsuario.add(labelUsuario);
        panelUsuario.add(Perfil);
        MainFrame.add(panelUsuario);



        MainFrame.setVisible(true);
    }
}
