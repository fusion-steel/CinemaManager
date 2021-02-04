package cinema

fun main() {
    println("Enter the number of rows:")
    val a = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val b = readLine()!!.toInt()

    val cinema = Cinema(a, b)
    cinema.runCinema()
}