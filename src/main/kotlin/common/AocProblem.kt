package common

abstract class AocProblem(val year: Int, val day: Int) {
    protected val input = readInput("aoc$year/input$day.txt")
    protected val lines: List<String> = input.split("\n")
    protected val splitByBlankLine: List<String> = input.split("\n\n")

    abstract fun solvePart1(): Any
    abstract fun solvePart2(): Any

    fun solve() {
        val part1 = solvePart1()
        val part2 = try {
            solvePart2()
        } catch (e: NotImplementedError) {
            null
        }
        println("Part 1: $part1")
        println("Part 2: $part2")

        if (part2 == null) writeToClipboard(part1).also { println("Part 1 written to clipboard") }
        else writeToClipboard(part2).also { println("Part 2 written to clipboard") }
    }
}