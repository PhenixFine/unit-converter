import java.util.*

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