fun main() {
    val (a, b, c) = listOf(readln().toInt(), readln().toInt(), readln().toInt())
    print(!(a == b || b == c || a == c))
}
