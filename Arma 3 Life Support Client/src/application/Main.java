package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Properties properties = new Properties();
    public static String messages[];

    public static final String APP_TITLE = "Arma 3 Life Support Client";
    public static final String APP_VERSION = "0.1.A3L";

    private static final String PROPERTY_FILE = "config.properties";
    private static final int MIN_HEIGHT = 550;
    private static final int MIN_WIDTH = 450;

    public static void main(String[] args) {

	Path path = Paths.get(PROPERTY_FILE);

	// Create if properties doesn't exist.
	if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
	    properties.setProperty("interview_procedure_url",
		    "");
	    properties.setProperty("interview_questions_url",
		    "");
	    properties.setProperty("official_rules_url",
		    "");
	    properties.setProperty("post_to_forums_url",
		    "");
	    properties.setProperty("post_to_secondary_log_url",
		    "");
	    properties.setProperty("developer_url", "https://andrewfernandessbarbaro.com/");
	    properties.setProperty("report_bug_url",
		    "");
	    properties.setProperty("post_log_support_ticket",
		    "");
	    properties.setProperty("post_log_team_speak",
		    "");

	    properties.setProperty("min_age", "15");

	    properties.setProperty("theme", "theme-1");

	    properties.setProperty("messages",
		    "Fun Fact: Banging your head against a wall burns 150 calories an hour.#Stop...#Why Tho?#Do your Job!#You're ruining my immersion.#I have really nice titties don't I.#That's a Strike!#Fun Fact: A more polite way of saying 'Stop or Die' would be 'Halt or Perish'#Fun Fact: Cherophobia is the fear of fun.#You're wasting your time!#Contacting a Senior Support! #You're Fired!#Why are you clicking the logo so much?#Dylan Grant made me add this feature back xD# The closest a person ever comes to perfection is when he fills out a job application form");

	    saveProperties(null);
	}

	// Load properties from the file.
	try (InputStream input = new FileInputStream("config.properties")) {
	    properties.load(input);
	    messages = properties.getProperty("messages").split("#");
	} catch (IOException ex) {
	    ex.printStackTrace();
	}

	launch(args);
    }

    /**
     * Save the properties to disk
     *
     * @param newProperties
     */
    public static void saveProperties(String comments) {
	try (OutputStream output = new FileOutputStream(PROPERTY_FILE)) {
	    properties.store(output, comments);
	} catch (IOException io) {
	    io.printStackTrace();
	}
    }

    @Override
    public void start(Stage primaryStage) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));
	    Parent rootPane = loader.load();
	    Scene scene = new Scene(rootPane, MIN_WIDTH, MIN_HEIGHT);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	    MainUIController mainUIController = loader.getController();
	    mainUIController.setHostServices(getHostServices());

	    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
	    primaryStage.setScene(scene);
	    primaryStage.setTitle(APP_TITLE);
	    primaryStage.setMinHeight(MIN_HEIGHT);
	    primaryStage.setMinWidth(MIN_WIDTH);
	    primaryStage.setResizable(false);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
