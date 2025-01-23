module org.dragdrop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.dragdrop to javafx.fxml;
    exports org.dragdrop;
}