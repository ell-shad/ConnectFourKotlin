fun main() {
    val input = readln().split(" ")
    val a = input[0].toInt()
    val b = input[1].toInt()
    val c = input[2].toInt()
    val n = input[3].toInt()
    var pswrd = ""
    var tmpC: String
    val tempu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val templ = tempu.lowercase()
    val tempd = "0123456789"
    val tempt = tempu + templ + tempd
    if (a + b + c == 0) pswrd = appendNew(n, tempt, pswrd) else {
        pswrd = appendNew(a, tempu, pswrd)
        pswrd = appendNew(b, templ, pswrd)
        pswrd = appendNew(c, tempd, pswrd)
        while (pswrd.length < n) {
            tmpC = newCharacter(tempt)
            while (tmpC == pswrd.last().toString()) tmpC = newCharacter(tempt)
            pswrd += tmpC
        }
    }
    print(pswrd)
}

fun newCharacter(tmp: String): String {
    val randomNumber = (Math.random() * tmp.length).toInt()
    return tmp.substring(randomNumber, randomNumber + 1)
}

fun appendNew(n: Int, tmp: String, psw: String): String {
    var newC: String
    var newP = psw
    for (i in 0 until n) {
        newC = newCharacter(tmp)
        if (newP.isNotEmpty()) {
            while (newC == newP.last().toString()) newC = newCharacter(tmp)
        }
        newP += newC
    }
    return newP
}
