private val input = readInput("input7.txt")

fun main() {
    val bags = input.split("\n").map { line ->
        val bag = line.split(" bags contain")[0]
        val others = if (line.contains("contain no other bags")) listOf()
        else line.split("bags contain ")[1].removeSuffix(".").split(", ").map {
            it.split(" ")[0].toInt() to it.split(" ").subList(1, it.split(" ").size - 1).joinToString(" ")
        }
        bag to others
    }

    fun recursePart1(startingBag: String, currentBag: String = startingBag): List<String> =
        if (currentBag == "shiny gold") listOf(startingBag)
        else bags.first { it.first == currentBag }.second.map { recursePart1(startingBag, it.second) }.flatten()
    println("Part 1: ${bags.map { recursePart1(it.first) }.flatten().toSet().size - 1}")

    fun recursePart2(startingBag: String, currentBag: String = startingBag): Int =
        if (bags.first { it.first == currentBag }.second.isEmpty()) 1
        else 1 + bags.first { it.first == currentBag }.second.sumBy { it.first * recursePart2(startingBag, it.second) }
    println("Part 2: ${recursePart2("shiny gold") - 1}")
}

