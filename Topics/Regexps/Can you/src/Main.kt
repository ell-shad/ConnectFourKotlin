fun main() {
    val answer = readln().toRegex()
    print(if (answer.matches("I can do my homework on time!") || answer.matches("I can't do my homework on time!")) "true" else "false")
}
