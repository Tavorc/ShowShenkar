package shenkar.ac.il.showshenkar.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import shenkar.ac.il.showshenkar.R;
import shenkar.ac.il.showshenkar.model.DepProject;
import shenkar.ac.il.showshenkar.adapters.DepProjectsRecyclerAdapter;

public class DepartmentActivity extends AppCompatActivity {
    private List<DepProject> mProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        String title = getIntent().getStringExtra("title");
        int imageResource = getIntent().getIntExtra("image", 0);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageResource);
        imageView.setImageBitmap(bitmap);

        // Initialize recycler view
        RecyclerView rvProjects = (RecyclerView) findViewById(R.id.projects);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        setDummyProject();

        DepProjectsRecyclerAdapter adapter = new DepProjectsRecyclerAdapter(this, mProjects);
        rvProjects.setAdapter(adapter);
    }

    private void setDummyProject() {
        mProjects = new ArrayList<>();
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
    }


}
