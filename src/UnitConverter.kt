fun unitConverter(input: String) {
    val strMeasure1: String
    val strMeasure2: String
    val strArray = input.replace("degrees ", "").replace("degree ", "").split(" ").toTypedArray()
    if (strArray.size > 4 || strArray[0].toDoubleOrNull() == null) {
        parseError()
        return
    }
    val amount = strArray[0].toDouble()
    val measure1 = Units.getMeasure(strArray[1])
    val measure2 = Units.getMeasure(strArray[3])
    val converted = if (measure1 != Units.ERROR && measure2 != Units.ERROR && measure1.unit == measure2.unit) {
        when {
            measure1 == measure2 -> amount
            measure1.unit == 2 -> convTemp(amount, measure1, measure2)
            else -> {
                if (amount < 0.0) {
                    negativeError(measure1.unit)
                    return
                }
                measure1.valueM * amount / measure2.valueM
            }
        }
    } else {
        error(measure1, measure2)
        return
    }

    strMeasure1 = if (amount == 1.0) measure1.strings[1] else measure1.strings[2]
    strMeasure2 = if (converted == 1.0) measure2.strings[1] else measure2.strings[2]
    println("$amount $strMeasure1 is $converted $strMeasure2")
}

private fun convTemp(amount: Double, measure1: Units, measure2: Units): Double {
    var converted = 0.0

    when (measure1) {
        Units.KELVIN -> {
            when (measure2) {
                Units.CELSIUS -> converted = amount - 273.15
                Units.FAHRENHEIT -> converted = amount * 9 / 5 - 459.67
            }
        }
        Units.CELSIUS -> {
            when (measure2) {
                Units.KELVIN -> converted = amount + 273.15
                Units.FAHRENHEIT -> converted = amount * 9 / 5 + 32
            }
        }
        Units.FAHRENHEIT -> {
            when (measure2) {
                Units.KELVIN -> converted = (amount + 459.67) * 5 / 9
                Units.CELSIUS -> converted = (amount - 32) * 5 / 9
            }
        }
    }
    return converted
}

private fun error(unit1: Units, unit2: Units) {
    println("Conversion from ${unit1.strings[2]} to ${unit2.strings[2]} is impossible")
}

private fun parseError() = println("Parse error")

private fun negativeError(num: Int) {
    val word = if (num == 0) "Length" else "Weight"
    println("$word shouldn't be negative")
}