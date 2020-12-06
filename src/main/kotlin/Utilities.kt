import java.io.File
import kotlin.math.sqrt

fun readInput(filename: String): String {
    return File(ClassLoader.getSystemResource(filename).file).readText()
}

fun <T> Collection<T>.without(element: T) = toMutableList().apply { remove(element) }

fun String.isInt() = toIntOrNull() != null

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

fun <T, R> List<T>.chunkedBy(key: (T) -> R): List<Pair<R, List<T>>> {
    val chunks = mutableListOf<Pair<R, List<T>>>()
    var startingIndex = 0
    forEachIndexed { index, t ->
        val value = key(t)
        if (value != key(this[startingIndex])) {
            chunks += key(this[startingIndex]) to subList(startingIndex, index)
            startingIndex = index
        }
    }
    if (startingIndex < size) chunks += key(this[startingIndex]) to subList(startingIndex, size)
    return chunks
}

fun <T, R> Sequence<T>.chunkedBy(key: (T) -> R) = sequence {
    val iter = this@chunkedBy.iterator()
    if (!iter.hasNext()) return@sequence
    var currentList = mutableListOf(iter.next())
    var currentKey = key(currentList.first())
    for (t in iter) {
        val nextKey = key(t)
        if (nextKey == currentKey) {
            currentList.add(t)
            continue
        }
        yield(currentKey to currentList)
        currentKey = nextKey
        currentList = mutableListOf(t)
    }
    yield(currentKey to currentList)
}

fun main() {
    (1..100).toList()
        .chunkedBy { sqrt(it.toDouble()).toInt().toDouble() == sqrt(it.toDouble()) }
        .let { println(it) }
}