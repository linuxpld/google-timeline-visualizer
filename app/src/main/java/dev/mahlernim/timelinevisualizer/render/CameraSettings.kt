package dev.mahlernim.timelinevisualizer.render

enum class RouteContext(val fraction: Double) {
    COMPACT(0.02),
    BALANCED(0.05),
    GENEROUS(0.08),
    MAXIMUM(0.10),
}

enum class LocalFraming(
    val padding: Double,
    val minimumViewportSpan: Double,
) {
    DETAILED(1.8, 0.00030),
    BALANCED(2.2, 0.00045),
    WIDE(2.6, 0.00060),
}

enum class ZoomInSmoothness(val halfDistanceKm: Double) {
    QUICK(25.0),
    GENTLE(50.0),
    CINEMATIC(80.0),
}

enum class LongHopSensitivity(val thresholdMultiplier: Double) {
    MORE_SENSITIVE(0.67),
    AUTOMATIC(1.0),
    LESS_SENSITIVE(1.5),
}

enum class LongTripCompression(val exponent: Double) {
    OFF(1.00),
    GENTLE(0.85),
    BALANCED(0.75),
    STRONG(0.60),
}

enum class VideoQuality(
    val size: Int,
    val bitrate: Int,
) {
    STANDARD(480, 2_500_000),
    HIGH(720, 5_000_000),
    ULTRA(1080, 8_000_000),
}

data class CameraSettings(
    val routeContext: RouteContext = RouteContext.BALANCED,
    val localFraming: LocalFraming = LocalFraming.BALANCED,
    val zoomInSmoothness: ZoomInSmoothness = ZoomInSmoothness.GENTLE,
    val longHopSensitivity: LongHopSensitivity = LongHopSensitivity.AUTOMATIC,
    val longTripCompression: LongTripCompression = LongTripCompression.BALANCED,
    val videoQuality: VideoQuality = VideoQuality.STANDARD,
) {
    companion object {
        val DEFAULT = CameraSettings()
        const val MIN_LOCAL_CONTEXT_KM = 1.0
        const val MAX_LOCAL_CONTEXT_KM = 250.0
        const val MIN_TRANSFER_THRESHOLD_KM = 40.0
        const val MAX_TRANSFER_THRESHOLD_KM = 180.0
    }
}
