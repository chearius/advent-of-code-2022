package dev.siller.aoc2022

private val example = """
    2-4,6-8
    2-3,4-5
    5-7,7-9
    2-8,3-7
    6-6,4-6
    2-6,4-8
""".trimIndent()

private fun part1(input: List<String>): Int = getRanges(input)
    .count { (r1, r2) ->
        r1.first <= r2.first && r1.last >= r2.last || r2.first <= r1.first && r2.last >= r1.last
    }

private fun part2(input: List<String>): Int = getRanges(input)
    .count { (r1, r2) ->
        r1.first <= r2.first && r1.last >= r2.first || r2.first <= r1.first && r2.last >= r1.first
    }

private fun getRanges(input: List<String>) = input
    .map { l ->
        val (s1, s2) = l.split(",", limit = 2)
        val r1 = s1.substringBefore("-").toInt()..s1.substringAfter("-").toInt()
        val r2 = s2.substringBefore("-").toInt()..s2.substringAfter("-").toInt()
        r1 to r2
    }

fun aocDay04() = aocTaskWithExample(
    day = 4,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 2,
    expectedOutputPart2 = 4
)

fun main() {
    aocDay04()
}
