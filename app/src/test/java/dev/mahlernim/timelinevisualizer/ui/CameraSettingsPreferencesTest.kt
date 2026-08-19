package dev.mahlernim.timelinevisualizer.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dev.mahlernim.timelinevisualizer.render.CameraSettings
import dev.mahlernim.timelinevisualizer.render.LocalFraming
import dev.mahlernim.timelinevisualizer.render.LongHopSensitivity
import dev.mahlernim.timelinevisualizer.render.LongTripCompression
import dev.mahlernim.timelinevisualizer.render.RouteContext
import dev.mahlernim.timelinevisualizer.render.VideoQuality
import dev.mahlernim.timelinevisualizer.render.ZoomInSmoothness
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class CameraSettingsPreferencesTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val preferences = CameraSettingsPreferences(context)

    @Before
    fun reset() {
        preferences.reset()
    }

    @After
    fun tearDown() {
        preferences.reset()
    }

    @Test
    fun savesAndRestoresAllAdvancedSettings() {
        val expected = CameraSettings(
            routeContext = RouteContext.MAXIMUM,
            localFraming = LocalFraming.WIDE,
            zoomInSmoothness = ZoomInSmoothness.CINEMATIC,
            longHopSensitivity = LongHopSensitivity.LESS_SENSITIVE,
            longTripCompression = LongTripCompression.STRONG,
            videoQuality = VideoQuality.ULTRA,
        )

        preferences.save(expected)

        assertEquals(expected, CameraSettingsPreferences(context).load())
    }

    @Test
    fun defaultsMatchTheAdvancedPanelDefaults() {
        assertEquals(CameraSettings.DEFAULT, preferences.load())
        assertEquals(LongTripCompression.BALANCED, preferences.load().longTripCompression)
        assertEquals(VideoQuality.STANDARD, preferences.load().videoQuality)
    }
}
