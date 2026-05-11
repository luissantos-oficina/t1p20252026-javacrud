package views;

import java.nio.file.Files;
import java.nio.file.Path;

public class HomeView {
    
    // HOME
    public static String home() throws Exception {

        return Files.readString(
                Path.of("templates/home.html")
        );
    }    
}

