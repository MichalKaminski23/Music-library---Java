package mk.musiclibrarygui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import mk.models.SongList;

public class App extends Application {

    private static Scene scene;
    private static SongList songList;

    @Override
    public void start(Stage stage) throws IOException {
        this.songList = new SongList();
        scene = new Scene(loadFXML("MusicTable"), 700, 450);
        stage.setTitle("Music library");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));

        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));
        return fxmlLoader.load();
    }

    private static Object createControllerInstance(Class<?> controllerClass) {
        try {
            // Sprawdź, czy kontroler ma konstruktor z argumentem SongList
            try {
                Constructor<?> constructor = controllerClass.getConstructor(SongList.class);
                return constructor.newInstance(songList);
            } catch (NoSuchMethodException e) {
                // Jeśli nie ma takiego konstruktora, sprawdź, czy ma konstruktor bezargumentowy
                try {
                    Constructor<?> constructor = controllerClass.getConstructor();
                    return constructor.newInstance();
                } catch (NoSuchMethodException ex) {
                    System.out.println("Brak konstruktorów dla klasy: " + controllerClass.getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        launch();
    }

}
