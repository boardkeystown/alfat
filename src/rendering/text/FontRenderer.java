package rendering.text;

import java.util.List;
import java.util.Map;

import dataStructures.RawModel;
import loaders.Loader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;

public class FontRenderer {

	private FontShader shader;
	private static final float[] VERTICES = {
			-0.5f, -0.5f,
			-0.5f, 0.5f,
			0.5f, 0.5f,
			0.5f, -0.5f
	};
	private RawModel square;

	public FontRenderer() {
		shader = new FontShader();
		square = Loader.loadToVAO(VERTICES, 2);
	}


	public void render(Map<FontType, List<GUIText>> texts){
		prepare();
		for(FontType font : texts.keySet()){
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTextureAtlas());
			for(GUIText text : texts.get(font)){
				renderText(text);
			}
		}
		endRendering();


	}

	public void cleanUp(){
		shader.cleanUp();
	}
	
	private void prepare(){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		shader.start();
	}
	
	private void renderText(GUIText text){
		GL30.glBindVertexArray(text.getMesh());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		shader.color.loadVec3(text.getColour());
		shader.width.loadFloat(text.getWidth());
		shader.edge.loadFloat(text.getEdge());
		shader.borderWidth.loadFloat(text.getBorderWidth());
		shader.borderEdge.loadFloat(text.getBorderEdge());
		shader.offset.loadVec2(text.getOffset());
		shader.outlineColor.loadVec3(text.getOutlineColor());
		shader.translation.loadVec2(text.getPosition());
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	private void endRendering(){
		shader.stop();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

}
