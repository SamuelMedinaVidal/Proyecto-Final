package socialNetwork;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import socialNetwork.UI.LoginUI;

import java.awt.*;

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
        });
    }
}

