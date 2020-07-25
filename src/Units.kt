enum class Units(val valueM: Double, val strings: Array<String>, val unit: Int) {
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
                for (string in enum.strings) if (string.toLowerCase() == size) return enum
            }
            return ERROR
        }
    }
}