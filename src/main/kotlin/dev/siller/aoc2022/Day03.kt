package dev.siller.aoc2022

private val example = """
    vJrwpWtwJgWrhcsFMMfFFhFp
    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
    PmmdzqPrVvPwwTWBwg
    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
    ttgJtRGJQctTZtZT
    CrZsJsPPZsGzwwsLwLmpwMDw
""".trimIndent()

private fun part1(input: List<String>): Int = input
    .map { l -> l.toList() }
    .map { l ->
        val comp1 = l.take(l.size / 2).toSet()
        val comp2 = l.drop(l.size / 2).toSet()

        comp1.intersect(comp2)
    }
    .flatten()
    .sumOf { c ->
        if (c in 'a'..'z') {
            c - 'a' + 1
        } else {
            c - 'A' + 27
        }
    }

private fun part2(input: List<String>): Int = input
    .map { l -> l.toList() }
    .chunked(3)
    .map { g -> g.map { l -> l.toSet() } }
    .map { g -> g[0].intersect(g[1]).intersect(g[2]) }
    .flatten()
    .sumOf { b ->
        if (b in 'a'..'z') {
            b - 'a' + 1
        } else {
            b - 'A' + 27
        }
    }

fun aocDay03() = aocTaskWithExample(
    day = 3,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 157,
    expectedOutputPart2 = 70
)

fun main() {
    aocDay03()
}
