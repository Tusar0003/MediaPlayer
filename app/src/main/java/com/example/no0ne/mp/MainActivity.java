package com.example.no0ne.mp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int seek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.the_wind_by_andy_james);

        Button playButton = (Button) findViewById(R.id.play);
        Button pauseButton = (Button) findViewById(R.id.pause);
        Button forwardButton = (Button) findViewById(R.id.forward);
        Button backwardButton = (Button) findViewById(R.id.backward);
        final CheckBox loopCheckBox = (CheckBox) findViewById(R.id.loop);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                displaySongTitle();
                displaySongArtist();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(MainActivity.this, "I'm done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        loopCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loopCheckBox.isChecked()) {
                    mediaPlayer.setLooping(true);
                } else {
                    mediaPlayer.setLooping(false);
                }
            }
        });



        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seek != mediaPlayer.getDuration()) {
                    seek = seek + 5000;
                    mediaPlayer.seekTo(seek);
                } else {
                    mediaPlayer.start();
                }
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seek != 0) {
                    seek = seek - 5000;
                    mediaPlayer.seekTo(seek);
                } else {
                    seek = 0;
                    mediaPlayer.seekTo(seek);
                }
            }
        });
    }

    public void displaySongTitle() {
        TextView songTitle = (TextView) findViewById(R.id.song_title);
        songTitle.setText("The wind that shakes the heart");
    }

    public void displaySongArtist() {
        TextView songArtist = (TextView) findViewById(R.id.song_artist);
        songArtist.setText("By - Andy James");
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
