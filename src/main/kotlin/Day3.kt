private val input = readInput("input3.txt")

fun main() {
    var part1 = 1L
    var part2 = 1L

    val lines = input.split("\n")

    for ((x, y) in listOf(3 to 1)) {
        var currentX = 0
        var currentY = 0

        var temp = 0

        while (currentY< lines.size) {
            if (lines[currentY][currentX % lines[0].length] == '#') temp++
            currentX += x
            currentY += y
        }
        part1 *= temp

    }

    for ((x, y) in listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)) {
        var currentX = 0
        var currentY = 0

        var temp = 0

        while (currentY< lines.size) {
            if (lines[currentY][currentX % lines[0].length] == '#') temp++
            currentX += x
            currentY += y
        }
        part2 *= temp

    }

    println("Part 1: $part1")
    println("Part 2: $part2")
}