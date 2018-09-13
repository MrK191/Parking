package com.dto

import com.excpetion.ReservationAlreadyStartedException
import com.excpetion.ReservationNotFoundException
import com.model.ReservationDao
import com.parkingrate.ParkingRateEvaluator
import com.util.ReservationCalculator
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Optional
import javax.transaction.Transactional


@Service
class ReservationManager(private val reservationDao: ReservationDao,
                         private val reservationConverter: ReservationConverter,
                         private val reservationCalculator: ReservationCalculator) {

    @Transactional
    fun createReservation(reservationRequest: ReservationRequest, startTime: LocalDateTime): StartedReservationDto {

        Optional.ofNullable(reservationDao.findStartedReservationByLicensePlate(reservationRequest.carLicensePlate))
                .ifPresent { throw ReservationAlreadyStartedException(reservationRequest.carLicensePlate) }

        return reservationConverter.convertToModel(reservationRequest, startTime)
                .also { reservationDao.save(it) }
                .let { reservationConverter.convertToReservationStartedDto(it) }
    }

    @Transactional
    fun stopReservation(reservationId: Long, endTime: LocalDateTime): EndedReservationDto {
        val reservation = reservationDao.findById(reservationId)

        return reservation
                .map {
                    it.endTime = endTime
                    it.cost = reservationCalculator.calculateReservationCost(
                            parkingRate = ParkingRateEvaluator.retrieveParkingRatesForDriver(reservation.get().driverType),
                            reservation = reservation.get())

                    reservationConverter.convertToReservationEndedDto(it)
                }
                .orElseThrow { ReservationNotFoundException(reservationId) }
    }

    fun retrieveReservationCost(reservationId: Long): BigDecimal? {

        return reservationDao.findById(reservationId).map { it.cost }
                .orElseThrow { ReservationNotFoundException(reservationId) }
    }

    fun isParkingmeterStarted(licenseId: String): Boolean = reservationDao.findStartedReservationByLicensePlate(licenseId) != null

    fun retrieveDailyEarnings(date: LocalDate): BigDecimal {
        return reservationDao.findByStartTimeAfterAndEndTimeBefore(date.atStartOfDay(), date.plusDays(1).atStartOfDay())
                .map { it.cost }
                .fold(BigDecimal.ZERO, BigDecimal::add)
    }
}
