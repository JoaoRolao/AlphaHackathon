package org.academiadecodigo.variachis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Alpha extends Game {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Sprite backgroundSprite;
    private Texture catcher;
    private Rectangle rCatcher;
    private Rectangle bCatcher;
    private Texture present;
    private Texture sock;
    private Texture baby;
    private Music loop;
    private Long lastDropTime;
    private Long lastDropTime1;
    private OrthographicCamera camera;
    private Array<Rectangle> socks;
    private Array<Rectangle> presents;
    private Sound presentSound;
    private Sound sockSound;
    private String yourScoreName;
    private BitmapFont yourBitmapFontName;
    private int score;

    @Override
    public void create() {
        batch = new SpriteBatch();

        score = 0;
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();

        //path to the background image
        backgroundImage = new Texture("background.jpg");
        backgroundSprite = new Sprite(backgroundImage);
        catcher = new Texture(Gdx.files.internal("freddy.png"));
        sock = new Texture(Gdx.files.internal("sock.png"));
        present = new Texture(Gdx.files.internal("present.png"));
        baby = new Texture(Gdx.files.internal("baby.png"));
        presentSound = Gdx.audio.newSound(Gdx.files.internal("catchPresentSound.wav"));
        sockSound = Gdx.audio.newSound(Gdx.files.internal("catchSockSound.wav"));

        loop = Gdx.audio.newMusic(Gdx.files.internal("loop2.mp3"));

        // start the playback of the background music immediately
        loop.setLooping(true);
        loop.play();

        rCatcher = new Rectangle();
        rCatcher.x = 800 / 2 - 64 / 2;
        rCatcher.y = 20;
        rCatcher.width = 64;
        rCatcher.height = 64;

        bCatcher = new Rectangle();
        bCatcher.x = 800 - 70;
        bCatcher.y = 480 - 70;
        bCatcher.width = 64;
        bCatcher.height = 64;

        socks = new Array<Rectangle>();
        presents = new Array<Rectangle>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        spawnPresents();
        spawnSocks();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        backgroundSprite.draw(batch);
        yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(batch, yourScoreName, 20, 450);
        batch.draw(catcher, rCatcher.x, rCatcher.y);
        batch.draw(baby, bCatcher.x, bCatcher.y);

        for (Rectangle presentDrop : presents) {
            batch.draw(present, presentDrop.x, presentDrop.y);

        }

        for (Rectangle sockDrop : socks) {
            batch.draw(sock, sockDrop.x, sockDrop.y);

        }

        batch.end();

        camera.update();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) rCatcher.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rCatcher.x += 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            rCatcher.x = touchPos.x - 64 / 2;
        }

        if (rCatcher.x < 0) rCatcher.x = 0;
        if (rCatcher.x > 800 - 64) rCatcher.x = 800 - 64;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnPresents();

        for (Iterator<Rectangle> iter = presents.iterator(); iter.hasNext(); ) {
            Rectangle rPresents = iter.next();
            rPresents.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rPresents.y + 64 < 0) iter.remove();

            if (rPresents.overlaps(rCatcher)) {
                score++;
                yourScoreName = "score: " + score + " /20" ;
                presentSound.setVolume(1, -50);
                presentSound.play();
                iter.remove();
            }
        }


        if (TimeUtils.nanoTime() - lastDropTime1 > 1000000000) spawnSocks();

        for (Iterator<Rectangle> iter = socks.iterator(); iter.hasNext(); ) {
            Rectangle rSocks = iter.next();
            rSocks.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rSocks.y + 64 < 0) iter.remove();

            if (rSocks.overlaps(rCatcher)) {
                score--;
                yourScoreName = "score: " + score + " /20"  ;
                sockSound.play();
                iter.remove();
            }
        }



    }

    @Override
    public void dispose() {
        batch.dispose();
        catcher.dispose();
        backgroundImage.dispose();
        present.dispose();
        baby.dispose();
        loop.dispose();
        sock.dispose();
    }

    private void spawnPresents() {

        Rectangle present = new Rectangle();
        present.x = MathUtils.random(0, 800 - 64);
        present.y = 480;
        present.width = 64;
        present.height = 64;
        presents.add(present);
        lastDropTime = TimeUtils.nanoTime();

    }

    private void spawnSocks() {

        Rectangle sock = new Rectangle();
        sock.x = MathUtils.random(0, 800 - 64);
        sock.y = 480;
        sock.width = 64;
        sock.height = 64;
        socks.add(sock);
        lastDropTime1 = TimeUtils.nanoTime();

    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getYourBitmapFontName() {
        return yourBitmapFontName;
    }
}
