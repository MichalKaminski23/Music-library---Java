module com.mycompany.music_library_app_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.music_library_app_gui to javafx.fxml;
    exports com.mycompany.music_library_app_gui;
    exports com.mycompany.controllers;
}
