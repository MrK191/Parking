package com.excpetion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ReservationNotFoundException(reservationId: Long,
                                   message: String? = "Reservation not found with id: $reservationId") : RuntimeException(message)