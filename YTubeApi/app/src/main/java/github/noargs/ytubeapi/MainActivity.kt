package github.noargs.ytubeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import github.noargs.ytubeapi.databinding.ActivityMainBinding

class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnYoutube.setOnClickListener {

            val ytPlayer = YouTubePlayerView(this)
            ytPlayer.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            binding.contentContainer.addView(ytPlayer)

            ytPlayer.initialize("YOUTUBE_API_KEYS_GOES_HERE", this)

        }
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        restored: Boolean
    ) {
        youtubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youtubePlayer?.setPlaybackEventListener(playbackEventListener)

        if(!restored)
        {
            youtubePlayer?.cueVideo("oB14FKhqDwA")
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this, "Error happened", Toast.LENGTH_SHORT).show()
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener
    {
        override fun onPlaying() {
        }

        override fun onPaused() {
            Toast.makeText(this@MainActivity, "paused", Toast.LENGTH_SHORT)
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
        }

        override fun onVideoStarted() {
        }

        override fun onVideoEnded() {
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }

    }

}