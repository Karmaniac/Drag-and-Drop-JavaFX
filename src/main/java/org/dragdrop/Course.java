package org.dragdrop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Course extends StackPane {


    private String courseCode;
    private String courseTag; //Lect, Lab, Tut
    private String professor;
    private String room;
    public Course(double width, double height, String courseCode, String courseTag, String professor, String room)
    {
        super();
        super.setAlignment(Pos.CENTER);

        Rectangle image = new Rectangle(width, height, Color.GREEN);

        VBox textLayout = new VBox();
        textLayout.getChildren().addAll(new Label(courseCode), new Label(courseTag),
                new Label(professor), new Label(room));

        super.getChildren().addAll(image, textLayout);

        this.courseCode = courseCode;
        this.courseTag = courseTag;
        this.professor = professor;
        this.room = room;



        DragController dragController = new DragController(this, true);
    }
}
