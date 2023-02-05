fun main() {
    val count = readln().toInt()
    var numberOfPositive = 0
    repeat(count) {
        if (readln().toInt() > 0) numberOfPositive++
    }
    print(numberOfPositive)
}
