package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;

import java.awt.*;

public class GameOverScreen implements Screen {

    private final Alpha alpha;
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Sprite backgroundSprite;
    private Music cryingBaby = Gdx.audio.newMusic(Gdx.files.internal("babycrying.wav"));

    private OrthographicCamera camera;

    public GameOverScreen(Alpha game) {

        this.alpha = game;
        this.batch = game.batch;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

        backgroundImage = new Texture("gameover.png");
        backgroundSprite = new Sprite(backgroundImage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), Color.WHITE.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        batch.setProjectionMatrix(camera.combined);

        //cryingBaby.play();


        batch.begin();

        backgroundSprite.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.Q))
            Gdx.app.exit();

        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            //cryingBaby.stop();
            alpha.setScreen(new MainMenuScreen(alpha));
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

        cryingBaby.dispose();
        backgroundImage.dispose();

    }
}
