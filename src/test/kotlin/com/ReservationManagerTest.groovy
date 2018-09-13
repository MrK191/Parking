package com

import com.dto.EndedReservationDto
import com.dto.ReservationConverter
import com.dto.ReservationManager
import com.dto.StartedReservationDto
import com.excpetion.ReservationAlreadyStartedException
import com.model.ReservationDao
import com.util.ReservationCalculator
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

class ReservationManagerTest extends Specification implements ReservationExamples {

    ReservationDao reservationDao = Mock()
    def reservationManager = new ReservationManager(reservationDao, new ReservationConverter(), new ReservationCalculator())

    def "should successfully start parkingmeter"() {

        when:
        StartedReservationDto reservationDto = reservationManager.createReservation(createReservationRequest(),
                LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00))

        then:
        reservationDto.carLicensePlate == 'ABC1'
        reservationDto.startTime != null
    }

    def "should successfully throw exception when trying to start same reservation twice"() {
        given:
        reservationDao.findStartedReservation(_) >> oneHourRegularReservation

        when:
        reservationManager.createReservation(createReservationRequest(),
                LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 00, 00))

        then:
        thrown ReservationAlreadyStartedException
    }

    def "should successfully stop parkingmeter"() {
        given:
        reservationDao.findById(_ as Long) >> Optional.ofNullable(startedReservation)

        when:
        EndedReservationDto reservationDto = reservationManager.stopReservation(1L,
                LocalDateTime.of(2018, Month.FEBRUARY, 1, 12, 30, 00))

        then:
        reservationDto.getEndTime() != null
        reservationDto.getCost() == 1.0
    }

    def "should inform how much to pay for reservation "() {
        given:
        reservationDao.findById(_) >> Optional.ofNullable(oneHourRegularReservation)

        when:
        BigDecimal cost = reservationManager.retrieveReservationCost(1L)

        then:
        cost == 1.0
    }

    def "should return correct earnings for owner for specific day"() {
        given:

        reservationDao.findReservationsForGivenDay(_, _) >> Arrays.asList(oneHourRegularReservation,
                twoHoursRegularReservation,
                twoHoursVipReservation)
        when:
        BigDecimal earningsForSpecificDay = reservationManager.retrieveDailyEarnings(LocalDate.now())

        then:
        earningsForSpecificDay == 5.0
    }

    def "should check if driver's car started reservation"() {
        given:
        reservationDao.findStartedReservation(_) >> oneHourRegularReservation

        when: "operator checks car"
        def startedParkingmeter = reservationManager.isParkingmeterStarted("ABC1")

        then: "system return response that there is parked car"
        startedParkingmeter
    }
}
