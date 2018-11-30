package org.academiadecodigo.variachis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public static final Music underPressure = Gdx.audio.newMusic(Gdx.files.internal("under-pressure.mp3"));
    public static final Sound presentSound = Gdx.audio.newSound(Gdx.files.internal("catchPresentSound.wav"));
    public static final Sound sockSound = Gdx.audio.newSound(Gdx.files.internal("holy-shit.wav"));
    public static final Music firstStage = Gdx.audio.newMusic(Gdx.files.internal("loop.mp3"));
    public static final Sound fanGirlMusic = Gdx.audio.newSound(Gdx.files.internal("groupieSound.wav"));
    public static final Sound beerMusic = Gdx.audio.newSound(Gdx.files.internal("beerSound.wav"));
    public static final Music secondStage = Gdx.audio.newMusic(Gdx.files.internal("darude.mp3"));
    public static final Music barrel = Gdx.audio.newMusic(Gdx.files.internal("barrelColision.mp3"));
    public static final Music thirdStage = Gdx.audio.newMusic(Gdx.files.internal("MusicLVL3.mp3"));
}
