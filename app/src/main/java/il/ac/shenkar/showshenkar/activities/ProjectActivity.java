package il.ac.shenkar.showshenkar.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;

import il.ac.shenkar.showshenkar.R;

public class ProjectActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener{
    public static final String API_KEY = "AIzaSyCBu6Kh-XTr-XW3D1w8ZzlpFfEBmCotjuk";
    Button playVd;
    Button playSD;
    private YouTubePlayerView playtube;
    private YouTubePlayer youTubeP;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        String project = getIntent().getStringExtra("project");
        String student = getIntent().getStringExtra("student");
        Toast.makeText(this, project + " " + student, Toast.LENGTH_LONG).show();
        playtube=(YouTubePlayerView) findViewById(R.id.viewVideo);
        playtube.setVisibility(View.GONE);
        mediaPlayer = new MediaPlayer();
        playVd=(Button) findViewById(R.id.buttonVideo);
        playSD=(Button) findViewById(R.id.buttonSoundM);
        dialog=new AlertDialog.Builder(this);
        playtube.initialize(API_KEY, this);
        playVd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubeP.setFullscreen(true);
            }
        });

    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubeP=youTubePlayer;
        youTubePlayer.cueVideo("JRfuAukYTKg");
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void playSound(View v)
    {
        dialog.setTitle("האזן");
        dialog.setPositiveButton("נגן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "מנגן", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "מנגן",Toast.LENGTH_SHORT).show();
                String url="http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                }
                mediaPlayer.start();

            }
        });
        dialog.setNegativeButton("הפסק", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "הפסיק", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();

            }
        });
        dialog.show();


    }
}
