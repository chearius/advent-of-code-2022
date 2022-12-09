package dev.siller.aoc2022

private val example = """
    A Y
    B X
    C Z
""".trimIndent()

private const val WIN = 6

private const val DRAW = 3

private const val LOSS = 0

private fun part1(input: List<String>): Int = input
    .map {
        val opponent = it[0] - 'A' + 1
        val me = it[2] - 'X' + 1

        me + when (me - opponent) {
            1, -2 -> WIN
            0 -> DRAW
            -1, 2 -> LOSS
            else -> 0
        }
    }
    .sum()

private fun part2(input: List<String>): Int = input
    .map {
        val opponent = it[0] - 'A' + 1
        val outcome = (it[2] - 'X') * 3

        outcome + when (outcome) {
            LOSS -> if (opponent > 1) opponent - 1 else 3
            DRAW -> opponent
            WIN -> if (opponent < 3) opponent + 1 else 1
            else -> 0
        }
    }
    .sum()

fun aocDay02() = aocTaskWithExample(
    day = 2,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 15,
    expectedOutputPart2 = 12
)

fun main() {
    aocDay02()
}
