package com.example.car.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.media.MediaBrowserServiceCompat
import java.util.*


/**
 * This class provides a MediaBrowser through a service. It exposes the media library to a browsing
 * client, through the onGetRoot and onLoadChildren methods. It also creates a MediaSession and
 * exposes it through its MediaSession.Token, which allows the client to create a MediaController
 * that connects to and send control commands to the MediaSession remotely. This is useful for
 * user interfaces that need to interact with your media session, like Android Auto. You can
 * (should) also use the same service from your app"s UI, which gives a seamless playback
 * experience to the user.
 *
 *
 * To implement a MediaBrowserService, you need to:
 *
 *  *  Extend [MediaBrowserServiceCompat], implementing the media browsing
 * related methods [MediaBrowserServiceCompat.onGetRoot] and
 * [MediaBrowserServiceCompat.onLoadChildren];
 *
 *  *  In onCreate, start a new [MediaSessionCompat] and notify its parent
 * with the session"s token [MediaBrowserServiceCompat.setSessionToken];
 *
 *  *  Set a callback on the [MediaSessionCompat.setCallback].
 * The callback will receive all the user"s actions, like play, pause, etc;
 *
 *  *  Handle all the actual music playing using any method your app prefers (for example,
 * [android.media.MediaPlayer])
 *
 *  *  Update playbackState, "now playing" metadata and queue, using MediaSession proper methods
 * [MediaSessionCompat.setPlaybackState]
 * [MediaSessionCompat.setMetadata] and
 * [MediaSessionCompat.setQueue])
 *
 *  *  Declare and export the service in AndroidManifest with an intent receiver for the action
 * android.media.browse.MediaBrowserService
 *
 * To make your app compatible with Android Auto, you also need to:
 *
 *  *  Declare a meta-data tag in AndroidManifest.xml linking to a xml resource
 * with a &lt;automotiveApp&gt; root element. For a media app, this must include
 * an &lt;uses name="media"/&gt; element as a child.
 * For example, in AndroidManifest.xml:
 * &lt;meta-data android:name="com.google.android.gms.car.application"
 * android:resource="@xml/automotive_app_desc"/&gt;
 * And in res/values/automotive_app_desc.xml:
 * &lt;automotiveApp&gt;
 * &lt;uses name="media"/&gt;
 * &lt;/automotiveApp&gt;
 *
 */
class MusicExampleService : MediaBrowserServiceCompat() {

    private lateinit var session: MediaSessionCompat

    private val callback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            Log.d(TAG, "onPlay")
        }

        override fun onSkipToQueueItem(queueId: Long) {
            Log.d(TAG, "onSkipToQueueItem, queueId=$queueId")
        }

        override fun onSeekTo(position: Long) {
            Log.d(TAG, "onSeekTo, position=$position")
        }

        override fun onPlayFromMediaId(
            mediaId: String?,
            extras: Bundle?
        ) {
            Log.d(TAG, "onPlayFromMediaId, mediaId=$mediaId, extras=${extras?.keySet()}")
        }

        override fun onPause() {
            Log.d(TAG, "onPause")
        }

        override fun onStop() {
            Log.d(TAG, "onStop")
        }

        override fun onSkipToNext() {
            Log.d(TAG, "onSkipToNext")
        }

        override fun onSkipToPrevious() {
            Log.d(TAG, "onSkipToPrevious")
        }

        override fun onCustomAction(
            action: String?,
            extras: Bundle?
        ) {
            Log.d(TAG, "onCustomAction, action=$action, extras=${extras?.keySet()}")
        }

        override fun onPlayFromSearch(
            query: String?,
            extras: Bundle?
        ) {
            Log.d(TAG, "onPlayFromSearch, query=$query, extras=${extras?.keySet()}")
        }
    }

    override fun onCreate() {
        super.onCreate()

        session = MediaSessionCompat(this, TAG)
        sessionToken = session.sessionToken
        session.setCallback(callback)
        session.setFlags(
            MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                    MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS or
                    MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )

        val stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PLAY_PAUSE or
                        PlaybackStateCompat.ACTION_STOP
            )
        session.setPlaybackState(stateBuilder.build())
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    override fun onDestroy() {
        session.release()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): MediaBrowserServiceCompat.BrowserRoot? {
        return MediaBrowserServiceCompat.BrowserRoot("root", null)
    }

    private var playable = false
    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaItem>>
    ) {
        val item = MediaItem(
            MediaDescriptionCompat
                .Builder()
                .setMediaId("TEST_MEDIA_ID+${Date().time}")
                .setTitle("TEST_TITLE")
                .setDescription("TEST_DESCRIPTION")
                .setIconUri(Uri.parse("https://miro.medium.com/max/400/1*XAksToqI3TyMLhcszTCmhg.png"))
                .setMediaUri(Uri.parse("https://themushroomkingdom.net/sounds/wav/smw/smw_course_clear.wav"))
                .build(),
           if (!playable) MediaItem.FLAG_BROWSABLE else MediaItem.FLAG_PLAYABLE
        )
        playable = true
        result.sendResult(arrayListOf(item))
    }

    private companion object Contract {

        const val TAG = "MusicExampleService"
    }
}