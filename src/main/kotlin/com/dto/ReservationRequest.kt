package com.dto

import com.model.DriverType
import com.validation.CurrencyCode
import javax.validation.constraints.Size

class ReservationRequest(

        @field:Size(min = 4, max = 8)
        val carLicensePlate: String,

        @field:CurrencyCode
        val currencyCode: String,

        val driverType: DriverType
)
