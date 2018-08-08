package donald.com.godlife.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.DateFormat;
import java.util.Date;

import donald.com.godlife.R;

public class LiveUpdates extends AppCompatActivity {
    private TextView announcements;
    private TextView liveUpdates;
    private EditText uliveServicePost;
    private Button uSubmitPost;
    private DatabaseReference liveDB;
    private String postDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_updates);

        announcements = findViewById(R.id.tvAnnouncement);
        liveUpdates = findViewById(R.id.tvLiveUpdates);
        uliveServicePost = findViewById(R.id.et_live_service);
        uSubmitPost = findViewById(R.id.btnLiveService);
        postDate = DateFormat.getDateTimeInstance().format(new Date());

        liveDB = FirebaseDatabase.getInstance().getReference().child("LiveUpdates");
        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postUpdates = new Intent(LiveUpdates.this, PostUpdates.class);
                startActivity(postUpdates);
                finish();
            }
        });

        liveUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent liveUpdates = new Intent(LiveUpdates.this, LiveUpdates.class);
                startActivity(liveUpdates);
                finish();
            }
        });

        uSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String livePost = uliveServicePost.getText().toString().trim();

                if (livePost.isEmpty()){
                    MDToast.makeText(getApplicationContext(),"post is empty",
                            MDToast.TYPE_ERROR,MDToast.LENGTH_LONG);
                } else {
                    String id = liveDB.push().getKey();
                    liveDB.child(id).child("post").setValue(livePost);
                    liveDB.child(id).child("postDate").setValue(postDate);

                    MDToast.makeText(getApplicationContext(),"post successful",
                            MDToast.TYPE_SUCCESS,MDToast.LENGTH_LONG);
                    uliveServicePost.setText("");
                }
            }
        });
    }
}
