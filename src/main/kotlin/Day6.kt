private val input = readInput("input6.txt")
fun main() {
    input.split("\n\n").map { group ->
        group.split("\n").map { it.toCharArray().toList() }.flatten().groupBy { it }.size
    }.sum().let { println("Part 1: $it") }

    input.split("\n\n").map { group ->
        group.split("\n").map { it.toCharArray().toList() }.flatten().groupBy { it }
            .map { it.value }.filter { it.size == group.split("\n").size }.size
    }.sum().let { println("Part 2: $it") }
}