package il.ac.shenkar.showshenkar.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.adapters.DepProjectsRecyclerAdapter;
import il.ac.shenkar.showshenkar.backend.projectApi.model.Project;

public class MyRouteActivity extends ShenkarActivity {
    private List<Project> mProjects;

    private DepProjectsRecyclerAdapter adapter;

    public static void addProjectId(Context context, Long projectId) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> projectIdsStr = sharedPref.getStringSet(context.getString(R.string.preference_ids_key), new HashSet<String>());
        projectIdsStr.add(Long.toString(projectId));

        editor.putStringSet(context.getString(R.string.preference_ids_key), projectIdsStr);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_route);


        // Initialize recycler view
        RecyclerView rvProjects = (RecyclerView) findViewById(R.id.projects);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        mProjects = new ArrayList<>();
        adapter = new DepProjectsRecyclerAdapter(this, mProjects);
        rvProjects.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.clear();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Set<String> projectIdsStr = sharedPref.getStringSet(getString(R.string.preference_ids_key), new HashSet<String>());
        for (String projectId : projectIdsStr) {
            adapter.refresh(Long.parseLong(projectId));
        }
    }
}