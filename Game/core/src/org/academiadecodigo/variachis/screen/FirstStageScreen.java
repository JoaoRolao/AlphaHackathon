package org.academiadecodigo.variachis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import org.academiadecodigo.variachis.Alpha;
import org.academiadecodigo.variachis.Constants;
import org.academiadecodigo.variachis.Sounds;

import java.util.Iterator;

public class FirstStageScreen implements Screen {


    private SpriteBatch batch;
    private Texture backgroundImage;
    private Sprite backgroundSprite;
    private Texture catcher;
    private Rectangle rCatcher;
    private Rectangle bCatcher;
    private Texture present;
    private Texture sock;
    private Texture baby;
    private Long lastDropTime;
    private Long lastDropTime1;
    private OrthographicCamera camera;
    private Array<Rectangle> socks;
    private Array<Rectangle> presents;
    private String yourScoreName;
    private BitmapFont font;
    private BitmapFont fontPopUp;
    private int score;
    private ShapeRenderer shapeRenderer;
    private float timeSeconds = 0f;
    private float currentTime = 0f;
    private boolean isOver = false;
    private Alpha alpha;
    private GameOverScreen gameOverScreen;
    private FirstWinScreen firstWinScreen;


    public FirstStageScreen(Alpha alpha) {
        this.alpha = alpha;
        this.batch = alpha.batch;
        this.font = alpha.font;

        fontPopUp = new BitmapFont();

        backgroundImage = new Texture("background.jpg");
        backgroundSprite = new Sprite(backgroundImage);
        catcher = new Texture(Gdx.files.internal("freddy.png"));
        sock = new Texture(Gdx.files.internal("poop.png"));
        present = new Texture(Gdx.files.internal("present.png"));
        baby = new Texture(Gdx.files.internal("baby.png"));

    }

    @Override
    public void show() {


        score = 0;
        yourScoreName = "score: 0/20";
        font = new BitmapFont();

        // start the playback of the background music immediately

        Sounds.firstStage.setLooping(true);
        Sounds.firstStage.play();

        rCatcher = new Rectangle();
        rCatcher.x = Constants.CATCHER_X;
        rCatcher.y = Constants.CATCHER_Y;
        rCatcher.width = Constants.CATCHER_WIDTH;
        rCatcher.height = Constants.CATCHER_HEIGHT;

        bCatcher = new Rectangle();
        bCatcher.x = Constants.BABY_X;
        bCatcher.y = Constants.BABY_Y;
        bCatcher.width = Constants.BABY_WIDTH;
        bCatcher.height = Constants.BABY_HEIGHT;

        socks = new Array<Rectangle>();
        presents = new Array<Rectangle>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        shapeRenderer = new ShapeRenderer();

        gameOverScreen = new GameOverScreen(alpha);
        firstWinScreen = new FirstWinScreen(alpha);

        spawnPresents();
        spawnSocks();


    }


    @Override
    public void render(float delta) {

        String popUp = "YOU HAVE 30 SECONDS";



        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.setProjectionMatrix(camera.combined);
        backgroundSprite.draw(batch);
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        font.draw(batch, yourScoreName, Constants.SCORE_X, Constants.SCORE_Y);
        batch.draw(catcher, rCatcher.x, rCatcher.y);

        batch.end();

        if (isOver) {
            Sounds.firstStage.stop();
            alpha.setScreen(gameOverScreen);
        }

        timer();

        batch.begin();

        batch.draw(baby, bCatcher.x, bCatcher.y);

        for (Rectangle presentDrop : presents) {
            batch.draw(present, presentDrop.x, presentDrop.y);

        }

        for (Rectangle sockDrop : socks) {
            batch.draw(sock, sockDrop.x, sockDrop.y);

        }

        batch.end();

        camera.update();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) rCatcher.x -= Constants.PLAYER_SPEED * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rCatcher.x += Constants.PLAYER_SPEED * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            rCatcher.x = touchPos.x - 64 / 2;
        }

        if (rCatcher.x < 0) rCatcher.x = 0;
        if (rCatcher.x > 800 - 64) rCatcher.x = 800 - 64;

        if (TimeUtils.nanoTime() - lastDropTime > Constants.DROP_TIME_PRESENTS) spawnPresents();

        for (Iterator<Rectangle> iter = presents.iterator(); iter.hasNext(); ) {
            Rectangle rPresents = iter.next();
            rPresents.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rPresents.y + 64 < 0) iter.remove();

            if (rPresents.overlaps(rCatcher)) {
                score++;
                yourScoreName = "score: " + score + "/20";
                Sounds.presentSound.play();
                iter.remove();
            }

            if (score == 20) {
                Sounds.firstStage.stop();
                alpha.setScreen(firstWinScreen);
            }

            if (score == -1) {
                Sounds.firstStage.stop();
                alpha.setScreen(gameOverScreen);
            }
        }


        if (TimeUtils.nanoTime() - lastDropTime1 > Constants.DROP_TIME_SOCKS) spawnSocks();

        for (Iterator<Rectangle> iter = socks.iterator(); iter.hasNext(); ) {
            Rectangle rSocks = iter.next();
            rSocks.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rSocks.y + 64 < 0) iter.remove();

            if (rSocks.overlaps(rCatcher)) {
                score--;
                yourScoreName = "score: " + score + "/20";
                Sounds.sockSound.setVolume(1, 50);
                Sounds.sockSound.play();
                iter.remove();
            }
        }

        if (currentTime < -28) {
            batch.begin();
            fontPopUp.setColor(Color.BLACK);
            fontPopUp.getData().setScale(3);
            fontPopUp.draw(batch, popUp, 150, 240);
            batch.end();
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
        batch.dispose();
        catcher.dispose();
        backgroundImage.dispose();
        present.dispose();
        baby.dispose();
        Sounds.firstStage.dispose();
        sock.dispose();
        Sounds.presentSound.dispose();
        Sounds.sockSound.dispose();

    }


    public void timer() {


        timeSeconds += Gdx.graphics.getRawDeltaTime();


        currentTime = Constants.MAX_TIME + timeSeconds;

        float barWidth = currentTime * 25;

        shapeRenderer.setProjectionMatrix(camera.combined);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(Constants.BAR_X, Constants.BAR_Y, barWidth, Constants.BAR_HEIGHT);
        shapeRenderer.setColor(Color.WHITE);

        if ((int) barWidth % 2 == 0) {
            shapeRenderer.setColor(Color.RED);
        }

        if (barWidth >= 0) {
            isOver = true;


        }


        shapeRenderer.end();

    }

    private void spawnPresents() {

        Rectangle present = new Rectangle();
        present.x = MathUtils.random(0, Constants.PRESENT_SPAWN_X);
        present.y = Constants.PRESENT_SPAWN_Y;
        present.width = Constants.PRESENT_WIDTH;
        present.height = Constants.PRESENT_HEIGHT;
        presents.add(present);
        lastDropTime = TimeUtils.nanoTime();

    }

    private void spawnSocks() {

        Rectangle sock = new Rectangle();
        sock.x = MathUtils.random(0, Constants.SOCKS_SPAWN_X);
        sock.y = Constants.SOCKS_SPAWN_Y;
        sock.width = Constants.SOCKS_WIDTH;
        sock.height = Constants.SOCKS_HEIGHT;
        socks.add(sock);
        lastDropTime1 = TimeUtils.nanoTime();

    }

}
