package aoc2019

import common.*
import java.lang.Exception

class Aoc2019Day2 : Problem(2019, 2) {
    override fun solvePart1(): Any {
        val values = input.split(",").map { it.toInt() }.toMutableList()
        values[1] = 12
        values[2] = 2
        var current = 0
        while (true) {
            when (values[current]) {
                1 -> {
                    val (read1, read2, store) = values.subList(current + 1, current + 4)
                    values[store] = values[read1] + values[read2]
                    current += 4
                }
                2 -> {
                    val (read1, read2, store) = values.subList(current + 1, current + 4)
                    values[store] = values[read1] * values[read2]
                    current += 4
                }
                99 -> break
            }

        }
        return values.first()
    }

    override fun solvePart2(): Any {
        val n = 100
        (1..n).forEach { pos1 ->
            (1..n).forEach { pos2 ->
                val values = input.split(",").map { it.toInt() }.toMutableList()
                values[1] = pos1
                values[2] = pos2
                var current = 0
                while (true) {
                    when (values[current]) {
                        1 -> {
                            val (read1, read2, store) = values.subList(current + 1, current + 4)
                            values[store] = values[read1] + values[read2]
                            current += 4
                        }
                        2 -> {
                            val (read1, read2, store) = values.subList(current + 1, current + 4)
                            values[store] = values[read1] * values[read2]
                            current += 4
                        }
                        99 -> break
                    }

                }
                if (values.first() == 19690720) return 100 * pos1 + pos2
            }
        }

        throw Exception()

    }
}

fun main() = Aoc2019Day2().solve()