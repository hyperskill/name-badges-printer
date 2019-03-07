package nameBadgesPrinter

import java.util.*

class FontSymbol(val symbol: Char, private val data: String, private val width: Int = 4) {
    fun getSymbolLine(line: Int): String {
        return this.data.slice((line - 1) * width until line * width)
    }
}

class Font(private val letterSpacing: Int = 1) {
    private val symbols: ArrayList<FontSymbol> = ArrayList()

    init {
        symbols.add(FontSymbol('A', "____|__||  |"))
        symbols.add(FontSymbol('B', "___ |__]|__]"))
        symbols.add(FontSymbol('C', "____|   |___"))
        symbols.add(FontSymbol('D', "___ |  \\|__/"))
        symbols.add(FontSymbol('E', "____|___|___"))
        symbols.add(FontSymbol('F', "____|___|   "))
        symbols.add(FontSymbol('G', "____| __|__]"))
        symbols.add(FontSymbol('H', "_  _|__||  |"))
        symbols.add(FontSymbol('I', "_||", 1))
        symbols.add(FontSymbol('J', " _ |_|", 2))
        symbols.add(FontSymbol('K', "_  _|_/ | \\_"))
        symbols.add(FontSymbol('L', "_   |   |___"))
        symbols.add(FontSymbol('M', "_  _|\\/||  |"))
        symbols.add(FontSymbol('N', "_  _|\\ || \\|"))
        symbols.add(FontSymbol('O', "____|  ||__|"))
        symbols.add(FontSymbol('P', "___ |__]|   "))
        symbols.add(FontSymbol('Q', "____|  ||_\\|"))
        symbols.add(FontSymbol('R', "____|__/|  \\"))
        symbols.add(FontSymbol('S', "____[__ ___]"))
        symbols.add(FontSymbol('T', "___ |  | ", 3))
        symbols.add(FontSymbol('U', "_  _|  ||__|"))
        symbols.add(FontSymbol('V', "_  _|  | \\/ "))
        symbols.add(FontSymbol('W', "_ _ _| | ||_|_|", 5))
        symbols.add(FontSymbol('X', "_  _ \\/ _/\\_"))
        symbols.add(FontSymbol('Y', "_   _ \\_/   |  ", 5))
        symbols.add(FontSymbol('Z', "___   /  /__"))
        symbols.add(FontSymbol(' ', "            "))
    }

    private fun getSymbol(symbol: Char): FontSymbol? {
        return this.symbols.findLast { it.symbol == symbol }
    }

    fun getTextLines(text: String): Array<String> {
        val lines = Array(3) { String() }

        for (r in 0 until 3) {
            for (i in 0 until text.length) {
                val symbol = this.getSymbol(text.toUpperCase()[i]) ?: continue

                if (i != 0) {
                    lines[r] += " ".repeat(this.letterSpacing)
                }

                lines[r] += symbol.getSymbolLine(r + 1)
            }
        }

        return lines
    }
}

fun readline(info:String): String {
    val scanner = Scanner(System.`in`)
    print("$info: ")

    val name = scanner.nextLine()
    println()

    return name
}

fun printCard(name:String, status:String) {
    val font = Font()
    val lines = font.getTextLines(name)
    val contentLength = lines[0].length
    val leftPadding = (contentLength - status.length) / 2
    val rightPadding = contentLength - status.length - leftPadding
    val divider = "*".repeat(contentLength + 6)

    println(divider)

    for (i in 0 until lines.size) {
        println("*  ${lines[i]}  *")
    }

    println("*  ${" ".repeat(leftPadding)}$status${" ".repeat(rightPadding)}  *")
    println(divider)
}

fun main() {
    val name = readline("Enter name and surname")
    val status = readline("Enter person's status")

    printCard(name, status)
}

