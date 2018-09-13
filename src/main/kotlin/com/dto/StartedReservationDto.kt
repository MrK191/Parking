package com.dto

import com.model.DriverType
import java.time.LocalDateTime

class StartedReservationDto(
        val id: Long,

        val driverType: DriverType,

        val carLicensePlate: String,

        val startTime: LocalDateTime,

        val currencyCode: String
)
