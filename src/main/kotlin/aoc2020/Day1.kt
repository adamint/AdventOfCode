package aoc2020

import common.Problem
import common.allDistinct
import common.product
import common.times

class Aoc2020Day1 : Problem(2020, 1) {
    val nums = lines.map { it.toInt() }

    override fun solvePart1(): Any {
        return nums.filter { it < 2020 }.times(2).filter { it.allDistinct() }.first { it.sum() == 2020 }.product()
            .toInt()
    }

    override fun solvePart2(): Any {
        return nums.filter { it < 2020 }.times(nums, 3, cull = { it.sum() > 2020 }).filter { it.allDistinct() }
            .first { it.sum() == 2020 }.product()
            .toInt()
    }
}

fun main() {
    Aoc2020Day1().solve()
}