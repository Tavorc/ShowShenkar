package il.ac.shenkar.showshenkar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import il.ac.shenkar.showshenkar.R;


public class VideoActivity extends YouTubeBaseActivity {

    Button playVd;
    private YouTubePlayerView playtube;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        playtube=(YouTubePlayerView) findViewById(R.id.viewVideoTube);
        onInitializedListener=new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("WUZW1EArVL0");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playVd=(Button) findViewById(R.id.buttonPlayVideo);
        playVd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playtube.initialize("AIzaSyCBu6Kh-XTr-XW3D1w8ZzlpFfEBmCotjuk", onInitializedListener);
            }
        });

    }
}
