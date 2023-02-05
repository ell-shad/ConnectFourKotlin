fun main() {
    val input = readln().toInt()
    print("${if (input % 2 != 0) input + 1 else input + 2}")
}
