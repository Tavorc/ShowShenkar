package il.ac.shenkar.showshenkar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import il.ac.shenkar.showshenkar.R;

public class MapActivity extends AppCompatActivity {
private ImageView imageViewMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        imageViewMap=(ImageView)findViewById(R.id.imageViewMap);
        imageViewMap.setImageResource(R.drawable.building);

    }
}
