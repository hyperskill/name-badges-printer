package nameBadgesPrinter

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class FontSymbol(val symbol: Char, val data: Array<String>)

class Font(fontPath: String, spaceLength:Int) {
    private val symbols: ArrayList<FontSymbol> = ArrayList()
    private var lineHeight:Int = 0

    init {
        val reader = File(fontPath).inputStream().bufferedReader()
        val (lineHeight: Int, charCount: Int) = reader.readLine().split(" ").map { it.toInt() }

        for (i in 0 until charCount) {
            val fontChar = reader.readLine()[0]
            val charData = Array<String>(lineHeight) { reader.readLine() }

            this.symbols.add(FontSymbol(fontChar, charData))
        }

        val spaceData = Array<String>(lineHeight) { " ".repeat(spaceLength) }

        this.symbols.add(FontSymbol(' ', spaceData))
        this.lineHeight = lineHeight
    }

    private fun getSymbol(symbol: Char): FontSymbol? {
        return this.symbols.findLast { it.symbol == symbol }
    }

    fun getTextLines(text: String): Array<String> {
        val lines = Array<String>(this.lineHeight) { "" }

        for (l in 0 until this.lineHeight) {
            for (i in 0 until text.length) {
                val symbol = this.getSymbol(text[i]) ?: continue

                lines[l] += symbol.data[l]
            }
        }

        return lines
    }
}

fun readline(info: String): String {
    val scanner = Scanner(System.`in`)
    print("$info: ")

    val name = scanner.nextLine()
    println()

    return name
}

fun alignCentre(badgeData: ArrayList<String>): ArrayList<String> {
    val maxSize:Int = badgeData.map{ it.length }.max() ?: 0

    return ArrayList<String>(badgeData.map {
        if (it.length < maxSize) {
            val leftPadding = (maxSize - it.length) / 2
            val rightPadding = maxSize - it.length - leftPadding

            return@map "${" ".repeat(leftPadding)}$it${" ".repeat(rightPadding)}"
        }

        return@map it
    })
}

fun printCard(name: String, status: String) {
    val romanFont = Font("fonts/roman.txt", 10)
    val mediumFont = Font("fonts/medium.txt", 5)
    var badgeData = ArrayList<String>()

    badgeData.addAll(romanFont.getTextLines(name))
    badgeData.addAll(mediumFont.getTextLines(status))

    badgeData = alignCentre(badgeData)
    badgeData = addBorder(badgeData)

    badgeData.forEach { println(it) }
}

fun addBorder(badgeData: ArrayList<String>): ArrayList<String> {
    val borderedBadgeData = ArrayList<String>()
    val borderLength = badgeData.first().length + 8

    borderedBadgeData.add("8".repeat(borderLength))
    borderedBadgeData.addAll(badgeData.map {
        return@map "88  $it  88"
    })
    borderedBadgeData.add("8".repeat(borderLength))

    return borderedBadgeData
}

fun main() {
    val name = readline("Enter name and surname")
    val status = readline("Enter person's status")

    printCard(name, status)
}
