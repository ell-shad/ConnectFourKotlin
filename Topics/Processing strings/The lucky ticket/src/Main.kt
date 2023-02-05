fun main() {
    val input = readln()
    val a = input.substring(0, 3)
    val b = input.substring(3)
    var sum1 = 0
    var sum2 = 0
    for (i in a.indices) {
        sum1 += a[i].digitToInt()
        sum2 += b[i].digitToInt()
    }
    print(if (sum1 == sum2) "Lucky" else "Regular")

}
