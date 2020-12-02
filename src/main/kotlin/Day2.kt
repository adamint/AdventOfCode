private val input = readInput("input2.txt")

// part 1
/*fun main() {
    input.split("\n").filter { line ->
        val (rangeT, lT, password) = line.split(" ")
        val l = lT.removeSuffix(":")
        val ranges = rangeT.split("-").map { it.toInt() }
        val low = ranges[0]
        val high = ranges[1]

        val num = password.filter { it.toString() == l }.length
        num in low..high
    }.size.let { println(it) }
}
*/

// part 2
fun main() {
    input.split("\n").filter { line ->
        val (rangeT, lT, password) = line.split(" ")
        val l = lT.removeSuffix(":")
        val ranges = rangeT.split("-").map { it.toInt() }
        val low = ranges[0]
        val high = ranges[1]

        var num = 0
        if (password[low - 1].toString() == l)num+=1
        if (password[high - 1].toString() == l)num+=1
        num == 1
    }.size.let { println(it) }
}