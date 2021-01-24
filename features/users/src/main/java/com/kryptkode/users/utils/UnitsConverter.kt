package com.kryptkode.users.utils

import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class UnitsConverter @Inject constructor() {

    fun convertCmToInches(centimeters: String): String {
        return try {
            (
                BigDecimal(centimeters.toDouble() * 0.393701).setScale(
                    3,
                    RoundingMode.HALF_EVEN
                )
                ).toString()
        } catch (e: NumberFormatException) {
            ""
        }
    }

    fun sanitizePopulation(population: String): String {
        return if (population.contains("unknown", ignoreCase = true)) "0" else population
    }
}
