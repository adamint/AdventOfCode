package aoc2020

import common.AocProblem
import common.eqs
import common.matchLines

class Aoc2020Day2 : AocProblem(2020, 2) {
    val passwords = "(\\d+)-(\\d+) (.+): (.+)".toRegex().matchLines(lines).filterNotNull()
    override fun solvePart1(): Any {
        return passwords.filter { passwordMatch ->
            val (rangeLow, rangeHigh, letter, password) = passwordMatch.destructured
            val range = rangeLow.toInt()..rangeHigh.toInt()
            password.count { letter eqs it } in range
        }.size
    }

    override fun solvePart2(): Any {
        return passwords.filter { passwordMatch ->
            val (rangeLow, rangeHigh, letter, password) = passwordMatch.destructured
            val range = rangeLow.toInt()..rangeHigh.toInt()
            password[range.first - 1] eqs letter xor (password[range.last - 1] eqs letter)
        }.size
    }
}

fun main() {
    Aoc2020Day2().solve()
}