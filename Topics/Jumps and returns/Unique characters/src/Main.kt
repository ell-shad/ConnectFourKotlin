fun main() {
    val a = readln()
    var temp = 0
    var count = 0
    for (i in a) {
        temp = 0
        for (j in a) {
            if (i == j) temp++
            if (temp > 1) {
                temp = 0
                break
            }
        }
        if (temp == 1) count++
    }
    print(count)
}
