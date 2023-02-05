fun main() {
    val count = readln().toInt()
    var temp = 0
    repeat(count) {
        val newValue = readln().toInt()
        if (newValue % 4 == 0 && newValue > temp) temp = newValue
    }
    print(temp)
}
