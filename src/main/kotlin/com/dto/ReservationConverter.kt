package com.dto

import com.model.Reservation
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ReservationConverter {
    fun convertToReservationStartedDto(reservation: Reservation): StartedReservationDto {
        return StartedReservationDto(id = reservation.id,
                driverType = reservation.driverType,
                carLicensePlate = reservation.carLicensePlate,
                startTime = reservation.startTime,
                currencyCode = reservation.currencyCode)
    }

    fun convertToReservationEndedDto(reservation: Reservation): EndedReservationDto {
        return EndedReservationDto(id = reservation.id,
                driverType = reservation.driverType,
                carLicensePlate = reservation.carLicensePlate,
                startTime = reservation.startTime,
                endTime = reservation.endTime,
                cost = reservation.cost,
                currencyCode = reservation.currencyCode)
    }

    fun convertToModel(reservationRequest: ReservationRequest, startTime: LocalDateTime): Reservation {
        return Reservation(driverType = reservationRequest.driverType,
                carLicensePlate = reservationRequest.carLicensePlate,
                startTime = startTime,
                currencyCode = reservationRequest.currencyCode)
    }
}
