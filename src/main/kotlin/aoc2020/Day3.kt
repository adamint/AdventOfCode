package aoc2020

import common.*

class Aoc2020Day3 : AocProblem(2020, 3) {
    fun count(slopes: List<Pair<Int, Int>>) = slopes.map { (x, y) ->
        var currentX = 0
        var currentY = 0
        var sum = 0
        while (currentY < lines.size) {
            if (lines[currentY][currentX % lines[0].length] == '#') sum++
            currentX += x
            currentY += y
        }
        sum.toLong()
    }.product()

    override fun solvePart1(): Any {
        return count(listOf(3 to 1))
    }

    override fun solvePart2(): Any {
        return count(listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2))
    }
}

fun main() {
    Aoc2020Day3().solve()
}