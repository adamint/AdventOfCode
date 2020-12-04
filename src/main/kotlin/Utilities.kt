import java.io.File

fun readInput(filename: String): String {
    return File(ClassLoader.getSystemResource(filename).file).readText()
}

fun <T> Collection<T>.without(element: T) = toMutableList().apply { remove(element) }


fun <T, U, V> product(xs: Collection<T>, ys: Collection<U>, combiner: (T, U) -> V): Collection<V> =
    xs.flatMap { x ->
        ys.map { y ->
            combiner(x, y)
        }
    }

operator fun <T, U> Set<T>.times(ys: Set<U>): Set<Set<*>> =
    product(this, ys) { x, y ->
        if (x is Set<*>) x + y // keeps the result flat
        else setOf(x, y)
    }.toSet()

operator fun <T, U> List<T>.times(ys: List<U>): List<List<*>> =
    product(this, ys) { x, y ->
        if (x is List<*>) x + y // keeps the result flat
        else listOf(x, y)
    }.toList()

operator fun Int.plus(boolean: Boolean): Int {
    return if (boolean) this + 1
    else this
}