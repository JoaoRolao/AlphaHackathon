package org.academiadecodigo.variachis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture present;
    private Texture sock;
    private Long lastDropTime;
    private OrthographicCamera camera;
    private Array<Rectangle> socks;
    private Array<Rectangle> presents;


    @Override
    public void create() {
        batch = new SpriteBatch();

        //path to the background image
        backgroundImage = new Texture("badlogic.jpg");
        backgroundSprite = new Sprite(backgroundImage);
        catcher = new Texture(Gdx.files.internal("freddy.png"));
        sock = new Texture(Gdx.files.internal("sock.png"));
        present = new Texture(Gdx.files.internal("present.png"));

        rCatcher = new Rectangle();
        rCatcher.x = 800 / 2 - 64 / 2;
        rCatcher.y = 20;
        rCatcher.width = 64;
        rCatcher.height = 64;

        socks = new Array<Rectangle>();
        presents = new Array<Rectangle>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        spawnPresents();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(catcher, rCatcher.x, rCatcher.y);

        for (Rectangle raindrop : presents) {
            batch.draw(present, raindrop.x, raindrop.y);

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
}
