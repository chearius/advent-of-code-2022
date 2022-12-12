package dev.siller.aoc2022

private val example = """
    addx 15
    addx -11
    addx 6
    addx -3
    addx 5
    addx -1
    addx -8
    addx 13
    addx 4
    noop
    addx -1
    addx 5
    addx -1
    addx 5
    addx -1
    addx 5
    addx -1
    addx 5
    addx -1
    addx -35
    addx 1
    addx 24
    addx -19
    addx 1
    addx 16
    addx -11
    noop
    noop
    addx 21
    addx -15
    noop
    noop
    addx -3
    addx 9
    addx 1
    addx -3
    addx 8
    addx 1
    addx 5
    noop
    noop
    noop
    noop
    noop
    addx -36
    noop
    addx 1
    addx 7
    noop
    noop
    noop
    addx 2
    addx 6
    noop
    noop
    noop
    noop
    noop
    addx 1
    noop
    noop
    addx 7
    addx 1
    noop
    addx -13
    addx 13
    addx 7
    noop
    addx 1
    addx -33
    noop
    noop
    noop
    addx 2
    noop
    noop
    noop
    addx 8
    noop
    addx -1
    addx 2
    addx 1
    noop
    addx 17
    addx -9
    addx 1
    addx 1
    addx -3
    addx 11
    noop
    noop
    addx 1
    noop
    addx 1
    noop
    noop
    addx -13
    addx -19
    addx 1
    addx 3
    addx 26
    addx -30
    addx 12
    addx -1
    addx 3
    addx 1
    noop
    noop
    noop
    addx -9
    addx 18
    addx 1
    addx 2
    noop
    noop
    addx 9
    noop
    noop
    noop
    addx -1
    addx 2
    addx -37
    addx 1
    addx 3
    noop
    addx 15
    addx -21
    addx 22
    addx -6
    addx 1
    noop
    addx 2
    addx 1
    noop
    addx -10
    noop
    noop
    addx 20
    addx 1
    addx 2
    addx 2
    addx -6
    addx -11
    noop
    noop
    noop
""".trimIndent()

data class State(val cycle: Int, val regX: Int)

private fun part1(input: List<String>): Int = getStates(input)
    .filter { s -> (s.cycle - 20) % 40 == 0 }
    .sumOf { s -> s.cycle * s.regX }

private fun part2(input: List<String>): Int = getStates(input)
    .map { s -> if (s.cycle % 40 - 1 in s.regX - 1..s.regX + 1) "#" else "." }
    .chunked(40)
    .joinToString("\n") { l ->
        l.joinToString("")
    }
    .let {
        println(it)

        it.length
    }

private fun getStates(input: List<String>): List<State> {
    var regX = 1
    var cycle = 1

    return input.flatMap { l ->
        when {
            l == "noop" -> listOf(State(cycle++, regX))
            l.startsWith("addx ") -> {
                val incr = l.substringAfter(' ').toInt()
                val res = listOf(State(cycle++, regX), State(cycle++, regX))
                regX += incr
                res
            }

            else -> emptyList()
        }
    }
}

fun aocDay10() = aocTaskWithExample(
    day = 10,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 13_140,
    expectedOutputPart2 = 245
)

fun main() {
    aocDay10()
}
