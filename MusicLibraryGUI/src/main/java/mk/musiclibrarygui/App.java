package mk.musiclibrarygui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import mk.musiclibrarygui.models.SongList;

/**
 * Main application class for the Music Library GUI. Initializes the application
 * and loads the main scene.
 *
 * @author Michal Kaminski
 * @version 3.0
 */
public class App extends Application {

    /**
     * Empty constructor for the App class.
     */
    @SuppressWarnings("empty-statement")
    public App() {
        ;
    }

    /**
     * The main Scene for the application.
     */
    private Scene scene;

    /**
     * Stores the list of songs in the program.
     */
    private SongList songList;

    /**
     * Starts the main application window and initializes the main scene.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the main scene FXML cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.songList = new SongList();
        scene = new Scene(loadFXML("/mk/musiclibrarygui/views/Menu"), 700, 450);
        stage.setTitle("Music library");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the current scene to a new FXML layout.
     *
     * @param fxml the path to the FXML file to load
     * @throws IOException if the FXML file cannot be loaded
     */
    public void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    /**
     * Loads and returns an FXML layout as a Parent node.
     *
     * @param fxml the path to the FXML file to load
     * @return the loaded Parent node
     * @throws IOException if the FXML file cannot be loaded
     */
    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));
        return fxmlLoader.load();
    }

    /**
     * Creates an instance of a controller, injecting SongList if required.
     *
     * @param controllerClass the class of the controller to instantiate
     * @return the instantiated controller object, or null if instantiation
     * fails
     */
    private Object createControllerInstance(Class<?> controllerClass) {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(SongList.class, App.class);
            return constructor.newInstance(songList, this);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch();
    }
}
