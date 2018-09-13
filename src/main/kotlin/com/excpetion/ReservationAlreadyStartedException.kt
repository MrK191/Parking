package com.excpetion

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ReservationAlreadyStartedException(licensePlate: String,
                                         message: String? = "Reservation already started for license plate: $licensePlate") : RuntimeException(message)