package gui;

import fontMeshCreator.FontType;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.util.ArrayList;
import java.util.List;


public class TextBox {
    
    private Vector2f position;
    private Vector2f size;
    private Vector3f backgroundColor;
    private Vector3f borderColor;
    private Vector3f textColor;
    private List<GUIText> texts = new ArrayList<>();
    private float border;
    private GUIFilledBox guiFilledBox;

    private static final float LINE_HEIGHT = 0.06f;

    public TextBox(Vector2f position, Vector2f size, Vector3f backgroundColor, Vector3f textColor, Vector3f borderColor, String content, FontType font, float fontSize, float thickness, float borderWidth, float border) {
        this.position = position;
        this.size = size;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        String[] lines = content.split("\n");
        float minHeight = border;
        float lineHeight = LINE_HEIGHT*fontSize;
        for (String line : lines){
		    texts.add(new GUIText(line, fontSize, font, new Vector2f(border + position.x-1,position.y-minHeight+size.y-1), thickness, borderWidth, textColor, new Vector4f(position.x, position.y, position.x + size.x, position.y + size.y), false));
		    minHeight += lineHeight;
        }
        guiFilledBox = new GUIFilledBox(position, size, backgroundColor);
    }

    public TextBox(Vector2f position, Vector3f backgroundColor, Vector3f textColor, Vector3f borderColor, String content, FontType font, float fontSize, float thickness, float borderWidth, float border) {
        this.position = position;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        this.border = border;
        String[] lines = content.split("\n");
        float lineHeight = LINE_HEIGHT * fontSize;
        float minHeight = border;
        double greatestLength = 0;

        for (String line : lines){
		    GUIText text = new GUIText(line, fontSize, font, new Vector2f(border + position.x-1,position.y-minHeight - 1 - border + lineHeight*lines.length), thickness, borderWidth, textColor, null, false);
		    texts.add(text);
            if (text.getLength() > greatestLength){
                greatestLength = text.getLength();
            }
            minHeight+=lineHeight;
        }
        this.size = new Vector2f((float)greatestLength*2 + 4*border,lineHeight*lines.length + border);
        for(GUIText text : texts){
            if(text.getPositionBounds() == null){
                text.setPositionBounds(new Vector4f(position.x, position.y, position.x+size.x, position.y+size.y));
            }
        }
        guiFilledBox = new GUIFilledBox(position, size, backgroundColor);
    }


    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getSize() {
        return size;
    }

    public Vector3f getBackgroundColor(){
        return backgroundColor;
    }

    public void changeVerticalPosition(float change){
        position.y += change;
        for(GUIText text : texts){
            text.changeVerticalPosition(change);
        }
    }

    public void changeHorizontalPosition(float change){
        position.x += change;
        for(GUIText text : texts){
            text.changeHorizontalPosition(change);
        }
    }

    public void changeContentsVerticalPosition(float change){
        for(GUIText text : texts){
            text.changeVerticalPosition(change);
        }
    }

    public void changeContentsHorizontalPosition(float change){
        for(GUIText text : texts){
            text.changeHorizontalPosition(change);
        }
    }

    public List<GUIText> getTexts(){
        return texts;
    }

    public void removeText(GUIText text){
        int index = texts.indexOf(text);
        float heightChange = text.getFontSize() * 0.06f;
        texts.remove(index);
        text.remove(text);
        for(int i = index; i < texts.size(); i++){
            texts.get(i).changeVerticalPosition(heightChange);
        }
    }

    public void addText(GUIText text, int index){
        float heightChange = text.getFontSize() * 0.06f;
        texts.add(index, text);
        for(int i = index + 1; i < texts.size(); i++){
            texts.get(i).changeVerticalPosition(-heightChange);
        }
    }

    public GUIText mergeTexts(GUIText left, GUIText right){
        int index = texts.indexOf(left);
        String rightText = right.getTextString();
        removeText(right);
        GUIText newText = new GUIText(left.getTextString() + rightText, left, true);
        texts.set(index, newText);
        return newText;
    }

    public GUIFilledBox getGuiFilledBox() {
        return guiFilledBox;
    }
}