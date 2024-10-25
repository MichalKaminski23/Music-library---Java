module mk.musiclibrarygui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens mk.musiclibrarygui to javafx.fxml;
    opens mk.models to javafx.base;
    exports mk.musiclibrarygui;
}
