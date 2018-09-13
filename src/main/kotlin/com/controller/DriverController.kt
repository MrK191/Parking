package com.controller

import com.dto.EndedReservationDto
import com.dto.ReservationManager
import com.dto.ReservationRequest
import com.dto.StartedReservationDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/driver/reservation")
class DriverController(private val reservationManager: ReservationManager) {

    @PostMapping("/start")
    private fun startReservation(@Valid @RequestBody reservationRequest: ReservationRequest): StartedReservationDto =
            reservationManager.createReservation(reservationRequest, LocalDateTime.now())


    @PutMapping("/stop")
    private fun stopReservation(@RequestParam reservationId: Long): EndedReservationDto =
            reservationManager.stopReservation(reservationId, LocalDateTime.now())


    @GetMapping("/cost")
    private fun calculateReservationCost(@RequestParam reservationId: Long): BigDecimal? =
            reservationManager.retrieveReservationCost(reservationId)

}
