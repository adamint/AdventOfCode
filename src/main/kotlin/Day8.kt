private val input = readInput("input8.txt")

data class Instruction(val name: String, val argument: Int)

fun main() {
    val instructions = input.split("\n").map { it.split(" ") }.map { Instruction(it[0], it[1].toInt()) }
    fun part1(): Int {
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
    println("Part 1: ${part1()}")

    fun part2() {
        instructions
            .mapIndexed { index, instruction -> index to instruction }
            .filter { it.second.name in listOf("jmp", "nop") }
            .map { it.first }.forEach { a ->
                var accumulator = 0
                val testInstructions = instructions.toMutableList().apply {
                    val oldInstruction = this[a]
                    this[a] = oldInstruction.copy(name = if (oldInstruction.name == "jmp") "nop" else "jmp")
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
                if (currentLine == testInstructions.size) {
                    println("Part 2: $accumulator")
                    return@forEach
                }
            }
    }

    part2()

}

