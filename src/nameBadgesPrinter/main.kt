package nameBadgesPrinter

import java.io.File
import java.util.*
import kotlin.math.max

private const val SPACE_BETWEEN_WORDS: String = "     "
private const val BORDER: String = "  "
private const val FILLER: String = "8"
private const val MEDIUM_FONT: String = "fonts/medium.txt"
private const val ROMAN_FONT: String = "fonts/roman.txt"

fun main() {
    val mediumFontMap = initLettersMap(MEDIUM_FONT)
    val romanFontMap = initLettersMap(ROMAN_FONT)
    val (name, status) = readData()
    printBadge(name, status, romanFontMap, mediumFontMap)
}

private fun printBadge(name: String, status: String, nameLettersMap: Map<Char, Letter>,
                       statusLetterMap: Map<Char, Letter>) {
    val (nameLength, statusLength, totalLength) = calculateLength(name, status, nameLettersMap, statusLetterMap)
    printBorder(totalLength)
    printWord(name, nameLettersMap, nameLength, totalLength, true)
    printWord(status, statusLetterMap, statusLength, totalLength, false)
    printBorder(totalLength)
}

fun printWord(word: String, lettersMap: Map<Char, Letter>, length: Int, totalLength: Int, doubleSpaces: Boolean) {
    val leftSpaces = (totalLength - length) / 2
    val rightSpaces = totalLength - length - leftSpaces
    val height = lettersMap['a']?.rows?.size ?: 0
    for (i in 0 until height) {
        print("$FILLER$FILLER$BORDER")
        repeat(leftSpaces) {
            print(" ")
        }
        for (char in word) {
            print(lettersMap[char]?.rows?.get(i) ?: "")
            if (char == ' ') {
                print(SPACE_BETWEEN_WORDS)
                if (doubleSpaces) {
                    print(SPACE_BETWEEN_WORDS)
                }
            }
        }
        repeat(rightSpaces) {
            print(" ")
        }
        print("$BORDER$FILLER$FILLER\n")
    }
}

private fun printBorder(totalLength: Int) {
    repeat(totalLength) {
        print(FILLER)
    }
    println()
}

data class LengthResult(val nameLegth: Int, val statusLength: Int, val totalLength: Int)

private fun calculateLength(name: String, status: String, nameLettersMap: Map<Char, Letter>,
                            statusLetterMap: Map<Char, Letter>): LengthResult {
    var nameLength = calculateStringLength(name, nameLettersMap)
    var statusLength = calculateStringLength(status, statusLetterMap)
    if (name.contains(' ')) {
        nameLength += SPACE_BETWEEN_WORDS.length * 2
    }
    if (status.contains(' ')) {
        statusLength += SPACE_BETWEEN_WORDS.length
    }
    val totalLength = max(nameLength, statusLength)
    return LengthResult(nameLength, statusLength, totalLength)
}

private fun calculateStringLength(string: String, lettersMap: Map<Char, Letter>): Int {
    var length = string.chars()
        .map { lettersMap[it.toChar()]?.rows?.get(0)?.length ?: 0 }
        .sum()
    length += BORDER.length * 2
    length += FILLER.length * 4
    return length
}

data class Result(val name: String, val status: String)

private fun readData(): Result {
    val scanner = Scanner(System.`in`)
    println("Enter name and surname:")
    val name = scanner.nextLine()
    println("Enter person's status:")
    val status = scanner.nextLine()
    return Result(name, status)
}

private fun initLettersMap(fileName: String): MutableMap<Char, Letter> {
    val fileReader = Scanner(File(fileName))
    val height = fileReader.nextInt()
    val numOfLetters = fileReader.nextInt()
    val lettersMap = mutableMapOf<Char, Letter>()
    for (i in 1..numOfLetters) {
        val letter = Letter(height)
        val char = fileReader.next()[0]
        fileReader.nextInt() // character width - never used
        fileReader.nextLine()
        for (j in 0 until height) {
            letter.rows.add(fileReader.nextLine())
        }
        lettersMap[char] = letter
    }
    return lettersMap
}