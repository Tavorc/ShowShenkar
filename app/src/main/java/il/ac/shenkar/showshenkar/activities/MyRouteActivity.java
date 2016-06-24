package il.ac.shenkar.showshenkar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.model.DepProject;

public class MyRouteActivity extends AppCompatActivity {
    private List<DepProject> mProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_route);


        // Initialize recycler view
        RecyclerView rvProjects = (RecyclerView) findViewById(R.id.projects);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        setDummyProject();

        //DepProjectsRecyclerAdapter adapter = new DepProjectsRecyclerAdapter(this, mProjects);
        //rvProjects.setAdapter(adapter);
    }

    private void setDummyProject() {
        mProjects = new ArrayList<>();
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
        mProjects.add(new DepProject("project 1", "student 1"));
    }
}
