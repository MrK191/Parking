package com

import com.dto.ReservationRequest
import com.model.DriverType
import com.model.Reservation

import java.time.LocalDateTime
import java.time.Month

trait ReservationExamples {

    Reservation oneHourRegularReservation = createReservation(1L,
            DriverType.REGULAR,
            "ABC1",
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00),
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 30, 00),
            new BigDecimal(1),
            "PLN")

    Reservation twoHoursRegularReservation = createReservation(1L,
            DriverType.REGULAR,
            "ABC3",
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00),
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 14, 00, 00),
            new BigDecimal(2),
            "PLN")

    Reservation twoHoursVipReservation = createReservation(1L,
            DriverType.VIP,
            "ABC3",
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00),
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 14, 00, 00),
            new BigDecimal(2),
            "PLN")

    Reservation startedReservation = createReservation(1L,
            DriverType.REGULAR,
            "ABC4",
            LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00),
            null,
            null,
            "PLN")


    ReservationRequest createReservationRequest() {
        return new ReservationRequest("ABC1",
                "PLN",
                DriverType.REGULAR)
    }

    Reservation createReservation(Long id,
                                  DriverType driverType,
                                  String carLicensePlate,
                                  LocalDateTime startTime,
                                  LocalDateTime endTime,
                                  BigDecimal cost,
                                  String currencyCode) {
        return new Reservation(id, driverType, carLicensePlate, startTime, endTime, cost, currencyCode)
    }
}