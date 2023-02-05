package connectfour

fun main() = startGame()
//startGame function to construct game logic
fun startGame() {
    //main variables
    var gameBoard: String
    var boardIsOK: Int
    val row: Int
    val mainMatrix: MutableList<MutableList<Int>>
    val column: Int
    var gameCount: Int
    val invalidMessage =
        "Invalid input\n" + "Set the board dimensions (Rows x Columns)\n" + "Press Enter for default (6 x 7)"
    //printing game beginning & getting player names and board size
    println("Connect Four")
    println("First player's name:")
    val firstPlayerName = readln()
    println("Second player's name:")
    val secondPlayerName = readln()
    println("Set the board dimensions (Rows x Columns)\n" + "Press Enter for default (6 x 7)")
    gameBoard = readln()
    //checking board dimension input
    if (gameBoard.isEmpty()) {
        //if input is blank default 6x7 dimension will be used
        row = 6
        column = 7
        //taking count of the game
        gameCount = askForGameCount()
        println("$firstPlayerName VS $secondPlayerName \n6 X 7 board")
        println(if (gameCount == 1) "Single game" else "Total $gameCount games")
        mainMatrix = createMatrix(row, column)
    } else {
        //if not empty we are trimming input
        var trimmed = trim(gameBoard)
        boardIsOK = checkBoard(trimmed)
        if (boardIsOK == 2) {
            //if 2 input is ok, 1 rows or columns are greater or smaller than limits
            row = trimmed[0].toString().toInt()
            column = trimmed[trimmed.lastIndex].toString().toInt()
            gameCount = askForGameCount()
            println("$firstPlayerName VS $secondPlayerName \n$row X $column board")
            println(if (gameCount == 1) "Single game" else "Total $gameCount games")
            mainMatrix = createMatrix(row, column)
         } else {
            while (boardIsOK != 2) {
                if (boardIsOK == 0) println(invalidMessage)//input contains not applicable characters
                gameBoard = readln()
                trimmed = trim(gameBoard)
                if (gameBoard.isEmpty()) {
                    boardIsOK = 2
                    gameCount = askForGameCount()
                    println("$firstPlayerName VS $secondPlayerName \n6 X 7 board")
                    println(if (gameCount == 1) "Single game" else "Total $gameCount games")
                } else boardIsOK = checkBoard(trimmed)
            }
            row = trimmed[0].toString().toInt()
            column = trimmed[trimmed.lastIndex].toString().toInt()
            gameCount = askForGameCount()
            println("$firstPlayerName VS $secondPlayerName \n$row X $column board")
            println(if (gameCount == 1) "Single game" else "Total $gameCount games")
            mainMatrix = createMatrix(row, column)
        }
    }
    //all inputs ok, starting game
    startPlaying(row, column, firstPlayerName, secondPlayerName, mainMatrix, gameCount)
}
//checking input board dimension if there is any character beside numbers, also if numbers are out of limit
fun checkBoard(input: String): Int {
    val message =
        "Board rows should be from 5 to 9\n" + "Set the board dimensions (Rows x Columns)\n" + "Press Enter for default (6 x 7)"
    if (!input[0].isDigit() || !input[input.lastIndex].isDigit()) return 0
    else if (input.length == 3 && (input[1] != 'X' && input[1] != 'x')) return 0 else if (input.length != 3) {
        for (i in input.indices) if (input[i] != 'x' && input[i] != 'X' && !input[i].isDigit()) return 0
        return if (input.lowercase().substringBefore('x').toInt() > 9) {
            println(message)
            1
        } else {
            println(message.replace(Regex("rows"), "columns"))
            1
        }
    } else if (input[0].toString().toInt() < 5 || input[0].toString().toInt() > 9) {
        println(message)
        return 1
    } else if (input[input.lastIndex].toString().toInt() > 9 || input[input.lastIndex].toString().toInt() < 5) {
        println(message.replace(Regex("rows"), "columns"))
        return 1
    } else return 2
}
// trim function removes blank spaces & tabs from game board dimension input and returns new string
fun trim(str: String): String {
    var temp = ""
    for (i in str.indices) if (str[i] != ' ' && str[i] != '\t') temp += str[i]
    return temp
}
//creating 2D list as per rows & columns, by default all elements are 0
fun createMatrix(rows: Int, columns: Int): MutableList<MutableList<Int>> {
    val boardList = mutableListOf<MutableList<Int>>()
    for (i in 0 until rows) boardList.add(i, mutableListOf())
    for (i in 0 until rows) for (j in 0 until columns) boardList[i].add(j, 0)
    return boardList
}
//checking if there is win case after each player play
fun winCheck(list: MutableList<MutableList<Int>>): Int {
    //horizontal check
    for (i in 0 until list.size) for (j in 0 until list[i].size)
        if (j + 3 < list[i].size && list[i][j] == list[i][j + 1] && list[i][j + 1] == list[i][j + 2] && list[i][j + 2] == list[i][j + 3])
            if (list[i][j] != 0) return list[i][j]
    //vertical check
    for (i in 0 until list.size) for (j in list[i].indices) {
        var temp = 0
        for (k in i until list.size) {
            if (list[i][j] != 0 && list[i][j] == list[k][j]) temp++
            else temp = 0
            if (temp == 4) return list[i][j]
        }
    }
    //right to left diagonal check
    for (i in 0 until list.size - 3) for (j in 0 until list[i].size - 3)
        if (list[i][j] != 0 && list[i][j] == list[i + 1][j + 1] && list[i][j] == list[i + 2][j + 2] && list[i][j] == list[i + 3][j + 3])
            return list[i][j]
    //left to right diagonal check
    for (i in 0 until list.size - 3) for (j in list[i].size - 1 downTo 3)
        if (list[i][j] != 0 && list[i][j] == list[i + 1][j - 1] && list[i][j] == list[i + 2][j - 2] && list[i][j] == list[i + 3][j - 3])
            return list[i][j]
    var top = 0
    //checking if board is full(draw case)
    list.forEach { i -> i.forEach { j -> if (j != 0) top++ } }
    if (top == list.size * list[0].size) return 3
    return 0
}
//all pre-requirements are ok we are starting game
fun startPlaying(rows: Int, columns: Int, firstPlayer: String, secondPlayer: String, matrix: MutableList<MutableList<Int>>, gameCount: Int = 1) {
    var gameOver: Boolean
    var firstScore = 0
    var secondScore = 0
    var currentPlayer: String
    var inputColumnString: String
    var inputColumn: Int
    var temp: Int
    var inputIsOk: Boolean
    //loop to control game count
    for (i in 1..gameCount) {
        var cnt = 0
        gameOver = false
        //for each game matrix elements will 0
        for (l in matrix.indices) for (j in matrix[l].indices) matrix[l][j] = 0
        currentPlayer = if (i % 2 == 0) secondPlayer else firstPlayer
        if (gameCount != 1) println("Game #$i")
        else drawBoard(rows, columns, matrix)
        while (!gameOver) {
            inputIsOk = false
            if (gameCount != 1 && cnt == 0) {
                drawBoard(rows, columns, matrix)
                cnt++
            }
            println("$currentPlayer's turn: ")
            inputColumnString = readln()
            if (inputColumnString == "end") {
                println("Game over!")
                gameOver = true
            } else {
                while (!inputIsOk) {
                    temp = 0
                    if (inputColumnString == "end") {
                        gameOver = true
                        println("Game over!")
                    }
                    if (!gameOver) {
                        for (k in inputColumnString.indices) {
                            if (!inputColumnString[k].isDigit()) {
                                println("Incorrect column number\n$currentPlayer's turn:")
                                inputColumnString = readln()
                                temp = 1
                                break
                            }
                        }
                        if (inputColumnString.isEmpty()) {
                            println("Incorrect column number\n$currentPlayer's turn:")
                            inputColumnString = readln()
                        } else if (temp == 0) {
                            inputColumn = inputColumnString.toInt()
                            if (inputColumn > columns || inputColumn < 1) {
                                println("The column number is out of range (1 - $columns)")
                                println("$currentPlayer's turn: ")
                                inputColumnString = readln()
                            } else inputIsOk = true
                        }
                    } else break
                }
                if (!gameOver) {
                    inputColumn = inputColumnString.toInt()
                    for (m in matrix.lastIndex downTo 0) {
                        if (matrix[m][inputColumn - 1] == 0) {
                            matrix[m][inputColumn - 1] = if (currentPlayer == firstPlayer) 1 else 2
                            drawBoard(rows, columns, matrix)
                            when (winCheck(matrix)) {
                                1 -> {
                                    println("Player $firstPlayer won")
                                    if (gameCount != 1) {
                                        firstScore += 2
                                        println("Score\n$firstPlayer: $firstScore $secondPlayer: $secondScore")
                                    }
                                    if (i == gameCount) println("Game Over!")
                                    gameOver = true
                                    break
                                }
                                2 -> {
                                    println("Player $secondPlayer won")
                                    if (gameCount != 1) {
                                        secondScore += 2
                                        println("Score\n$firstPlayer: $firstScore $secondPlayer: $secondScore")
                                    }
                                    if (i == gameCount) println("Game Over!")
                                    gameOver = true
                                    break
                                }
                                3 -> {
                                    println("It is a draw")
                                    if (gameCount != 1) {
                                        firstScore++
                                        secondScore++
                                        println("Score\n$firstPlayer: $firstScore $secondPlayer: $secondScore")
                                    }
                                    if (i == gameCount) println("Game Over!")
                                    gameOver = true
                                    break
                                }
                            }
                            currentPlayer = if (currentPlayer == firstPlayer) secondPlayer else firstPlayer
                            break
                        }
                        if (m == 0 && matrix[m][inputColumn - 1] != 0) {
                            println("Column $inputColumn is full\n")
                            break
                        }
                    }
                }
            }
        }
    }
}
//drawing board borders and discs
fun drawBoard(rows: Int, columns: Int, matrix: MutableList<MutableList<Int>>) {
    for (i in 1..columns) print(" $i")
    println()
    (0 until rows).forEach { i ->
        (0..columns).forEach { j ->
            print("║")
            if (j != columns) print(if (matrix[i][j] == 1) 'o' else if (matrix[i][j] == 2) '*' else ' ')
        }
        println()
    }
    for (i in 0..columns * 2) print(
        if (i == 0) "╚"
        else if (i == columns * 2) "╝"
        else if (i % 2 != 0) "═"
        else "╩"
    )
    println()
}
//asking for game count :D
fun askForGameCount(): Int {
    var input: String
    do {
        println("Do you want to play single or multiple games?\nFor a single game, input 1 or press Enter\nInput a number of games:")
        input = readln()
        if (input == "") return 1
    } while (!checkDig(input))
    return input.toInt()
}
//checking if game count input contains any non-digit character (built-in isDigit method can be used as well)
fun checkDig(str: String): Boolean {
    str.forEach { i ->
        if (i !in '1'..'9') {
            println("Invalid input")
            return false
        }
    }
    return true
}