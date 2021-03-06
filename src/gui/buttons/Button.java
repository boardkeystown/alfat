package gui.buttons;

import org.lwjgl.util.vector.Vector2f;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Button{

    private Vector2f position;
    private Vector2f size;

    public Button(Vector2f position, Vector2f size) {
        this.position = position;
        this.size = size;
    }

    public Button(){
    }

    public abstract void onPress();

    public Vector2f getPosition(){
        return position;
    }

    public Vector2f getSize(){
        return size;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }
}
