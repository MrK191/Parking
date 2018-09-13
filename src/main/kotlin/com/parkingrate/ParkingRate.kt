package com.parkingrate

import java.math.BigDecimal

interface ParkingRate {
    val firstHour: BigDecimal

    val secondHour: BigDecimal

    val extraHourMultiplier: BigDecimal
}