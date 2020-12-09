package aoc2020

import common.*

class Aoc2020Day5 : Problem(2020, 5) {
    val numbers = input.split("\n").map { line ->
        val row = line.take(7).map { if (it == 'F') 0 else 1 }.joinToString("").toInt(2)
        val col = line.takeLast(3).map { if (it == 'L') 0 else 1 }.joinToString("").toInt(2)
        row * 8 + col to row
    }

    override fun solvePart1(): Any {
        return numbers.map { it.first }.maxOrNull()!!
    }

    override fun solvePart2(): Any {
        val filtered = numbers.filter { it.second != 0 && it.second != 127 }.map { it.first }
        return filtered.times(2).first { list ->
            val (one, two) = list
            one - two == 2 && (one - 1) !in filtered
        }[0] - 1
    }
}

fun main() {
    Aoc2020Day5().solve()
}