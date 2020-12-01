import java.io.File

fun readInput(filename: String): String {
    return File(ClassLoader.getSystemResource(filename).file).readText()
}