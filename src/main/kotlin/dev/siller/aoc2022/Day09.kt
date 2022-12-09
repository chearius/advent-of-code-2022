package dev.siller.aoc2022

import kotlin.math.sign

private val examples = listOf(
    """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent(),
    """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
    """.trimIndent()
)

data class Vector(val x: Int, val y: Int)

typealias Position = Vector

private operator fun Vector.plus(other: Vector) = Vector(x + other.x, y + other.y)

private val ORIGIN = Position(0, 0)

private val UP = Vector(0, 1)

private val DOWN = Vector(0, -1)

private val LEFT = Vector(-1, 0)

private val RIGHT = Vector(1, 0)

private fun Position.isTouching(other: Position): Boolean =
    other.x in x - 1..x + 1 && other.y in y - 1..y + 1

private fun Position.vectorTo(other: Position): Vector = Vector(other.x - x, other.y - y)

private fun part1(input: List<String>): Int {
    val visited = mutableSetOf<Position>()

    var headPosition = ORIGIN
    var tailPosition = ORIGIN

    visited += ORIGIN

    input.forEach { line ->
        val (direction, count) = getDirectionAndCount(line)

        repeat(count) {
            headPosition += direction

            if (!tailPosition.isTouching(headPosition)) {
                val (x, y) = tailPosition.vectorTo(headPosition)
                tailPosition += Vector(x.sign, y.sign)
                visited += tailPosition
            }
        }
    }

    return visited.size
}

private const val KNOTS_COUNT = 10
private fun part2(input: List<String>): Int {
    val visited = mutableSetOf<Position>()

    val knots = (0 until KNOTS_COUNT).map { ORIGIN }.toMutableList()

    visited += ORIGIN

    input.forEach { line ->
        val (direction, count) = getDirectionAndCount(line)

        repeat(count) {
            knots[0] += direction

            for (i in 1 until KNOTS_COUNT) {
                if (!knots[i].isTouching(knots[i - 1])) {
                    val (x, y) = knots[i].vectorTo(knots[i - 1])
                    knots[i] += Vector(x.sign, y.sign)

                    if (i == KNOTS_COUNT - 1) {
                        visited += knots[i]
                    }
                } else {
                    break
                }
            }
        }
    }

    return visited.size
}

private fun getDirectionAndCount(line: String): Pair<Vector, Int> {
    val direction = when (line.substringBefore(' ')) {
        "U" -> UP
        "D" -> DOWN
        "L" -> LEFT
        "R" -> RIGHT
        else -> error("Invalid direction ${line.substringBefore(' ')}")
    }

    val count = line.substringAfter(' ').toInt()
    return Pair(direction, count)
}

fun aocDay09() = aocTaskWithExamples(
    day = 9,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = examples,
    expectedOutputPart1 = listOf(13, 88),
    expectedOutputPart2 = listOf(1, 36)
)

fun main() {
    aocDay09()
}
