package donald.com.godlife.live_streaming;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import donald.com.godlife.R;
import donald.com.godlife.util.DeveloperKey;

public class LiveTv extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayer player;
    private Button fullScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);


        YouTubePlayerView playerView = (YouTubePlayerView)findViewById(R.id.youTubePlayerView);
        playerView.initialize(DeveloperKey.DEVELOPER_KEY,this);

        fullScreen = (Button)findViewById(R.id.button2);

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setFullscreen(true);
            }
        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {

        this.player = player;

        if(!b){

            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            // You can change the ID.. of the video to be played
            player.loadVideo("UhNX5NdJCs4");



        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
