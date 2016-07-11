package il.ac.shenkar.showshenkar.activities;

import android.os.Bundle;
import android.widget.ImageView;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.utils.DownloadImageTask;

public class ProjectImageActivity extends ShenkarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_image);

        String imageUrl = getIntent().getStringExtra("url");

        new DownloadImageTask((ImageView)findViewById(R.id.image)).execute(imageUrl);
    }
}
