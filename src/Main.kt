import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val getString = { scanner.nextLine().toLowerCase() }
    var strings = ""

    while (strings != "exit") {
        print("Enter what you want to convert (or exit): ")
        strings = getString()
        if (strings != "exit") Converter.convert(strings)
    }
}