package com.dto

import com.model.DriverType
import java.math.BigDecimal
import java.time.LocalDateTime

class StoppedReservationDto(
        val id: Long,

        val driverType: DriverType,

        val carLicensePlate: String,

        val startTime: LocalDateTime,

        val currencyCode: String,

        val endTime: LocalDateTime?,

        val cost: BigDecimal?
)
