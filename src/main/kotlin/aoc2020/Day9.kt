package aoc2020

import common.*

class Aoc2020Day9 : Problem(2020, 9) {
    val numbers = lines.map { it.toLong() }
    val preamble = 25
    override fun solvePart1(): Any {
        return (25..numbers.lastIndex).first { index ->
            numbers.subList(index - preamble, index).times(2).none { it.sum() == numbers[index] }
        }.let { numbers[it] }
    }

    override fun solvePart2(): Any {
        val toFind = solvePart1() as Long
        return (2..25).asSequence().map { size ->
            (0..numbers.lastIndex - size)
                .map { numbers.subList(it, it + size) }
                .find { it.sum() == toFind }
        }.first { it != null }!!.let { found -> found.minOrNull()!! + found.maxOrNull()!! }
    }
}

fun main() {
    Aoc2020Day9().solve()
}

