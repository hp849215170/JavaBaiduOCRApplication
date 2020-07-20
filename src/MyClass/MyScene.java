package MyClass;

import javafx.event.EventTarget;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class MyScene extends Scene implements EventTarget {

    public MyScene(Parent root, double width, double height) {
        super(root, width, height, Color.RED);
    }
}
