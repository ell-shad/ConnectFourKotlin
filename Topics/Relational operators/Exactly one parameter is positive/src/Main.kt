fun main() {
    val sd: MutableList<Int> = mutableListOf(readln().toInt(), readln().toInt(), readln().toInt())
    var neg = 0
    println(sd)
    for (i in 0..sd.lastIndex) if (sd[i] > 0) neg++
    print(if (neg > 1 || neg == 0) "false" else "true")
}