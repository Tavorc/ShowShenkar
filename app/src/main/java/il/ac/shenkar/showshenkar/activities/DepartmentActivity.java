package il.ac.shenkar.showshenkar.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.adapters.DepProjectsRecyclerAdapter;
import il.ac.shenkar.showshenkar.backend.departmentApi.model.Department;
import il.ac.shenkar.showshenkar.backend.projectApi.model.Project;
import il.ac.shenkar.showshenkar.utils.BitmapDownloader;


public class DepartmentActivity extends ShenkarActivity {
    private List<Project> mProjects;
    private Department mDepartment;
    private Long mDepartmentId;
    private String mDepartmentName;
    private DepProjectsRecyclerAdapter adapter;
    private Button buttonWhere;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        mDepartmentId = getIntent().getLongExtra("id", 0);
        mDepartmentName = getIntent().getStringExtra("title");
        buttonWhere=(Button) findViewById(R.id.btnLocation);
        final String imageUrl = getIntent().getStringExtra("image");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(mDepartmentName);

        final ImageView imageView = (ImageView) findViewById(R.id.image);
        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                return BitmapDownloader.getBitmapFromURL(imageUrl);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                //show complition in UI
                //fill grid view with data
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }.execute();

        // Initialize recycler view
        RecyclerView rvProjects = (RecyclerView) findViewById(R.id.projects);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        mProjects = new ArrayList<>();

        adapter = new DepProjectsRecyclerAdapter(this, mDepartmentName, mProjects);
        rvProjects.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.refresh();
    }

    private void setDummyProject() {
        //mProjects = new ArrayList<>();
        //mProjects.add(new DepProject("project 1", "student 1"));
        //mProjects.add(new DepProject("project 1", "student 1"));
        //mProjects.add(new DepProject("project 1", "student 1"));
        //mProjects.add(new DepProject("project 1", "student 1"));
    }

    public void showDepartmentLocation(View v) {
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);



        // TODO: show department location
    }

}
