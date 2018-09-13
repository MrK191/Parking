package com.parkingrate

import com.model.DriverType

class ParkingRateEvaluator {

    companion object {
        fun retrieveParkingRatesForDriver(driverType: DriverType): ParkingRate {
            return when (driverType) {
                DriverType.REGULAR -> RegularParkingRate()
                DriverType.VIP -> VipParkingRate()
            }

        }
    }
}