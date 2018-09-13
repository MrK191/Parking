package com.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ReservationDao : JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE CAR_LICENSE_PLATE = :licensePlate AND endTime = null")
    fun findStartedReservation(@Param("licensePlate") licensePlate: String): Reservation?

    @Query("SELECT r FROM Reservation r WHERE START_TIME >= :startDate AND END_TIME <= :endDate")
    fun findReservationsForGivenDay(@Param("startDate") startDate: LocalDateTime,
                                    @Param("endDate") endDate: LocalDateTime): List<Reservation>
}