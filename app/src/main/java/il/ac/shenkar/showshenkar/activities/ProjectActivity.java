package il.ac.shenkar.showshenkar.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.model.DBHelper;

//import com.google.android.youtube.player.YouTubeBaseActivity;

public class ProjectActivity extends ShenkarActivity {

    final Context context = this;
    static class ProjectViewHolder {
        TextView txtProjectName;
        TextView txtStudentName;
        TextView txtLocation;
        TextView txtProjectDesc;
        ImageView imgScreenShot;

        public ProjectViewHolder(Activity activity)
        {
            txtProjectName = (TextView) activity.findViewById(R.id.txtProjectName);
            txtStudentName = (TextView) activity.findViewById(R.id.txtStudentName);
            txtLocation = (TextView) activity.findViewById(R.id.txtLocation);
            txtProjectDesc = (TextView) activity.findViewById(R.id.txtProjectDesc);
            imgScreenShot = (ImageView) activity.findViewById(R.id.imgScreenShot);
        }
    }


    Button playVd;
    Button playSD;

    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private ProjectViewHolder views;
    private Long id;
    private String project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        // initialize all the project's views
        views = new ProjectViewHolder(this);
        project = getIntent().getStringExtra("project");
        views.txtProjectName.setText(project);
        String student = getIntent().getStringExtra("student");
        views.txtStudentName.setText(student);
        id = getIntent().getLongExtra("id",0);
        //test
        //Toast.makeText(this, id.toString() , Toast.LENGTH_LONG).show();
        mediaPlayer = new MediaPlayer();
        playVd=(Button) findViewById(R.id.buttonVideo);
        playSD=(Button) findViewById(R.id.buttonSoundM);
        playVd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(ProjectActivity.this, YouTubeActivity.class);
                startActivity(i);
            }
        });
        playSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogT = new Dialog(context);
                dialogT.setContentView(R.layout.custom);
                ImageButton dialogButtonPlay = (ImageButton) dialogT.findViewById(R.id.imageButtonPlay);
                // if button is clicked, close the custom dialog
                dialogButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IllegalArgumentException e) {

                        } catch (SecurityException e) {

                        } catch (IllegalStateException e) {

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            mediaPlayer.prepare();
                        } catch (IllegalStateException e) {

                        } catch (IOException e) {

                        }
                        mediaPlayer.start();
                    }
                });

                ImageButton dialogButtonStop = (ImageButton) dialogT.findViewById(R.id.imageButtonStop);
                // if button is clicked, close the custom dialog
                dialogButtonStop.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mediaPlayer.stop();
                    }
                });

                dialogT.show();
            }
        });
    }
    public void shareProject(View v)
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri screenshotUri = Uri.parse("android.resource://il.ac.shenkar.showshenkar.activities/*");
        try {
            InputStream stream = getContentResolver().openInputStream(screenshotUri);
        }

        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

      sharingIntent.setType("image/jpeg");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
        // TODO: implement share project
        Toast.makeText(this, "שתף פרויקט", Toast.LENGTH_LONG).show();
    }


    public void showLocation(View v)
    {
        Intent toProjectLocationMap = new Intent(this, ProjectLocationMap.class);
        toProjectLocationMap.putExtra("name", project);
        toProjectLocationMap.putExtra("id", id);
        //toProjectLocationMap.putExtra("LL", new LatLng(0.0,0.0));
        startActivity(toProjectLocationMap);
    }
}
