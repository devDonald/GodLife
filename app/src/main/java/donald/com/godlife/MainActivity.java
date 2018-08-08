package donald.com.godlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import donald.com.godlife.admin.AdminLogin;
import donald.com.godlife.live_streaming.LiveRadio;
import donald.com.godlife.live_streaming.LiveTv;
import donald.com.godlife.model.LiveUpdateModel;

public class MainActivity extends AppCompatActivity {
    private BoomMenuButton boomMenu;
    private TextView announcements;
    private TextView liveUpdates;
    private RecyclerView allPost_RV;
    private DatabaseReference allLiveUpdates;
    private Context context;

    private FirebaseRecyclerAdapter<LiveUpdateModel,LiveUpdatesViewHolder> firebaseRecyclerAdapter;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.live_radio:
                    Intent liveRadio = new Intent(MainActivity.this, LiveRadio.class);
                    startActivity(liveRadio);
                    return true;
                case R.id.live_streaming:
                    Intent livevideo = new Intent(MainActivity.this, LiveTv.class);
                    startActivity(livevideo);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        allPost_RV=(RecyclerView)findViewById(R.id.recycler);
        allLiveUpdates= FirebaseDatabase.getInstance().getReference().child("LiveUpdates");
        allPost_RV.setHasFixedSize(true);
        allPost_RV.setLayoutManager(new LinearLayoutManager(context));

        announcements = findViewById(R.id.tvAnnouncement3);
        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent announce = new Intent(MainActivity.this,Announcements.class);
                startActivity(announce);
                finish();
            }
        });

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        boomMenu = (BoomMenuButton)findViewById(R.id.bmb);

        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder();
        builder.normalImageRes(R.drawable.ic_location_on).normalText("Locate Church");
        builder.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent location = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(location);
            }
        });
        boomMenu.addBuilder(builder);
        TextOutsideCircleButton.Builder builder1 = new TextOutsideCircleButton.Builder();
        builder1.normalImageRes(R.drawable.ic_comment).normalText("Comments");
        boomMenu.addBuilder(builder1);
        TextOutsideCircleButton.Builder builder2 = new TextOutsideCircleButton.Builder();
        builder2.normalImageRes(R.drawable.ic_music).normalText("Music");
        boomMenu.addBuilder(builder2);
        TextOutsideCircleButton.Builder builder3 = new TextOutsideCircleButton.Builder();
        builder3.normalImageRes(R.drawable.ic_messages).normalText("Messages");
        boomMenu.addBuilder(builder3);
        TextOutsideCircleButton.Builder builder4 = new TextOutsideCircleButton.Builder();
        builder4.normalImageRes(R.drawable.ic_event).normalText("Events");
        boomMenu.addBuilder(builder4);
        TextOutsideCircleButton.Builder builder5 = new TextOutsideCircleButton.Builder();
        builder5.normalImageRes(R.drawable.ic_payment).normalText("Givings");
        boomMenu.addBuilder(builder5);
        TextOutsideCircleButton.Builder builder6 = new TextOutsideCircleButton.Builder();
        builder6.normalImageRes(R.drawable.ic_contact_us).normalText("Contact Us");
        builder6.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent contact_us = new Intent(MainActivity.this, ContactUs.class);
                startActivity(contact_us);
            }
        });
        boomMenu.addBuilder(builder6);
        TextOutsideCircleButton.Builder builder7 = new TextOutsideCircleButton.Builder();
        builder7.normalImageRes(R.drawable.ic_share).normalText("Share");

        boomMenu.addBuilder(builder7);

        TextOutsideCircleButton.Builder builder8 = new TextOutsideCircleButton.Builder();
        builder8.normalImageRes(R.drawable.ic_admin).normalText("Admin Login");
        builder8.listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent admin = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(admin);
            }
        });
        boomMenu.addBuilder(builder8);




    }

    public void displayUpdates(){

            firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<LiveUpdateModel, LiveUpdatesViewHolder>(
                    LiveUpdateModel.class,
                    R.layout.layout_live_updates,
                    LiveUpdatesViewHolder.class,
                    allLiveUpdates
            ) {

                @Override
                protected void populateViewHolder(LiveUpdatesViewHolder viewHolder, LiveUpdateModel model, int position) {
                    viewHolder.setProjectbody(model.getPost());
                    viewHolder.setPostDate(model.getPostDate());

                }

                @Override
                public LiveUpdatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    LiveUpdatesViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                    return viewHolder;
                }
            };
            allPost_RV.setAdapter(firebaseRecyclerAdapter);
        }

    @Override
    protected void onStart() {
        super.onStart();
        displayUpdates();
    }
}
