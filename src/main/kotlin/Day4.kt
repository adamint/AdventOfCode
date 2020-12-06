private val input = readInput("input4.txt")

fun main() {
    val all = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    input.mapToPassports().filterNotNull().size.let { println("Part 1: $it") }

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
}

fun String.toPassport(): Passport? {
    val map = split(" ", "\n").filter { it.isNotBlank() }.map { it.split(":")[0] to it.split(":")[1] }.toMap()
    val byr = map["byr"]?.toIntOrNull() ?: return null
    val iyr = map["iyr"]?.toIntOrNull() ?: return null
    val eyr = map["eyr"]?.toIntOrNull() ?: return null
    val pid = map["pid"]?.toIntOrNull() ?: return null
    if (map["pid"]!!.length != 9) return null
    val cid = map["cid"]?.toIntOrNull() // can be null ?: return null
    val ecl = map["ecl"] ?: return null
    val hcl = map["hcl"] ?: return null
    val hgt = map["hgt"] ?: ""
    val heightUnit = when {
        hgt.endsWith("cm") -> "cm"
        hgt.endsWith("in") -> "in"
        else -> hgt
    }
    val height = hgt.removeSuffix(heightUnit).toIntOrNull() ?: return null
    return Passport(byr, iyr, eyr, height, heightUnit, hcl, ecl, pid, cid)
}

/**
 * @param byr Birth year
 * @param iyr Issue Year
 * @param eyr Expiration Year
 * @param height Height
 * @param hairColor Hair Color
 * @param eyeColor Eye Color
 * @param pid Passport ID
 * @param cid Country ID
 */
data class Passport(
    val byr: Int,
    val iyr: Int,
    val eyr: Int,
    val height: Int,
    val heightUnit: String,
    val hairColor: String,
    val eyeColor: String,
    val pid: Int,
    val cid: Int?
) {
    fun validate(): Boolean {
        return (byr in 1920..2002
                && iyr in 2010..2020
                && eyr in 2020..2030
                && eyeColor in "amb blu brn gry grn hzl oth".split(" ")
                && hairColor[0] == '#'
                && hairColor.substring(1).all { it in '0'..'9' || it in 'a'..'f' }
                && if (heightUnit == "cm") height in 150..193 else height in 59..76)
    }
}

fun String.mapToPassports(): List<Passport?> {
    return input.split("\n\n").map { passport ->
        passport.toPassport()
    }
}