package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.variachis.Alpha;

public class GameWinScreen implements Screen {

    private final Alpha alpha;
    private SpriteBatch batch;
    private BitmapFont font;
    private int score;

    private OrthographicCamera camera;


    public GameWinScreen(Alpha alpha, GameScreen gameScreen) {

        this.alpha = alpha;
        this.batch = alpha.batch;
        this.font = alpha.font;
        this.score = gameScreen.getScore();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
