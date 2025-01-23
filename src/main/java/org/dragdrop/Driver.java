package org.dragdrop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver extends Application {

    private Course currSelected;
    private GridPane grid;
    public static double GRID_SIZE_X = 80;
    public static double GRID_SIZE_Y = 40;
    public static double GRID_PADDING = 5;
    private static int AMOUNT_OF_GRID_SQUARES_X = 5;
    private static int AMOUNT_OF_GRID_SQUARES_Y = 15;

    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Group timeTableGroup = new Group();
        VBox settingsLayout = new VBox();
        root.getChildren().addAll(timeTableGroup, settingsLayout);
        settingsLayout.setLayoutX(GRID_SIZE_X * AMOUNT_OF_GRID_SQUARES_X);

        String[] classLengths = {"1 Hour", "1.5 Hours", "3 Hours"};
        ComboBox<String> timeSelection = new ComboBox<String>(FXCollections.observableArrayList(classLengths));
        Button addClass = new Button("Add Class");

        String[] tags = {"Lecture", "Tutorial", "Lab"};
        TextField courseCode = new TextField();
        ComboBox<String> courseTag = new ComboBox<String>(FXCollections.observableArrayList(tags));
        TextField professor = new TextField();
        TextField room = new TextField();


        addClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(courseCode.getText() != null && courseTag.getValue() != null && professor.getText()!= null && room.getText() != null)
                {
                    double hours;
                    hours = switch (timeSelection.getValue()) {
                        case "1.5 Hours" -> 3.0;
                        case "3 Hours" -> 6.0;
                        default -> 2.0;
                    };

                    Course temp = new Course(GRID_SIZE_X - 10, GRID_SIZE_Y * hours - 10,
                            courseCode.getText(), courseTag.getValue(), professor.getText(), room.getText());

                    timeTableGroup.getChildren().add(temp);
                }
            }
        });

        settingsLayout.setAlignment(Pos.CENTER);

        settingsLayout.getChildren().addAll(new Label("Course Code"), courseCode, new Label("Tag"),
                courseTag, new Label("Professor"), professor, new Label("Room"), room, new Label("time"),
                timeSelection, new Label(" "), addClass);



        Scene scene = new Scene(root, GRID_SIZE_X * AMOUNT_OF_GRID_SQUARES_X - GRID_PADDING + 165,
                GRID_SIZE_Y * AMOUNT_OF_GRID_SQUARES_Y - GRID_PADDING);

        for (int i = 0; i < AMOUNT_OF_GRID_SQUARES_X; i++) {
            for (int j = 0; j < AMOUNT_OF_GRID_SQUARES_Y; j++) {
                TimeSlot temp = new TimeSlot(GRID_SIZE_X - GRID_PADDING,GRID_SIZE_Y - GRID_PADDING, Color.GREY);
                timeTableGroup.getChildren().add(temp);
                temp.setX(i * GRID_SIZE_X);
                temp.setY(j * GRID_SIZE_Y);
            }
        }

        stage.setTitle("Drag and Drop!");
        stage.setResizable(false);
        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}