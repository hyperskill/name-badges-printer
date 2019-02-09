import java.io.File
import java.util.*
import kotlin.math.max

val FIRST_ROW: Array<String> = arrayOf("____", "___ ", "____", "___ ", "____", "____",
        "____", "_  _", "_", " _", "_  _", "_   ", "_  _", "_  _", "____", "___ ", "____", "____",
        "____", "___", "_  _", "_  _", "_ _ _", "_  _", "_   _", "___ ")
val SECOND_ROW: Array<String> = arrayOf("|__|", "|__]", "|   ", "|  \\", "|___", "|___", "| __",
        "|__|", "|", " |", "|_/ ", "|   ", "|\\/|", "|\\ |", "|  |", "|__]", "|  |", "|__/", "[__ ",
        " | ", "|  |", "|  |", "| | |", " \\/ ", " \\_/ ", "  / ")
val THIRD_ROW: Array<String> = arrayOf("|  |", "|__]", "|___", "|__/", "|___", "|   ", "|__]",
        "|  |", "|", "_|", "| \\_", "|___", "|  |", "| \\|", "|__|", "|   ", "|_\\|", "|  \\",
        "___]", " | ", "|__|", " \\/ ", "|_|_|", "_/\\_", "  |  ", " /__")

var font: MutableMap<String, MutableList<String>> = mutableMapOf()

fun readFont() {
    val scanner = Scanner(File("src/font"))
    val length = scanner.nextInt()
    val letters = scanner.nextInt()

    for (i in 1..letters) {
        val letter = scanner.next()
        val len = scanner.nextInt()

        val ignore = scanner.nextLine()
        for (j in 0 until 10) {
            var row = scanner.nextLine()
            row += " ".repeat(len - row.length)

            if (!font.containsKey(letter))
                font.put(letter, mutableListOf())

            font[letter]?.plusAssign(row)
        }
    }
}

fun printEights(length: Int) {
    for (i in 1..length)
        print('8')
}

fun printRow(row: Array<String>, name: String, statusLength: Int, length: Int) {
    val indent = (length - statusLength) / 2
    val end = length - (indent + statusLength)

    print("\n88  " + if (indent > 0) " ".repeat(indent) else "")

    for (c in name) {
        if (c == ' ') {
            print(" ".repeat(5))
            continue
        }

        print("${row[c.toLowerCase() - 'a']} ")
    }

    print((if (end > 0) " ".repeat(end) else "") + "  88")
}

fun printName(name: String, nameLength: Int, length: Int) {
    val indent = (length - nameLength) / 2
    val end = length - (indent + nameLength)

    for (i in 0 until 10) {
        print("\n88  " + if (indent > 0) " ".repeat(indent) else "")

        for (c in name) {
            if (c == ' ') {
                print(" ".repeat(10))
                continue
            }

            print(font[c.toString()]!![i] + " ")
        }

        print((if (end > 0) " ".repeat(end) else "") + "  88")
    }
}

fun printStatus(status: String, statusLength: Int, length: Int) {
    printRow(FIRST_ROW, status, statusLength, length)
    printRow(SECOND_ROW, status, statusLength, length)
    printRow(THIRD_ROW, status, statusLength, length)
    println()
}

fun main(args: Array<String>) {
    print("Enter name and surname: ")
    val name = readLine()!!

    print("Enter person's status: ")
    val status = readLine()!!

    readFont()

    var nameLength = 8
    for (c in name) {
        if (c == ' ') {
            nameLength += 10
            continue
        }
        nameLength += font[c.toString()]!![0].length + 1
    }

    var statusLength = 8
    for (c in status) {
        if (c == ' ') {
            statusLength += 5
            continue
        }
        statusLength += FIRST_ROW[c.toLowerCase() - 'a'].length + 1
    }

    val length = max(statusLength, nameLength)

    printEights(length)
    printName(name, nameLength, length)
    printStatus(status, statusLength, length)
    printEights(length)
}

/*
Ian One
VIP

A b
Long Participant

Bill Gates
VIP

Tom Smith
Worker

Mr Anonimus
Participant

Alex Koryakov
dont want to do this shit

Hi my name is Mark
I have come to you from London
 */
