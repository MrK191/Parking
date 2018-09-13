package com.controller

import com.dto.ReservationManager
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/operator/parking")
class OperatorController(private val reservationManager: ReservationManager) {

    @GetMapping("/parkingmeter")
    private fun isParkingMeterStarted(@RequestParam licenseId: String): Boolean =
            reservationManager.isParkingmeterStarted(licenseId)

    @GetMapping("/earnings")
    private fun earnings(@RequestParam
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): BigDecimal =
            reservationManager.retrieveDailyEarnings(date)
}
