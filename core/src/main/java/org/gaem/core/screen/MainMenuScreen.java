package org.gaem.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.gaem.core.AGame;
import org.gaem.core.ui.widgets.KeyboardListener;

/**
 * Created by Johan on 2014-09-27.
 */
public class MainMenuScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private Table root;

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.zoom = 0.5f;

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.stage.getViewport().setCamera(camera);
        this.root = new Table();
        this.root.setFillParent(true);

        this.stage.addActor(root);

        Table child = new Table(getSkin());
        child.setBackground("border");
        Cell c = root.add(child);
        c.size(300, 200);

        TextButton button = new TextButton("HAI DER", getSkin());
        stage.setKeyboardFocus(button);
        child.addListener(new KeyboardListener());

        Cell c2 = child.add(button);
        //c2.size(5, 5);
    }

    private Skin getSkin() {
        Skin s = new Skin();

        Texture src = AGame.ASSETS.get("ui/ui.png", Texture.class);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(255, 255, 255, 0));
        pixmap.fill();

        s.add("transparent", new Texture(pixmap));
        NinePatch window = new NinePatch(new TextureRegion(src, 0, 0, 16, 16),
                new TextureRegion(src, 16, 0, 16, 16), new TextureRegion(src, 32, 0, 16, 16),
                new TextureRegion(src, 0, 16, 16, 16), new TextureRegion(src, 16, 16, 16, 16),
                new TextureRegion(src, 32, 16, 16, 16), new TextureRegion(src, 0, 32, 16, 16),
                new TextureRegion(src, 16, 32, 16, 16), new TextureRegion(src, 32, 32, 16, 16));
        s.add("border", window);

        TextButton.TextButtonStyle tbStyle = new TextButton.TextButtonStyle();
        tbStyle.font = AGame.ASSETS.get("fonts/SILKWONDER.fnt", BitmapFont.class);
        tbStyle.up = s.getDrawable("transparent");
        tbStyle.down = s.getDrawable("transparent");
        tbStyle.overFontColor = Color.BLACK;
        tbStyle.fontColor = Color.WHITE;
        tbStyle.over = s.getDrawable("transparent");
        tbStyle.checked = s.getDrawable("transparent");

        s.add("default", tbStyle);
        return s;
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
