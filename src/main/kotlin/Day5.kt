private val input = readInput("input5.txt")

fun main() {
    val numbers = input.split("\n").map { line ->
        val row = line.take(7).map { if (it == 'F') 0 else 1 }.joinToString("").toInt(2)
        val col =  line.takeLast(3).map { if (it == 'L') 0 else 1 }.joinToString("").toInt(2)
        row * 8 + col to row
    }
    numbers.map { it.first }.maxOrNull()!!.let { println("Part 1: $it") }

    val filtered = numbers.filter { it.second != 0 && it.second != 127 }.map { it.first }
    filtered.forEach { one ->
        filtered.forEach { two ->
            if (one - two == 2 && (one - 1) !in filtered) println("Part 2: ${one - 1}")
        }
    }
}