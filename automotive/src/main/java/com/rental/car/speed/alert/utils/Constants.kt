package com.rental.car.speed.alert.utils

/**
 * A collection of constants used throughout the application.
 *
 * @author []Ankit Pandey]
 */
object Constants {
    /**
     * The ID of the notification channel used for speed alerts.
     */
    const val NOTIFICATION_CHANNEL_ID = "speed_alert_channel"

    /**
     * The name of the notification channel used for speed alerts.
     */
    const val NOTIFICATION_CHANNEL_NAME = "Speed Alert Notifications"

    /**
     * The title of the notification used for speed alerts.
     */
    const val NOTIFICATION_TITLE = "Speed Alert"

    /**
     * The text used to identify speed alerts.
     */
    const val TEXT_SPEED_ALERTS = "speed_alerts"

    /**
     * The key used to store the car ID.
     */
    const val CAR_ID = "CAR_ID"

    /**
     * A sample car ID used for testing purposes.
     */
    const val CAR_123 = "CAR123"

    /**
     * The constant representing the vehicle speed property.
     */
    const val PERF_VEHICLE_SPEED = 291504647

    /**
     * The normal sensor update rate.
     */
    const val SENSOR_RATE_NORMAL = 3

    /**
     * The conversion factor used to convert meters per second to kilometers per hour.
     */
    const val SPEED_CONVERSION_FACTOR = 3.6f

    /**
     * The conversion factor used to convert meters per second to kilometers per hour when the speed is zero.
     */
    const val SPEED_CONVERSION_FACTOR_ZERO = 0.0f
}
