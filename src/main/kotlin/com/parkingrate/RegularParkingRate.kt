package com.parkingrate

import java.math.BigDecimal

class RegularParkingRate(override val firstHour: BigDecimal = BigDecimal(1),
                         override val secondHour: BigDecimal = BigDecimal(2),
                         override val extraHourMultiplier: BigDecimal = BigDecimal("1.5")) : ParkingRate