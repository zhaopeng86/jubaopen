package com.minigame.info.listvideo.video_player_manager.player_messages;

/**
 * This generic interface for messages
 */
public interface Message {
    void runMessage();
    void polledFromQueue();
    void messageFinished();
}
