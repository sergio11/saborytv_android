package com.dreamsoftware.saborytv.ui.screens.player

import androidx.annotation.OptIn
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.player.FudgeTvPlayerTitle
import com.dreamsoftware.fudge.component.player.video.FudgeTvVideoPlayerControlsIcon
import com.dreamsoftware.fudge.component.player.video.FudgeTvVideoPlayerFrame
import com.dreamsoftware.fudge.component.player.video.FudgeTvVideoPlayerOverlay
import com.dreamsoftware.fudge.component.player.video.FudgeTvVideoPlayerSeeker
import com.dreamsoftware.fudge.component.player.video.FudgeTvVideoPlayerState
import com.dreamsoftware.fudge.component.player.video.rememberVideoPlayerState
import com.dreamsoftware.fudge.utils.dPadVideoEvents
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(UnstableApi::class)
@Composable
internal fun VideoPlayerScreenContent(
    state: VideoPlayerUiState,
) {

    val context = LocalContext.current
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = 4)

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context)))
            .setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT)
            .build()
            .apply {
                playWhenReady = true
                repeatMode = Player.REPEAT_MODE_ALL
            }
    }

    LaunchedEffect(exoPlayer, state) {
        exoPlayer.setMediaItem(
            MediaItem.Builder()
                .setUri(state.videoUrl)
                .build()
        )
        exoPlayer.prepare()
    }

    var contentCurrentPosition by remember { mutableLongStateOf(0L) }
    var isPlaying: Boolean by remember { mutableStateOf(exoPlayer.isPlaying) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            contentCurrentPosition = exoPlayer.currentPosition
            isPlaying = exoPlayer.isPlaying
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .dPadVideoEvents(
                exoPlayer,
                videoPlayerState,
            )
            .focusable()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PlayerView(context)
                    .apply {
                        useController = false
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    }
            },
            update = { it.player = exoPlayer },
            onRelease = { exoPlayer.release() }
        )

        FudgeTvFocusRequester { focusRequester ->
            FudgeTvVideoPlayerOverlay(
                modifier = Modifier.align(Alignment.BottomCenter),
                focusRequester = focusRequester,
                state = videoPlayerState,
                isPlaying = isPlaying,
                onBuildCenterButton = {
                    with(MaterialTheme.colorScheme) {
                        FudgeTvVideoPlayerControlsIcon(
                            modifier = Modifier.focusRequester(focusRequester),
                            icon = if (!isPlaying) R.drawable.play_icon else R.drawable.pause,
                            onClick = {
                                if (isPlaying) {
                                    exoPlayer.pause()
                                } else {
                                    exoPlayer.play()
                                }
                            },
                            contentColor = surface,
                            focusedContentColor = primary,
                            state = videoPlayerState,
                            isPlaying = isPlaying,
                        )
                    }
                },
                onBuildControls = {
                    VideoPlayerControls(
                        isPlaying = isPlaying,
                        contentCurrentPosition = contentCurrentPosition,
                        exoPlayer = exoPlayer,
                        state = videoPlayerState,
                        title = state.title,
                        authorName = state.chefProfileName,
                    )
                }
            )
        }
    }
}

@Composable
private fun VideoPlayerControls(
    isPlaying: Boolean,
    contentCurrentPosition: Long,
    exoPlayer: ExoPlayer,
    state: FudgeTvVideoPlayerState,
    title: String,
    authorName: String,
) {
    with(MaterialTheme.colorScheme) {
        FudgeTvVideoPlayerFrame(
            videoTitle = {
                FudgeTvPlayerTitle(
                    title = title,
                    description = authorName,
                    titleColor = surface,
                    descriptionColor = surface,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            videoActions = {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FudgeTvVideoPlayerControlsIcon(
                        icon = R.drawable.subtitles,
                        state = state,
                        isPlaying = isPlaying,
                        contentColor = surface,
                        focusedContentColor = primary
                    ) {}
                    FudgeTvVideoPlayerControlsIcon(
                        icon = R.drawable.ic_audio,
                        state = state,
                        isPlaying = isPlaying,
                        contentColor = surface,
                        focusedContentColor = primary
                    ) {}
                    FudgeTvVideoPlayerControlsIcon(
                        icon = R.drawable.ic_settings,
                        state = state,
                        isPlaying = isPlaying,
                        contentColor = surface,
                        focusedContentColor = primary
                    ) {}
                }
            },
            videoSeeker = {
                FudgeTvVideoPlayerSeeker(
                    state = state,
                    onSeek = { exoPlayer.seekTo(exoPlayer.duration.times(it).toLong()) },
                    contentProgress = contentCurrentPosition.milliseconds,
                    contentDuration = exoPlayer.duration.milliseconds,
                    normalColor = surface,
                    isSelectedColor = primary
                )
            }
        )
    }
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerScreen() {
    FitFlexTVTheme {
        VideoPlayerScreenContent(
            state = VideoPlayerUiState()
        )
    }
}