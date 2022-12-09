package dev.siller.aoc2022

private val examples = listOf(
    "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
    "bvwbjplbgvbhsrlpgdmjqwftvncz",
    "nppdvjthqldpwncqszvftbrmjlhg",
    "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
    "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
)

private fun part1(input: List<String>): Int = getStartOfMessagePosition(input, 4)
private fun part2(input: List<String>): Int = getStartOfMessagePosition(input, 14)

private fun getStartOfMessagePosition(input: List<String>, markerLength: Int) = input.first()
    .let { line ->
        val chars = line.toList()
        var marker = chars.take(markerLength)
        var startOfData = markerLength

        while (marker.distinct().size != markerLength) {
            startOfData++
            marker = chars.drop(startOfData - markerLength).take(markerLength)
        }

        startOfData
    }

fun main() = aocTaskWithExamples(
    day = 6,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = examples,
    expectedOutputPart1 = listOf(7, 5, 6, 10, 11),
    expectedOutputPart2 = listOf(19, 23, 23, 29, 26)
)
