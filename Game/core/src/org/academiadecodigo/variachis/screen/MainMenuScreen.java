package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;
import org.academiadecodigo.variachis.Constants;
import org.academiadecodigo.variachis.Sounds;

import java.awt.*;

public class MainMenuScreen implements Screen {

    private final Alpha game;
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Sprite backgroundSprite;
    private OrthographicCamera camera;

    public MainMenuScreen(Alpha game) {

        this.game = game;
        this.batch = game.batch;


        Gdx.graphics.setWindowedMode(800, 480);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }


    @Override
    public void show() {

        backgroundImage = new Texture("mainMenuScreen.png");
        backgroundSprite = new Sprite(backgroundImage);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), Color.WHITE.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        Sounds.underPressure.setLooping(true);
        Sounds.underPressure.play();


        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        backgroundSprite.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            Sounds.underPressure.stop();
            // game.setScreen(new FirstStageInstructions(game));

            game.setScreen(new ThirdStageScreen(game));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            Sounds.underPressure.stop();
            game.setScreen(new WaitingScreen(game, 15));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            Sounds.underPressure.stop();
            game.setScreen(new WaitingScreen(game, 900));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            Sounds.underPressure.stop();
            game.setScreen(new WaitingScreen(game, 1800));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            Sounds.underPressure.stop();
            game.setScreen(new WaitingScreen(game, 3600));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
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
        Sounds.underPressure.dispose();


    }
}
