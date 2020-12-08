package aoc2020

import common.*

class Aoc2020Day4 : AocProblem(2020, 4) {
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    override fun solvePart1(): Any {
        return splitByBlankLine.count { passport ->
            passport.split(" ", "\n").filter { it.isNotBlank() }
                .map { it.split(":")[0] to it.split(":")[1] }
                .toMap().keys.containsAll(requiredFields)
        }
    }

    override fun solvePart2(): Any {
        return input.split("\n\n").count { passport ->
            val found =
                passport.split(" ", "\n").filter { it.isNotBlank() }.map { it.split(":")[0] to it.split(":")[1] }
                    .toMap()
            if (!found.keys.containsAll(requiredFields)) false
            else {
                found["byr"]?.toIntOrNull()?.let { it in 1920..2002 } == true &&
                        found["iyr"]?.toIntOrNull()?.let { it in 2010..2020 } == true &&
                        found["eyr"]?.toIntOrNull()?.let { it in 2020..2030 } == true &&
                        found["pid"]?.toIntOrNull()?.let { found["pid"]!!.length == 9 } == true &&
                        found["ecl"] in "amb blu brn gry grn hzl oth".split(" ") &&
                        found["hcl"]!![0] == '#' &&
                        found["hcl"]!!.substring(found["hcl"]!!.length - 6).all { it in '0'..'9' || it in 'a'..'f' } &&
                        if (found["hgt"]!!.endsWith("cm") || found["hgt"]!!.endsWith("in")) {
                            if (found["hgt"]!!.endsWith("cm")) found["hgt"]!!.removeSuffix("cm").toInt() in 150..193
                            else found["hgt"]!!.removeSuffix("in").toInt() in 59..76
                        } else false
            }
        }
    }
}

fun main() {
    Aoc2020Day4().solve()
}

/*
    input.split("\n\n").filter { passport ->
        val found =
            passport.split(" ", "\n").filter { it.isNotBlank() }.map { it.split(":")[0] to it.split(":")[1] }.toMap()
        if (!found.keys.containsAll(all)) false
        else {
            found["byr"]?.toIntOrNull()?.let { it in 1920..2002 } == true &&
                    found["iyr"]?.toIntOrNull()?.let { it in 2010..2020 } == true &&
                    found["eyr"]?.toIntOrNull()?.let { it in 2020..2030 } == true &&
                    found["pid"]?.toIntOrNull()?.let { found["pid"]!!.length == 9 } == true &&
                    found["ecl"] in "amb blu brn gry grn hzl oth".split(" ") &&
                    found["hcl"]!![0] == '#'
                    && found["hcl"]!!.substring(found["hcl"]!!.length - 6).all { it in '0'..'9' || it in 'a'..'f' }
                    && if (found["hgt"]!!.endsWith("cm") || found["hgt"]!!.endsWith("in")) {
                if (found["hgt"]!!.endsWith("cm")) found["hgt"]!!.removeSuffix("cm").toInt() in 150..193
                else found["hgt"]!!.removeSuffix("in").toInt() in 59..76
            } else false
        }
    }.size.let { println("Part 2: $it") }

 */