package aoc2020

import common.*

class Aoc2020Day7 : AocProblem(2020, 7) {
    val bags = lines.map { line ->
        val bag = line.split(" bags contain")[0]
        val others = if (line.contains("contain no other bags")) listOf()
        else line.split("bags contain ")[1].removeSuffix(".").split(", ").map {
            it.split(" ")[0].toInt() to it.split(" ").subList(1, it.split(" ").size - 1).joinToString(" ")
        }
        bag to others
    }

    override fun solvePart1(): Any {
        fun recursePart1(startingBag: String, currentBag: String = startingBag): List<String> =
            if (currentBag == "shiny gold") listOf(startingBag)
            else bags.first { it.first == currentBag }.second.map { recursePart1(startingBag, it.second) }.flatten()

        return bags.map { recursePart1(it.first) }.flatten().toSet().size - 1
    }

    override fun solvePart2(): Any {
        fun recursePart2(startingBag: String, currentBag: String = startingBag): Int =
            if (bags.first { it.first == currentBag }.second.isEmpty()) 1
            else 1 + bags.first { it.first == currentBag }.second.sumBy {
                it.first * recursePart2(startingBag, it.second)
            }

        return recursePart2("shiny gold") - 1
    }
}

fun main() {
    Aoc2020Day7().solve()
}

