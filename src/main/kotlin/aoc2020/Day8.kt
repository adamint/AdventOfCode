package aoc2020

import common.*

private val input = readInput("aoc2020/input8.txt")

class Aoc2020Day8 : AocProblem(2020, 8) {
    data class Instruction(val name: String, val argument: Int)

    val instructions = lines.map { it.split(" ") }.map { Instruction(it[0], it[1].toInt()) }

    override fun solvePart1(): Any {
        var accumulator = 0
        var currentLine = 0
        val linesRun = mutableListOf<Int>()

        while (currentLine < instructions.size && currentLine !in linesRun) {
            linesRun += currentLine
            val instruction = instructions[currentLine]
            when (instruction.name) {
                "acc" -> {
                    accumulator += instruction.argument
                    currentLine++
                }
                "nop" -> currentLine++
                "jmp" -> currentLine += instruction.argument
            }
        }
        return accumulator
    }

    override fun solvePart2(): Any {
        instructions
            .mapIndexed { index, instruction -> index to instruction }
            .filter { it.second.name in listOf("jmp", "nop") }
            .map { it.first }.forEach { jmpOrNopIndex ->
                var accumulator = 0
                val testInstructions = instructions.toMutableList().apply {
                    val oldInstruction = this[jmpOrNopIndex]
                    this[jmpOrNopIndex] = oldInstruction.copy(name = if (oldInstruction.name == "jmp") "nop" else "jmp")
                }

                var currentLine = 0
                val linesRun = mutableListOf<Int>()
                while (currentLine < testInstructions.size && currentLine !in linesRun) {
                    val instruction = testInstructions[currentLine]
                    linesRun += currentLine
                    when (instruction.name) {
                        "acc" -> {
                            accumulator += instruction.argument
                            currentLine++
                        }
                        "nop" -> currentLine++
                        "jmp" -> currentLine += instruction.argument
                    }
                }
                if (currentLine == testInstructions.size) return accumulator
            }

        throw Exception()
    }
}


fun main() {
    Aoc2020Day8().solve()
}

