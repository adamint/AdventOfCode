private val input = readInput("input2.txt")

fun main() {
    var part1 = 0
    var part2 = 0

    input.split("\n").forEach { line ->
        val (rangeT, lT, password) = line.split(" ")
        val l = lT.removeSuffix(":")
        val ranges = rangeT.split("-").map { it.toInt() }
        val low = ranges[0]
        val high = ranges[1]

        val num = password.filter { it.toString() == l }.length
        part1 += num in low..high
        part2 += (password[low - 1].toString() == l) xor (password[high - 1].toString() == l)

    }
    println("Part 1: $part1")
    println("Part 2: $part2")
}