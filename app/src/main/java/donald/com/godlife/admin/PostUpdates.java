package donald.com.godlife.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import donald.com.godlife.R;

public class PostUpdates extends AppCompatActivity {
    private TextView announcements;
    private TextView liveUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_updates);


        announcements = findViewById(R.id.tvAnnouncement1);
        liveUpdates = findViewById(R.id.tvLiveUpdates1);

        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postUpdates = new Intent(PostUpdates.this, PostUpdates.class);
                startActivity(postUpdates);
                finish();
            }
        });

        liveUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent liveUpdates = new Intent(PostUpdates.this, LiveUpdates.class);
                startActivity(liveUpdates);
                finish();
            }
        });
    }
}
