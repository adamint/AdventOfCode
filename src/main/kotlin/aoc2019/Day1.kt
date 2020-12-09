package aoc2019

import common.*

class Aoc2019Day1 : Problem(2019, 1) {
    fun Int.getFuel() = this / 3 - 2
    fun Int.getTotalFuel(): Int {
        return if (this / 3 - 2 <=0) 0
        else (this / 3 - 2).let { it + it.getTotalFuel() }
    }
    override fun solvePart1(): Any {
        return lines.map { it.toInt() }.map { it.getFuel() }.sum()
    }

    override fun solvePart2(): Any {
        return lines.map { it.toInt() }.map { it.getTotalFuel() }.sum()
    }
}

fun main() = Aoc2019Day1().solve()