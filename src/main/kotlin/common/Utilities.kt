package common

import java.awt.*
import java.awt.datatransfer.*
import java.io.*
import kotlin.math.*


fun readInput(filename: String): String {
    return File(ClassLoader.getSystemResource(filename).file).readText()
}

fun <T> Collection<T>.without(element: T) = toMutableList().apply { remove(element) }

fun String.isInt() = toIntOrNull() != null

fun <T, U, V> product(xs: Collection<T>, ys: Collection<U>, combiner: (T, U) -> V): List<V> =
    xs.flatMap { x ->
        ys.map { y ->
            combiner(x, y)
        }
    }

fun <T> List<T>.times(repetitions: Int, current: List<List<T>> = listOf()): List<List<T>> {
    return when {
        repetitions == 0 -> current
        current.isEmpty() -> times(repetitions - 1, map { listOf(it) })
        else -> {
            times(repetitions - 1, current.map { list ->
                map { value -> list + value }
            }.flatten())
        }
    }
}

fun <T> List<T>.times(
    other: List<T>,
    repetitions: Int,
    current: List<List<T>> = listOf(),
    cull: ((List<T>) -> Boolean)? = null
): List<List<T>> {
    return when {
        repetitions == 0 -> current
        current.isEmpty() -> times(other, repetitions - 1, map { listOf(it) }, cull)
        else -> {
            val addedValues = current.map { list -> other.map { value -> list + value } }.flatten().toMutableList()
            if (cull != null && repetitions > 1) addedValues.removeIf { cull(it) }
            times(other, repetitions - 1, addedValues, cull)
        }
    }
}


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

fun List<Double>.product(): Double {
    var index = 0
    return generateSequence(1.toDouble()) { prev -> index++.let { if (it in indices) prev * this[it] else null } }.last()
}

fun List<Long>.product(): Long {
    var index = 0
    return generateSequence(1.toLong()) { prev -> index++.let { if (it in indices) prev * this[it] else null } }.last()
}

fun List<Int>.product(): Int {
    var index = 0
    return generateSequence(1) { prev -> index++.let { if (it in indices) prev * this[it] else null } }.last()
}

fun <T> List<T>.allDistinct(): Boolean = distinct().size == size

fun Regex.matchLines(lines: List<String>) = lines.map { matchEntire(it) }

infix fun Any.eqs(other: Any) = this.toString() == other.toString()

fun writeToClipboard(text: Any) {
    val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val transferable: Transferable = StringSelection(text.toString())
    clipboard.setContents(transferable, null)
}

fun main() {
    var index = 0
    println(listOf(1, 2, 3, 4).times(2))
    println(listOf(1, 2).times(listOf(3, 4), 2))

    (1..100).toList()
        .chunkedBy { sqrt(it.toDouble()).toInt().toDouble() == sqrt(it.toDouble()) }
        .let { println(it) }
}