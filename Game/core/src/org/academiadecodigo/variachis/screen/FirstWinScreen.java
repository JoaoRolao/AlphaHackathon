package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;

public class FirstWinScreen implements Screen {

    private final Alpha alpha;
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Sprite backgroundSprite;
    private SecondStageScreen secondStageScreen;

    private OrthographicCamera camera;


    public FirstWinScreen(Alpha alpha) {

        this.alpha = alpha;
        this.batch = alpha.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        secondStageScreen = new SecondStageScreen(alpha);

    }

    @Override
    public void show() {
        backgroundImage = new Texture("instructions22.png");
        backgroundSprite = new Sprite(backgroundImage);

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            alpha.setScreen(alpha.getMainMenuScreen());
            dispose();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            alpha.setScreen(secondStageScreen);
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
