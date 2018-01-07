package com.shixzh.java.interview.interfaces;

import java.applet.AudioClip;

public interface Singer {
    AudioClip sing(Song s);
}
interface Songwriter {
    Song compose(boolean hit);
}
interface SingerSongwriter extends Singer, Songwriter {
    AudioClip strum();
    void actSensitive();
}
class Song{
    
}