package org.ryuu.gdx.audio;

import com.badlogic.gdx.audio.Sound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
public class SoundExecutor {
    private final Executor executor;

    public SoundWrapper play(Sound sound) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.play();
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    public SoundWrapper play(Sound sound, float volume) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.play(volume);
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    public SoundWrapper play(Sound sound, float volume, float pitch, float pan) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.play(volume, pitch, pan);
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    public SoundWrapper loop(Sound sound) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.loop();
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    public SoundWrapper loop(Sound sound, float volume) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.loop(volume);
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    public SoundWrapper loop(Sound sound, float volume, float pitch, float pan) {
        SoundWrapper soundWrapper = new SoundWrapper();
        soundWrapper.sound = sound;
        executor.execute(() -> {
            long soundId = sound.loop(volume, pitch, pan);
            soundWrapper.soundId.set(soundId);
        });
        return soundWrapper;
    }

    @Getter
    @ToString
    public static class SoundWrapper {
        private final AtomicLong soundId = new AtomicLong();

        private volatile Sound sound;
    }
}