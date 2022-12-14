package dev.siller.aoc2022

import java.io.File
import java.util.Optional

private const val AOC_YEAR = 2022

private enum class ResultStatus {
    SUCCESS,
    ERROR
}

@Suppress("LongParameterList")
fun <R : Any> aocTaskWithExample(
    day: Int,
    part1: ((List<String>) -> R)? = null,
    part2: ((List<String>) -> R)? = null,
    exampleInput: String = "",
    expectedOutputPart1: R? = null,
    expectedOutputPart2: R? = null,
    inputFile: String = "input/${AOC_YEAR}_day_${day.toString().padStart(2, '0')}.txt"
): Pair<Optional<R>, Optional<R>> = aocTaskWithExamples(
    day,
    part1,
    part2,
    listOf(exampleInput),
    if (expectedOutputPart1 != null) listOf(expectedOutputPart1) else emptyList(),
    if (expectedOutputPart2 != null) listOf(expectedOutputPart2) else emptyList(),
    inputFile
)

@Suppress("LongParameterList", "ReturnCount")
fun <R : Any> aocTaskWithExamples(
    day: Int,
    part1: ((List<String>) -> R)? = null,
    part2: ((List<String>) -> R)? = null,
    exampleInput: List<String> = listOf(""),
    expectedOutputPart1: List<R> = emptyList(),
    expectedOutputPart2: List<R> = emptyList(),
    input: String = "input/2022_day_${day.toString().padStart(2, '0')}.txt"
): Pair<Optional<R>, Optional<R>> {
    println("Day ${day.toString().padStart(2, '0')} of Advent of Code $AOC_YEAR")
    println("=============================")

    if (part1 == null) {
        println("No solution for part 1, yet!")
        return Pair(Optional.empty(), Optional.empty())
    }

    val part1result = runPart(1, input, part1, exampleInput, expectedOutputPart1)

    if (part1result.isEmpty) {
        return Pair(Optional.empty(), Optional.empty())
    }

    if (part2 == null) {
        println("No solution for part 2, yet!")
        return Pair(part1result, Optional.empty())
    }

    val part2result = runPart(2, input, part2, exampleInput, expectedOutputPart2)

    if (part2result.isPresent) {
        println("[DONE] :-)")
    }

    return Pair(part1result, part2result)
}

@Suppress("ReturnCount")
private fun <R : Any> runPart(
    partNumber: Int,
    input: String,
    part: (List<String>) -> R,
    exampleInput: List<String>,
    expectedOutput: List<R>
): Optional<R> {
    val states = exampleInput.mapIndexed { index, i ->
        println("Running Part $partNumber for example ${index + 1} of ${exampleInput.size}:")

        val lines = i.lines()

        val result = part(lines)

        val status = if (result == expectedOutput.getOrNull(index)) {
            ResultStatus.SUCCESS
        } else {
            ResultStatus.ERROR
        }

        println("Expected result: ${expectedOutput.getOrNull(index) ?: "<not specified>"}")
        println("Actual result:   $result ($status)")
        println("---")

        status
    }

    if (states.any { rs -> rs == ResultStatus.ERROR }) {
        println("There were some incorrect results running the examples!")
        return Optional.empty()
    }

    if (!File(input).run { exists() && canRead() }) {
        println("Input file $input does not exist or is not readable!")
        println("Did you forget to download your puzzle input?")
        return Optional.empty()
    }

    println("Running Part $partNumber with puzzle input ...")
    val partResult = part(File(input).readLines())

    println("Part $partNumber result is: $partResult")
    println("=============================")

    return Optional.of(partResult)
}
