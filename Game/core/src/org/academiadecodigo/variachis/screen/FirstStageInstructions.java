package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;
import org.academiadecodigo.variachis.Constants;

import java.awt.*;

public class FirstStageInstructions implements Screen {

    private final Alpha game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture backgroundImage;
    private Sprite backgroundSprite;

    private OrthographicCamera camera;

    public FirstStageInstructions(Alpha game) {

        this.game = game;
        this.batch = game.batch;
        this.font = game.font;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }
    @Override
    public void show() {

        backgroundImage = new Texture("fsInstructions1.png");
        backgroundSprite = new Sprite(backgroundImage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), Color.WHITE.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        backgroundSprite.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            game.setScreen(new FirstStageScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        backgroundImage.dispose();
    }
}
