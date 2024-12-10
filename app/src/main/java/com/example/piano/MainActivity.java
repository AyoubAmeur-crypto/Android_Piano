package com.example.piano;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;

import android.os.Bundle;

import android.widget.Button;


import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    // MediaPlayer instance to play sound
    private List<Button> pianoKeys;
    private MediaPlayer mediaPlayer;
    private List<Integer> soundResources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button a = findViewById(R.id.btnA);
        Button b = findViewById(R.id.btnB);
        Button c = findViewById(R.id.btnC);
        Button d = findViewById(R.id.btnD);
        Button e = findViewById(R.id.btnE);
        Button f = findViewById(R.id.btnF);
        Button g = findViewById(R.id.btnG);


        // Create a List of buttons
        pianoKeys = Arrays.asList(a, b, c, d, e, f, g);

        // Create a List of corresponding sound resources
        soundResources = Arrays.asList(
                R.raw.a4,
                R.raw.b4,
                R.raw.c4,
                R.raw.d4,
                R.raw.e4,
                R.raw.f4,
                R.raw.g4
        );

        // Set OnClickListeners for each button dynamically
        for (int i = 0; i < pianoKeys.size(); i++) {
            final int soundResource = soundResources.get(i);
            pianoKeys.get(i).setOnClickListener(view -> playSound(soundResource));
        }
    }

    // Method to play the sound for the corresponding note
    private void playSound(int soundResource) {
        // Stop the current sound if it's playing
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // Initialize the MediaPlayer to play the new sound
        mediaPlayer = MediaPlayer.create(this, soundResource);
        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();  // Release the MediaPlayer when sound finishes
        });
        mediaPlayer.start();  // Play the sound
    }
}