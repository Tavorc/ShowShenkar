package il.ac.shenkar.showshenkar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.adapters.RoutesRecyclerAdapter;
import il.ac.shenkar.showshenkar.model.Route;

public class RoutesActivity extends AppCompatActivity {

    private ArrayList<Route> mRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        // Initialize recycler view
        RecyclerView rvProjects = (RecyclerView) findViewById(R.id.routes);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        // TODO: get real routes data
        setDummyRoutes();

        RoutesRecyclerAdapter adapter = new RoutesRecyclerAdapter(this, mRoutes);
        rvProjects.setAdapter(adapter);

        String title = getIntent().getStringExtra("title");
        TextView txtTitle = (TextView) findViewById(R.id.title);
        txtTitle.setText(title);
    }

    private void setDummyRoutes() {
        mRoutes = new ArrayList<>();
        mRoutes.add(new Route("route 1"));
        mRoutes.add(new Route("route 2"));
        mRoutes.add(new Route("route 3"));
        mRoutes.add(new Route("route 4"));
    }
}
