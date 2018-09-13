package com.parkingrate

import java.math.BigDecimal

class VipParkingRate(override val firstHour: BigDecimal = BigDecimal(0),
                     override val secondHour: BigDecimal = BigDecimal(2),
                     override val extraHourMultiplier: BigDecimal = BigDecimal("1.2")) : ParkingRate