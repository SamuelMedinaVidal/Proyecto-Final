package socialNetwork;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import socialNetwork.UI.LoginUI;

import java.awt.*;
//Inicia el programa indicando que es una aplicación SpringBoot y que no es una aplicación web.
//Posteriormente abre la ventana de LoginUI.
@SpringBootApplication
public class Main {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(Main.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        EventQueue.invokeLater(() -> {
            LoginUI loginUI = context.getBean(LoginUI.class);
            loginUI.initComponents();
        });
    }
}
