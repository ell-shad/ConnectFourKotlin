fun main() {
    val report = readln()
    val regex = Regex(". wrong answers?")
    print(if (regex.matches(report)) "true" else "false")
}
