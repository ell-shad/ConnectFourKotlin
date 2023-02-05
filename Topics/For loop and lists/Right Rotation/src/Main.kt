fun main() {

    val size = readln().toInt()
    val mutList: MutableList<Int> = mutableListOf()

    repeat(size) {
        mutList.add(readln().toInt())
    }

    var shftCnt = readln().toInt()

    if (shftCnt > size) {
        shftCnt %= size
    }

    if (shftCnt != size) {
        for (i in mutList.lastIndex - shftCnt + 1..mutList.lastIndex) {
            print("${mutList[i]} ")
        }
        for (i in 0..mutList.lastIndex - shftCnt) {
            print("${mutList[i]} ")
        }
    } else {
        for (i in mutList) {
            print("$i ")
        }
    }
}
