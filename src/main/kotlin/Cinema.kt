package cinema

class Cinema(_rows: Int = 1, _seats: Int = 1) {
    private var rows = _rows
        set(value) {
            if (value in 1..9) {
                field = value
            }
            else {
                throw IllegalArgumentException("Rows must be in range 1..9")
            }
        }
    private var seats = _seats
        set(value) {
            if (value in 1..9) {
                field = value
            }
            else {
                throw IllegalArgumentException("Number of seats must be in range 1..9")
            }
        }

    private val cinema2DArray: Array<CharArray> = Array(rows) { CharArray(seats) { 'S' } }

    private var purchasedTickets: Int = 0
    private val totalSeats: Int = seats * rows
    private val percentage: Double
        get() = purchasedTickets.toDouble() / totalSeats * 100
    private val totalIncome = calcIncome()
    private var currentIncome: Int = 0

    init {
        if (rows !in 1..9 || seats !in 1..9) {
            throw IllegalArgumentException("Rows and number of seats must be in range 1..9")
        }
        println("Object Cinema initialized.")
    }

    private fun printCinema() {
        println("Cinema:")
        println((1..seats).joinToString(separator = " ", prefix = "  "))
        for (i in cinema2DArray.indices) {
            println("${i + 1} ${cinema2DArray[i].joinToString(" ")}")
        }
    }

    private fun calcIncome(): Int {
        var tIncome = Int.MIN_VALUE
        val price = 10
        if (totalSeats < 60) tIncome = totalSeats * price
        else if (totalSeats >= 60) {
            val firstHalf = (rows / 2) * seats
            val secondHalf = totalSeats - firstHalf
            tIncome = firstHalf * price + secondHalf * (price - 2)
        }
        return tIncome
    }

    private fun buyTicket(): Int {
        var resultTicketPrice: Int = 0
        val standardTicketPrice = 10

        while (true) {
            println("Enter a row number:")
            val row = readLine()!!.toInt()
            println("Enter a seat number in that row:")
            val seat = readLine()!!.toInt()

            if (row <= rows && seat <= seats) {
                if (totalSeats < 60 || row <= rows / 2) {
                    resultTicketPrice = standardTicketPrice
                } else {
                    resultTicketPrice = (standardTicketPrice - 2)
                }
                if (cinema2DArray[row - 1][seat - 1] != 'B') {
                    cinema2DArray[row - 1][seat - 1] = 'B'
                    purchasedTickets++
                    currentIncome += resultTicketPrice
                    println("Ticket price: \$$resultTicketPrice")
                    break
                }
                else {
                    println("That ticket has already been purchased!")
                    println("Please, try again.")
                    continue
                }
            } else {
                println("Wrong input!")
                println()
                continue
            }
        }
        return resultTicketPrice
    }

    private fun printStatistics() {
        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: ${"%.2f".format(percentage)}%")
        println("Current income: \$$currentIncome")
        println("Total income: \$$totalIncome")
    }

    fun runCinema () {
        var infinite = true
        while (infinite) {
            val option = mapOf(
                1 to "Show the seats",
                2 to "Buy a ticket",
                3 to "Statistics",
                0 to "Exit"
            )
            println()
            option.forEach { (key, value) -> println("$key. $value") }
            println()

            val userChoice = readLine()
            println()

            if (userChoice != null) {
                when (userChoice.toInt()) {
                    1 -> printCinema()
                    2 -> buyTicket()
                    3 -> printStatistics()
                    0 -> infinite = false
                }
            }
        }
    }

    override fun toString(): String {
        println()
        return cinema2DArray.joinToString(separator = "\n") { it.joinToString(" ") }
    }
}