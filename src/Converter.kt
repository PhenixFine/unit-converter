import java.util.*

object Converter {
    private enum class Units(val valueM: Double, val strings: Array<String>, val unit: Int) {
        // measurements: unit = 0
        METER(1.0, arrayOf("m", "meter", "meters"), 0),
        KILOMETER(1000.0, arrayOf("km", "kilometer", "kilometers"), 0),
        CENTIMETER(.01, arrayOf("cm", "centimeter", "centimeters"), 0),
        MILLIMETER(.001, arrayOf("mm", "millimeter", "millimeters"), 0),
        MILE(1609.35, arrayOf("mi", "mile", "miles"), 0),
        YARD(.9144, arrayOf("yd", "yard", "yards"), 0),
        FEET(.3048, arrayOf("ft", "foot", "feet"), 0),
        INCH(.0254, arrayOf("in", "inch", "inches"), 0),

        // mass: unit = 1
        GRAM(1.0, arrayOf("g", "gram", "grams"), 1),
        KILOGRAM(1000.0, arrayOf("kg", "kilogram", "kilograms"), 1),
        MILLIGRAM(.001, arrayOf("mg", "milligram", "milligrams"), 1),
        POUND(453.592, arrayOf("lb", "pound", "pounds"), 1),
        OUNCE(28.3495, arrayOf("oz", "ounce", "ounces"), 1),

        // temperatures: unit = 2
        KELVIN(0.0, arrayOf("k", "Kelvin", "Kelvins"), 2),
        CELSIUS(0.0, arrayOf("dc", "degree Celsius", "degrees Celsius", "c", "celsius"), 2),
        FAHRENHEIT(0.0, arrayOf("fahrenheit", "degree Fahrenheit", "degrees Fahrenheit", "df", "f"), 2),
        ERROR(0.0, arrayOf("", "", "???"), -1);

        companion object {
            fun getMeasure(size: String): Units {
                for (enum in values()) {
                    for (string in enum.strings) {
                        if (string.toLowerCase() == size) return enum
                    }
                }
                return ERROR
            }
        }
    }

    private fun error(unit1: Units, unit2: Units) {
        println("Conversion from ${unit1.strings[2]} to ${unit2.strings[2]} is impossible")
    }

    private fun parseError() = println("Parse error")

    private fun negativeError(num: Int) {
        val word = if (num == 0) "Length" else "Weight"
        println("$word shouldn't be negative")
    }

    fun convert(strings: String) {
        val strMeasure1: String
        val strMeasure2: String
        val strArray: Array<String> = strings.replace("degrees ", "").replace("degree ", "").split(" ").toTypedArray()
        if (strArray.size > 4 || strArray[0].toDoubleOrNull() == null) {
            parseError()
            return
        }
        val amount = strArray[0].toDouble()
        var converted = amount
        val measure1 = Units.getMeasure(strArray[1])
        val measure2 = Units.getMeasure(strArray[3])

        if (measure1 != Units.ERROR && measure2 != Units.ERROR && measure1.unit == measure2.unit) {
            if (measure1.unit == 2) {
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
            } else {
                if (amount < 0.0) {
                    negativeError(measure1.unit)
                    return
                }
                converted = measure1.valueM * amount / measure2.valueM
            }
        } else {
            error(measure1, measure2)
            return
        }

        strMeasure1 = if (amount == 1.0) measure1.strings[1] else measure1.strings[2]
        strMeasure2 = if (converted == 1.0) measure2.strings[1] else measure2.strings[2]
        println("$amount $strMeasure1 is $converted $strMeasure2")
    }
}

fun getString(): String {
    val scanner = Scanner(System.`in`)
    return (scanner.nextLine().toLowerCase())
}

fun main() {
    var strings: String = ""

    while (strings != "exit") {
        print("Enter what you want to convert (or exit): ")
        strings = getString()
        if (strings != "exit") Converter.convert(strings)
    }
}