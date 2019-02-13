package nameBadgesPrinter

import java.util.*

private const val SPACES_BETWEEN_NAME_AND_SURNAME: String = "  "
private const val LEFT_BORDER: String = "*  "
private const val RIGHT_BORDER: String = "  *"

fun main() {
    val lettersMap = initLettersMap()
    val (name, surname, status) = readData()
    printBadge(name, surname, status, lettersMap)
}

private fun printBadge(name: String, surname: String, status: String, lettersMap: Map<Char, Letter>) {
    val length = calculateLength(name, surname, lettersMap)
    repeat(length) {
        print("*")
    }
    println()
    printName(name, surname, lettersMap)
    printStatus(status, length)
    repeat(length) {
        print("*")
    }
}

private fun printName(name: String, surname: String, lettersMap: Map<Char, Letter>) {
    val fullName = name + SPACES_BETWEEN_NAME_AND_SURNAME + surname

    print(LEFT_BORDER)
    for (letter in fullName) {
        print("${lettersMap[letter]?.firstRow ?: " "} ")
    }
    print("${RIGHT_BORDER.drop(1)}\n")

    print(LEFT_BORDER)
    for (letter in fullName) {
        print("${lettersMap[letter]?.secondRow ?: " "} ")
    }
    print("${RIGHT_BORDER.drop(1)}\n")

    print(LEFT_BORDER)
    for (letter in fullName) {
        print("${lettersMap[letter]?.thirdRow ?: " "} ")
    }
    print("${RIGHT_BORDER.drop(1)}\n")
}

private fun printStatus(status: String, length: Int) {
    val spaces = (length - status.length) / 2 - 2
    print("*")
    repeat(spaces) {
        print(" ")
    }
    print(status)
    repeat(length - status.length - spaces - 2) {
        print(" ")
    }
    println("*")
}

private fun calculateLength(name: String, surname: String, lettersMap: Map<Char, Letter>): Int {
    var length = name.chars()
        .map{lettersMap[it.toChar()]?.firstRow?.length ?: 1}
        .sum()
    length += surname.chars()
        .map{lettersMap[it.toChar()]?.firstRow?.length ?: 1}
        .sum()

    length += SPACES_BETWEEN_NAME_AND_SURNAME.length * 2 + 1
    length += LEFT_BORDER.length
    length += RIGHT_BORDER.length
    length += name.length - 1 // spaces between letters
    length += surname.length - 1
    return length
}

data class Result(val name: String, val surname: String, val status: String)
private fun readData(): Result {
    val scanner = Scanner(System.`in`)
    println("Enter name and surname:")
    val name = scanner.next().toUpperCase()
    val surname = scanner.next().toUpperCase()
    println("Enter person's status:")
    val status = scanner.next()
    return Result(name, surname, status)
}

private fun initLettersMap() : Map<Char, Letter>{
    return mapOf(
        'A' to Letter("____", "|__|", "|  |"),
        'B' to Letter("___ ", "|__]", "|__]"),
        'C' to Letter("____", "|   ", "|___"),
        'D' to Letter("___ ", "|  \\", "|__/"),
        'E' to Letter("____", "|___", "|___"),
        'F' to Letter("____", "|___", "|   "),
        'G' to Letter("____", "| __", "|__]"),
        'H' to Letter("_  _", "|__|", "|  |"),
        'I' to Letter("_", "|", "|"),
        'J' to Letter(" _", " |", "_|"),
        'K' to Letter("_  _", "|_/ ", "| \\_"),
        'L' to Letter("_   ", "|   ", "|___"),
        'M' to Letter("_  _", "|\\/|", "|  |"),
        'N' to Letter("_  _", "|\\ |", "| \\|"),
        'O' to Letter("____", "|  |", "|__|"),
        'P' to Letter("___ ", "|__]", "|   "),
        'Q' to Letter("____", "|  |", "|_\\|"),
        'R' to Letter("____", "|__/", "|  \\"),
        'S' to Letter("____", "[__ ", "___]"),
        'T' to Letter("___", " | ", " | "),
        'U' to Letter("_  _", "|  |", "|__|"),
        'V' to Letter("_  _", "|  |", " \\/ "),
        'W' to Letter("_ _ _", "| | |", "|_|_|"),
        'X' to Letter("_  _", " \\/ ", "_/\\_"),
        'Y' to Letter("_   _", " \\_/ ", "  |  "),
        'Z' to Letter("___ ", "  / ", " /__"),
        ' ' to Letter(" ", " ", " ")
    )
}