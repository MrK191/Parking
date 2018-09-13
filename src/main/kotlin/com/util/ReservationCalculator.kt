package com.util

import com.model.Reservation
import com.parkingrate.ParkingRate
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Duration

@Service
class ReservationCalculator {
    fun calculateReservationCost(reservation: Reservation,
                                 parkingRate: ParkingRate): BigDecimal {
        val duration = Duration.between(reservation.startTime, reservation.endTime)
        val durationInHoursWithStartedHour = duration.toHours().plus(1)

        return when (durationInHoursWithStartedHour) {
            1L -> parkingRate.firstHour
            2L -> parkingRate.secondHour
            else -> {
                calculateExtraHours(durationInHoursWithStartedHour, parkingRate)
            }
        }
    }

    private fun calculateExtraHours(reservationHours: Long, parkingRate: ParkingRate): BigDecimal {

        var result: BigDecimal = parkingRate.secondHour
        var currentHour: Long = parkingRate.secondHour.longValueExact()

        while (currentHour != reservationHours) {
            result = parkingRate.extraHourMultiplier.multiply(result)
            currentHour++
        }

        return result
    }
}