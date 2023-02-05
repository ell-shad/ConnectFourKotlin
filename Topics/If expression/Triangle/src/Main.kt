fun main() {
    val (a, b, c) = listOf(readln().toInt(), readln().toInt(), readln().toInt())
    print(if (a + b < c || c + a < b ||  b + c < a || a + b == c || c + a == b ||  b + c == a) "NO" else "YES")
}
