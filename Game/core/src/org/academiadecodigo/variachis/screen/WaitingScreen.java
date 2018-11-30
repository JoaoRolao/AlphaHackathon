package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;
import org.academiadecodigo.variachis.Constants;

public class WaitingScreen implements Screen {

    private final Alpha alpha;
    private SpriteBatch batch;
    private float timePassed = 0f;
    private boolean isWaiting;
    private float maxTime;

    private OrthographicCamera camera;

    public WaitingScreen(Alpha game, float maxTime) {

        this.maxTime = maxTime;
        this.alpha = game;
        this.batch = game.batch;

        Gdx.graphics.setWindowedMode(1, 1);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1, 1);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        timer();

        if (isWaiting) {
            Gdx.graphics.setWindowedMode(800, 480);
            alpha.setScreen(alpha.getMainMenuScreen());

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
        this.dispose();


    }

    public void timer() {
        timePassed += Gdx.graphics.getRawDeltaTime();
        System.out.println("time passed " + timePassed);
        if (timePassed >= maxTime) {
            isWaiting = true;


        }
    }
}
