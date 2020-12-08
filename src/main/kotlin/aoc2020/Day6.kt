package aoc2020

import common.*

class Aoc2020Day6 : AocProblem(2020, 6) {
    override fun solvePart1(): Any {
        return splitByBlankLine.map { group ->
            group.split("\n").map { it.toCharArray().toList() }.flatten().groupBy { it }.size
        }.sum()
    }

    override fun solvePart2(): Any {
        return splitByBlankLine.map { group ->
            group.split("\n").map { it.toCharArray().toList() }.flatten().groupBy { it }
                .map { it.value }.filter { it.size == group.split("\n").size }.size
        }.sum()
    }
}


fun main() {
    Aoc2020Day6().solve()
}