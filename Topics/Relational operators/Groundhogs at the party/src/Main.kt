fun main() {
    val maxCups = 25
    val minCups = 15
    val inputCups = readln().toInt()
    val weekEnd = readln().toBoolean()
    if (weekEnd) print(inputCups <= maxCups && inputCups >= minCups) else print(inputCups <= maxCups - 5 && inputCups >= minCups - 5)
}
