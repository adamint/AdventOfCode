private val input = readInput("input1.txt")

// part 1
/*fun main() {
    val nums = input.split("\n").map { it.toInt() }
    nums.forEach { one ->
        if (one < 2020) {
            nums.forEach { two ->
                if (one + two == 2020) {
                    println(one * two)
                    return
                }
            }
        }
    }
}*/

// part 2
fun main() {
    val nums = input.split("\n").map { it.toInt() }
    nums.forEach { one ->
        if (one < 2020) {
            nums.forEach { two ->
                if (one + two < 2020) {
                    nums.forEach { three ->
                        if (one + two + three == 2020) {
                            println(one * two * three)
                            return
                        }
                    }
                }
            }
        }
    }
}