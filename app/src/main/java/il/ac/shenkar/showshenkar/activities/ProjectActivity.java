package il.ac.shenkar.showshenkar.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import il.ac.shenkar.showshenkar.R;

public class ProjectActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener{
    public static final String API_KEY = "AIzaSyCBu6Kh-XTr-XW3D1w8ZzlpFfEBmCotjuk";
    Button playVd;
    private YouTubePlayerView playtube;
    private YouTubePlayer youTubeP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        String project = getIntent().getStringExtra("project");
        String student = getIntent().getStringExtra("student");
        Toast.makeText(this, project + " " + student, Toast.LENGTH_LONG).show();
        playtube=(YouTubePlayerView) findViewById(R.id.viewVideo);
        playtube.setVisibility(View.GONE);
        playVd=(Button) findViewById(R.id.buttonVideo);
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
}
