package com.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "reservations")
data class Reservation(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @NotNull
        @Enumerated(EnumType.STRING)
        val driverType: DriverType,

        @Size(min = 4, max = 8)
        val carLicensePlate: String,

        var startTime: LocalDateTime,

        var endTime: LocalDateTime? = null,

        var cost: BigDecimal? = null,

        val currencyCode: String
)

