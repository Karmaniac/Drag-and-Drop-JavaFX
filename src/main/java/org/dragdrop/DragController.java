package org.dragdrop;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
public class DragController {
    private final Node target;
    private double anchorX;
    private double anchorY;
    private double mouseOffsetFromNodeZeroX;
    private double mouseOffsetFromNodeZeroY;
    private EventHandler<MouseEvent> setAnchor;
    private EventHandler<MouseEvent> updatePositionOnDrag;
    private EventHandler<MouseEvent> commitPositionOnRelease;
    private final int ACTIVE = 1;
    private final int INACTIVE = 0;
    private int cycleStatus = INACTIVE;
    private BooleanProperty isDraggable;
    private double prevX;
    private double prevY;
    public DragController(Node target) {
        this(target, false);
    }
    public DragController(Node target, boolean isDraggable) {
        this.target = target;
        createHandlers();
        createDraggableProperty();
        this.isDraggable.set(isDraggable);
    }
    private void createHandlers() {
        setAnchor = event -> {
            if (event.isPrimaryButtonDown()) {
                cycleStatus = ACTIVE;
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                mouseOffsetFromNodeZeroX = event.getX();
                mouseOffsetFromNodeZeroY = event.getY();

                prevX = (event.getSceneX() - (event.getSceneX() % Driver.GRID_SIZE_X)) + (Driver.GRID_PADDING / 2);
                prevY = (event.getSceneY() - (event.getSceneY() % Driver.GRID_SIZE_Y)) + (Driver.GRID_PADDING / 2);
            }
            if (event.isSecondaryButtonDown()) {
                cycleStatus = INACTIVE;
                target.setTranslateX(0);
                target.setTranslateY(0);
            }
        };
        updatePositionOnDrag = event -> {
            if (cycleStatus != INACTIVE) {
                target.setTranslateX(event.getSceneX() - anchorX);
                target.setTranslateY(event.getSceneY() - anchorY);
            }
        };
        commitPositionOnRelease = event -> {
            if (cycleStatus != INACTIVE) {
                double roundedX = (event.getSceneX() - (event.getSceneX() % Driver.GRID_SIZE_X)) + (Driver.GRID_PADDING / 2);
                double roundedY = (event.getSceneY() - (event.getSceneY() % Driver.GRID_SIZE_Y)) + (Driver.GRID_PADDING / 2);

                int roundedXArr = (int) (roundedX / Driver.GRID_SIZE_X);
                int roundedYArr = (int) (roundedY / Driver.GRID_SIZE_Y);

                //commit changes to LayoutX and LayoutY
                target.setLayoutX(roundedX);
                target.setLayoutY(roundedY);

                //clear changes from TranslateX and TranslateY
                target.setTranslateX(0);
                target.setTranslateY(0);
            }
        };
    }
    public void createDraggableProperty() {
        isDraggable = new SimpleBooleanProperty();
        isDraggable.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                target.addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.addEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            } else {
                target.removeEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.removeEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.removeEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            }
        });
    }
    public boolean isIsDraggable() {
        return isDraggable.get();
    }
    public BooleanProperty isDraggableProperty() {
        return isDraggable;
    }
}