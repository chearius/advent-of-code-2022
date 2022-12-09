package dev.siller.aoc2022

private val example = """
    1000
    2000
    3000

    4000

    5000
    6000

    7000
    8000
    9000

    10000
""".trimIndent()

private fun part1(input: List<String>): Int =
    calculateCaloriesPerElf(input).max()

private fun part2(input: List<String>): Int =
    calculateCaloriesPerElf(input)
        .sortedDescending()
        .take(3)
        .sum()

private fun calculateCaloriesPerElf(input: List<String>) =
    input.foldIndexed(ArrayList<ArrayList<Int>>()) { index, acc, item ->
        if (index == 0 || item.isBlank()) {
            acc.add(ArrayList())
        } else {
            acc.last().add(item.toInt())
        }

        acc
    }.map { l ->
        l.sum()
    }

fun aocDay01() = aocTaskWithExample(
    day = 1,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 24_000,
    expectedOutputPart2 = 45_000
)

fun main() {
    aocDay01()
}
