fun main() {
    when (readln()) {
        "amount" -> calculate(amount = readln().toDouble())
        "percent" -> calculate(percent = readln().toDouble())
        "years" -> calculate(years = readln().toDouble())
    }
}

fun calculate(amount: Double = 1000.0, percent: Double = 5.0, years: Double = 10.0) =
    println((amount * power(years, 1 + percent / 100)).toInt())

fun power(power: Double, base: Double): Double {
    var power = power
    var temp = 1.0
    while (power != 0.0) {
        temp *= base
        power--
    }
    return temp
}
