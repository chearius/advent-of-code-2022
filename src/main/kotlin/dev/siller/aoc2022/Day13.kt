@file:Suppress("TooManyFunctions")

package dev.siller.aoc2022

import kotlin.math.min

private val example = """
    [1,1,3,1,1]
    [1,1,5,1,1]

    [[1],[2,3,4]]
    [[1],4]

    [9]
    [[8,7,6]]

    [[4,4],4,4]
    [[4,4],4,4,4]

    [7,7,7,7]
    [7,7,7]

    []
    [3]

    [[[]]]
    [[]]

    [1,[2,[3,[4,[5,6,7]]]],8,9]
    [1,[2,[3,[4,[5,6,0]]]],8,9]
""".trimIndent()

private interface Datagram

private data class NumberDatagram(val number: Int) : Datagram

private fun NumberDatagram.toListDatagram(): ListDatagram = ListDatagram(listOf(this))

private data class ListDatagram(val list: List<Datagram>) : Datagram

private enum class ComparisonResult {
    RIGHT_ORDER,
    UNDECIDED,
    WRONG_ORDER
}

private class ListDatagramComparator : Comparator<ListDatagram> {
    override fun compare(o1: ListDatagram?, o2: ListDatagram?): Int {
        if (o1 == null || o2 == null) {
            return 0
        }

        return when (compareOrder(o1, o2)) {
            ComparisonResult.RIGHT_ORDER -> -1
            ComparisonResult.UNDECIDED -> 0
            ComparisonResult.WRONG_ORDER -> 1
        }
    }
}

private operator fun ListDatagram.plus(number: NumberDatagram) = ListDatagram(list + number)

private operator fun ListDatagram.plus(other: ListDatagram) = ListDatagram(list + other)

private fun part1(input: List<String>): Int = input
    .asSequence()
    .filter(String::isNotBlank)
    .chunked(2)
    .map { (first, second) -> getPacketContent(first) to getPacketContent(second) }
    .mapIndexed { index, (left, right) ->
        if (compareOrder(left, right) == ComparisonResult.RIGHT_ORDER) {
            index + 1
        } else {
            0
        }
    }
    .sum()

private fun part2(input: List<String>): Int {
    val marker1 = getPacketContent("[[2]]")
    val marker2 = getPacketContent("[[6]]")

    val packets = (input.asSequence().filter(String::isNotBlank).map(::getPacketContent) + marker1 + marker2)
        .sortedWith(ListDatagramComparator())

    val indexMarker1 = packets.indexOfFirst { p -> p == marker1 } + 1
    val indexMarker2 = packets.indexOfFirst { p -> p == marker2 } + 1

    return indexMarker1 * indexMarker2
}

private fun getPacketContent(packet: String): ListDatagram {
    val stack = ArrayDeque<ListDatagram>()

    var tempString = ""

    for (c in packet) {
        when (c) {
            '[' -> stack += ListDatagram(emptyList())
            in '0'..'9' -> tempString += c
            ',' -> if (tempString.isNotEmpty()) {
                stack += stack.removeLast() + NumberDatagram(tempString.toInt())
                tempString = ""
            }
            ']' -> {
                if (tempString.isNotEmpty()) {
                    stack += stack.removeLast() + NumberDatagram(tempString.toInt())
                    tempString = ""
                }

                val innerListDatagram = stack.removeLast()
                var outerListDatagram = stack.removeLastOrNull()

                if (outerListDatagram != null) {
                    outerListDatagram += innerListDatagram
                } else {
                    outerListDatagram = innerListDatagram
                }

                stack += outerListDatagram
            }
        }
    }

    return stack.last()
}

private fun compareOrder(left: Datagram, right: Datagram): ComparisonResult =
    if (left is NumberDatagram && right is NumberDatagram) {
        compareOrder(left, right)
    } else {
        val leftList = if (left is NumberDatagram) left.toListDatagram() else left as ListDatagram
        val rightList = if (right is NumberDatagram) right.toListDatagram() else right as ListDatagram

        compareOrder(leftList, rightList)
    }

private fun compareOrder(left: NumberDatagram, right: NumberDatagram): ComparisonResult = when {
    left.number < right.number -> ComparisonResult.RIGHT_ORDER
    left.number == right.number -> ComparisonResult.UNDECIDED
    else -> ComparisonResult.WRONG_ORDER
}

private fun compareOrder(left: ListDatagram, right: ListDatagram): ComparisonResult {
    for (i in 0 until min(left.list.size, right.list.size)) {
        val result = compareOrder(left.list[i], right.list[i])

        if (result != ComparisonResult.UNDECIDED) {
            return result
        }
    }

    return when {
        left.list.size < right.list.size -> ComparisonResult.RIGHT_ORDER
        left.list.size == right.list.size -> ComparisonResult.UNDECIDED
        else -> ComparisonResult.WRONG_ORDER
    }
}

fun aocDay13() = aocTaskWithExample(
    day = 13,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 13,
    expectedOutputPart2 = 140
)

fun main() {
    aocDay13()
}
