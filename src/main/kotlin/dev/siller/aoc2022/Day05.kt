package dev.siller.aoc2022

private val example = """
        [D]
    [N] [C]
    [Z] [M] [P]
     1   2   3

    move 1 from 2 to 1
    move 3 from 1 to 3
    move 2 from 2 to 1
    move 1 from 1 to 2
""".trimIndent()

private data class Instruction(val count: Int, val from: Int, val to: Int)

private fun part1(input: List<String>): String = getStacks(input).let { stacks ->
    getInstructions(input).forEach { i ->
        repeat(i.count) {
            val e = stacks.getOrDefault(i.from, emptyList()).takeLast(1)
            stacks[i.from] = stacks.getOrDefault(i.from, emptyList()).dropLast(1)
            stacks[i.to] = (stacks.getOrDefault(i.to, emptyList()) + e)
        }
    }

    stacks.mapValues { (_, v) -> v.last() }.toSortedMap().values.joinToString("")
}

private fun part2(input: List<String>): String = getStacks(input).let { stacks ->
    getInstructions(input).forEach { i ->
        val e = stacks.getOrDefault(i.from, emptyList()).takeLast(i.count)
        stacks[i.from] = stacks.getOrDefault(i.from, emptyList()).dropLast(i.count)
        stacks[i.to] = (stacks.getOrDefault(i.to, emptyList()) + e)
    }

    stacks.mapValues { (_, v) -> v.last() }.toSortedMap().values.joinToString("")
}

private fun getStacks(input: List<String>) = input
    .takeWhile { l -> l.trim().matches("(\\[[A-Z]]\\s*)+".toRegex()) }
    .map { l -> l.chunked(4).mapIndexed { index, s -> index to s.trim().drop(1).dropLast(1) }.toMap() }
    .fold(mutableMapOf<Int, List<String>>()) { acc, map ->
        map.forEach { (k, v) -> acc.merge(k, listOf(v)) { old, new -> old + new } }
        acc
    }
    .mapValues { (_, v) -> v.filter(String::isNotBlank).reversed() }
    .toMutableMap()

private fun getInstructions(input: List<String>) = input
    .dropWhile { l -> !l.matches("move [0-9]+ from [0-9]+ to [0-9]+".toRegex()) }
    .map { l ->
        val count = l.substringAfter("move ").substringBefore(" from").toInt()
        val from = l.substringAfter("from ").substringBefore(" to").toInt() - 1
        val to = l.substringAfter("to ").toInt() - 1

        Instruction(count, from, to)
    }

fun main() = aocTaskWithExample(
    day = 5,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = "CMZ",
    expectedOutputPart2 = "MCD"
)
