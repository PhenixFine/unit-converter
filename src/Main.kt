import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val getString = { scanner.nextLine().toLowerCase() }
    var input = ""

    while (input != "exit") {
        print("Enter what you want to convert (or exit): ")
        input = getString()
        if (input != "exit") unitConverter(input)
    }
}