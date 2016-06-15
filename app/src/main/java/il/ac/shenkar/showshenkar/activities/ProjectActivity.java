package il.ac.shenkar.showshenkar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import il.ac.shenkar.showshenkar.R;

public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        String project = getIntent().getStringExtra("project");
        String student = getIntent().getStringExtra("student");

        Toast.makeText(this, project + " " + student, Toast.LENGTH_LONG).show();
    }
}
