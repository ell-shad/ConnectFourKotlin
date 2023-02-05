fun main() {
    val input = readln().toInt()
    print(if (input > 0) "positive" else if (input < 0) "negative" else "zero")
}
