val FIRST_ROW: Array<String> = arrayOf("____", "___ ", "____", "___ ", "____", "____",
        "____", "_  _", "_", " _", "_  _", "_   ", "_  _", "_  _", "____", "___ ", "____", "____",
        "____", "___", "_  _", "_  _", "_ _ _", "_  _", "_   _", "___ ")
val SECOND_ROW: Array<String> = arrayOf("|__|", "|__]", "|   ", "|  \\", "|___", "|___", "| __",
        "|__|", "|", " |", "|_/ ", "|   ", "|\\/|", "|\\ |", "|  |", "|__]", "|  |", "|__/", "[__ ",
        " | ", "|  |", "|  |", "| | |", " \\/ ", " \\_/ ", "  / ")
val THIRD_ROW: Array<String> = arrayOf("|  |", "|__]", "|___", "|__/", "|___", "|   ", "|__]",
        "|  |", "|", "_|", "| \\_", "|___", "|  |", "| \\|", "|__|", "|   ", "|_\\|", "|  \\",
        "___]", " | ", "|__|", " \\/ ", "|_|_|", "_/\\_", "  |  ", " /__")

fun print_asterisks(length: Int) {
    for (i in 1..length)
        print('*')
}

fun print_row(row: Array<String>, name: String) {
    print("\n* ")

    for (c in name) {
        if (c == ' ') {
            print("     ")
            continue
        }

        print("${row[c.toLowerCase() - 'a']} ")
    }

    print("*")
}

fun print_name(name: String) {
    print_row(FIRST_ROW, name)
    print_row(SECOND_ROW, name)
    print_row(THIRD_ROW, name)
}

fun print_status(status: String, length: Int) {
    print("\n* ")

    val start = (length - 4 - status.length) / 2
    for (i in 1..start)
        print(' ')
    print(status)

    for (i in 1..(length - 4 - start - status.length))
        print(' ')
    println(" *")
}

fun main(args: Array<String>) {
    print("Enter name and surname: ")
    val name = readLine()!!

    print("Enter person's status: ")
    val status = readLine()!!

    var length = 8
    for (c in name) {
        if (c == ' ')
            continue

        length += 1 + FIRST_ROW[(c.toLowerCase() - 'a')].length
    }

    print_asterisks(length)
    print_name(name)
    print_status(status, length)
    print_asterisks(length)
}

/*
Bill Gates
VIP

Tom Smith
Worker

Mr Anonimus
Participant

Alex Koryakov
don't want to do this shit
 */
