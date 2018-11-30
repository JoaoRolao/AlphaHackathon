package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;

import java.awt.*;

public class GameWinScreen implements Screen {

    private final Alpha alpha;
    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;


    public GameWinScreen(Alpha alpha) {

        this.alpha = alpha;
        this.batch = alpha.batch;
        this.font = alpha.font;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), Color.WHITE.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);camera.update();


        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "You Win!!!!", 100, 150);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.Q))
            Gdx.app.exit();

        if (Gdx.input.isTouched()) {
            alpha.setScreen(alpha.getMainMenuScreen());
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

    }
}
