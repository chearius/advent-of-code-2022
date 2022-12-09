@file:Suppress("FunctionOnlyReturningConstant", "UnusedPrivateMember")

package dev.siller.aoc2022

private val example = """
""".trimIndent()

@Suppress("UNUSED_PARAMETER")
private fun part1(input: List<String>): Int = 0

@Suppress("UNUSED", "UNUSED_PARAMETER")
private fun part2(input: List<String>): Int = 0

fun aocDayXX() = aocTaskWithExample(
    day = 0,
    part1 = ::part1,
//    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 0,
    expectedOutputPart2 = 0
)

fun main() {
    aocDayXX()
}
