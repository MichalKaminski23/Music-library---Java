/**
 * This module descriptor defines the mk.musiclibrarygui module,
 * specifying its dependencies and which packages are accessible
 * to other modules or JavaFX components.
 *
 * It requires the following modules:
 * - javafx.controls: Provides UI controls for JavaFX applications.
 * - javafx.fxml: Supports FXML for defining user interfaces.
 * - java.base: The base module that includes essential Java classes.
 *
 * The following packages are opened for reflection:
 * - mk.musiclibrarygui: Main package of the music library GUI application.
 * - mk.musiclibrarygui.controllers: Contains controller classes for FXML.
 * - mk.musiclibrarygui.models: Contains model classes for song management.
 *
 * The mk.musiclibrarygui package is exported for use by other modules.
 *
 * @author Michal Kaminski
 * @version 3.0
 */
module mk.musiclibrarygui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;

    opens mk.musiclibrarygui.controllers to javafx.fxml;
       
    exports mk.musiclibrarygui;
    exports mk.musiclibrarygui.controllers;
    exports mk.musiclibrarygui.models;
}
