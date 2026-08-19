package dev.mahlernim.timelinevisualizer.ui

import android.content.Context
import androidx.core.content.edit
import dev.mahlernim.timelinevisualizer.render.CameraSettings
import dev.mahlernim.timelinevisualizer.render.LocalFraming
import dev.mahlernim.timelinevisualizer.render.LongHopSensitivity
import dev.mahlernim.timelinevisualizer.render.LongTripCompression
import dev.mahlernim.timelinevisualizer.render.RouteContext
import dev.mahlernim.timelinevisualizer.render.ZoomInSmoothness
import dev.mahlernim.timelinevisualizer.render.VideoQuality

class CameraSettingsPreferences(context: Context) {
    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun load(): CameraSettings = CameraSettings(
        routeContext = enumValue(KEY_ROUTE_CONTEXT, RouteContext.BALANCED),
        localFraming = enumValue(KEY_LOCAL_FRAMING, LocalFraming.BALANCED),
        zoomInSmoothness = enumValue(KEY_ZOOM_IN, ZoomInSmoothness.GENTLE),
        longHopSensitivity = enumValue(KEY_LONG_HOP, LongHopSensitivity.AUTOMATIC),
        longTripCompression = enumValue(KEY_LONG_TRIP, LongTripCompression.BALANCED),
        videoQuality = enumValue(KEY_VIDEO_QUALITY, VideoQuality.STANDARD),
    )

    fun save(settings: CameraSettings) {
        preferences.edit {
            putString(KEY_ROUTE_CONTEXT, settings.routeContext.name)
            putString(KEY_LOCAL_FRAMING, settings.localFraming.name)
            putString(KEY_ZOOM_IN, settings.zoomInSmoothness.name)
            putString(KEY_LONG_HOP, settings.longHopSensitivity.name)
            putString(KEY_LONG_TRIP, settings.longTripCompression.name)
            putString(KEY_VIDEO_QUALITY, settings.videoQuality.name)
        }
    }

    fun reset(): CameraSettings {
        preferences.edit { clear() }
        return CameraSettings.DEFAULT
    }

    private inline fun <reified T : Enum<T>> enumValue(key: String, fallback: T): T =
        runCatching { enumValueOf<T>(preferences.getString(key, null) ?: return fallback) }.getOrDefault(fallback)

    private companion object {
        const val PREFERENCES_NAME = "camera-settings"
        const val KEY_ROUTE_CONTEXT = "route-context"
        const val KEY_LOCAL_FRAMING = "local-framing"
        const val KEY_ZOOM_IN = "zoom-in"
        const val KEY_LONG_HOP = "long-hop"
        const val KEY_LONG_TRIP = "long-trip"
        const val KEY_VIDEO_QUALITY = "video-quality"
    }
}
