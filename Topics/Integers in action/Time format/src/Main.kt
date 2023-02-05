fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // dont change this line
      val hours = totalSeconds / 60 / 60 % 24
    val minutes = totalSeconds / 60 % 60
    val seconds = totalSeconds % 60

    print(hours)
    print(":")
    print(minutes)
    print(":")
    print(seconds)

}
