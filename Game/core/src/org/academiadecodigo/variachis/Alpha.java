package org.academiadecodigo.variachis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import org.academiadecodigo.variachis.screen.MainMenuScreen;

import java.util.Iterator;

public class Alpha extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    private MainMenuScreen mainMenuScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        mainMenuScreen = new MainMenuScreen(this);
        this.setScreen(mainMenuScreen);


    }

    @Override
    public void render() {
    super.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }
}
