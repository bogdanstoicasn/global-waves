package app.player;

import app.utils.Enums;

public class PlayerStats {
    private final String name;
    private final int remainedTime;
    private String repeat;
    private final boolean shuffle;
    private final boolean paused;

    /**
     * @param name         name of the song
     * @param remainedTime remained time of the song
     * @param repeatMode   repeat mode of the player
     * @param shuffle      shuffle mode of the player
     * @param paused       paused status of the player
     */
    public PlayerStats(final String name, final int remainedTime,
                       final Enums.RepeatMode repeatMode, final boolean shuffle,
                       final boolean paused) {
        this.name = name;
        this.remainedTime = remainedTime;
        this.paused = paused;
        switch (repeatMode) {
            case REPEAT_ALL:
                this.repeat = "Repeat All";
                break;
            case REPEAT_ONCE:
                this.repeat = "Repeat Once";
                break;
            case REPEAT_INFINITE:
                this.repeat = "Repeat Infinite";
                break;
            case REPEAT_CURRENT_SONG:
                this.repeat = "Repeat Current Song";
                break;
            case NO_REPEAT:
                this.repeat = "No Repeat";
                break;
        }
        this.shuffle = shuffle;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return remained time
     */
    public int getRemainedTime() {
        return remainedTime;
    }

    /**
     *
     * @return repeat
     */
    public String getRepeat() {
        return repeat;
    }

    /**
     *
     * @return shuffle
     */
    public boolean isShuffle() {
        return shuffle;
    }

    /**
     *
     * @return paused
     */
    public boolean isPaused() {
        return paused;
    }
}
