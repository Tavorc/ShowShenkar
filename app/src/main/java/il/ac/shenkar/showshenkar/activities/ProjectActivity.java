package il.ac.shenkar.showshenkar.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import il.ac.shenkar.showshenkar.R;

//import com.google.android.youtube.player.YouTubeBaseActivity;

public class ProjectActivity extends AppCompatActivity {

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
    private AlertDialog.Builder dialog;
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
        dialog=new AlertDialog.Builder(this);
        playVd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(ProjectActivity.this, YouTubeActivity.class);
                startActivity(i);
            }
        });

    }


    public void shareProject(View v)
    {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_camera)
        {
            // TODO: implement camera QR logic
            Toast.makeText(this, R.string.camera_btn, Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
