fun main() {
    val input = readln()
    val a = input.substring(0, input.length / 2)
    val b = input.substring(input.length / 2)
    var sum1 = 0
    var sum2 = 0
    for (i in a.indices) {
        sum1 += a[i].digitToInt()
        sum2 += b[i].digitToInt()
    }
    print(if (sum1 == sum2) "YES" else "NO")
}
