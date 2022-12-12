package dev.siller.aoc2022

private val example = """
    Monkey 0:
      Starting items: 79, 98
      Operation: new = old * 19
      Test: divisible by 23
        If true: throw to monkey 2
        If false: throw to monkey 3

    Monkey 1:
      Starting items: 54, 65, 75, 74
      Operation: new = old + 6
      Test: divisible by 19
        If true: throw to monkey 2
        If false: throw to monkey 0

    Monkey 2:
      Starting items: 79, 60, 97
      Operation: new = old * old
      Test: divisible by 13
        If true: throw to monkey 1
        If false: throw to monkey 3

    Monkey 3:
      Starting items: 74
      Operation: new = old + 3
      Test: divisible by 17
        If true: throw to monkey 0
        If false: throw to monkey 1
""".trimIndent()

@Suppress("LongParameterList", "DataClassShouldBeImmutable")
data class Monkey(
    val id: Int,
    var items: List<Long>,
    val operation: (Long) -> Long,
    val testModulo: Long,
    val test: (Long) -> Boolean,
    val ifTrueToMonkey: Int,
    val ifFalseToMonkey: Int,
    var itemsInspected: Int = 0
)

private fun fnIdentity(): (Long) -> Long = { old -> old }
private fun fnAdd(a: Long): (Long) -> Long = { old -> a + old }
private fun fnMultiply(a: Long): (Long) -> Long = { old -> a * old }
private fun fnSquare(): (Long) -> Long = { old -> old * old }
private fun testDivisibility(a: Long): (Long) -> Boolean = { item -> item % a == 0L }

private fun part1(input: List<String>): Long {
    val monkeys = getMonkeys(input)

    repeat(20) {
        monkeys.forEach { m ->
            m.items.forEach { i ->
                val lvl = m.operation(i) / 3
                val targetMonkey = if (m.test(lvl)) m.ifTrueToMonkey else m.ifFalseToMonkey

                monkeys[targetMonkey].items += lvl
                m.itemsInspected++
            }

            m.items = emptyList()
        }
    }

    return monkeys
        .sortedByDescending { it.itemsInspected }
        .take(2)
        .map { it.itemsInspected }
        .fold(1L) { acc, i -> acc * i }
}

private fun part2(input: List<String>): Long {
    val monkeys = getMonkeys(input)

    val mod = monkeys.map { it.testModulo }.fold(1L) { acc, m -> acc * m }

    repeat(10_000) {
        monkeys.forEach { m ->
            m.items.forEach { i ->
                val lvl = m.operation(i) % mod
                val targetMonkey = if (m.test(lvl)) m.ifTrueToMonkey else m.ifFalseToMonkey

                monkeys[targetMonkey].items += lvl
                m.itemsInspected++
            }

            m.items = emptyList()
        }
    }

    return monkeys
        .sortedByDescending { it.itemsInspected }
        .take(2)
        .map { it.itemsInspected }
        .fold(1L) { acc, i -> acc * i }
}

private fun getMonkeys(input: List<String>) = input.asSequence()
    .filter(String::isNotBlank)
    .chunked(6)
    .map { c -> c.joinToString("").replace("\\s+".toRegex(), " ") }
    .map { s ->
        var remainingString = s

        remainingString = remainingString.substringAfter(' ')
        val id = remainingString.substringBefore(':').toInt()

        remainingString = remainingString.substringAfter(':')

        remainingString = remainingString.substringAfter(':')
        val items = remainingString.substringBefore('O')
            .trim()
            .split(", ")
            .map(String::toLong)

        remainingString = remainingString.substringAfter(':')
        val operationString = remainingString.substringBefore("Test").trim()

        val operation = when {
            operationString == "new = old * old" -> fnSquare()
            operationString.startsWith("new = old + ") -> fnAdd(operationString.substringAfterLast(" ").toLong())
            operationString.startsWith("new = old * ") -> fnMultiply(operationString.substringAfterLast(" ").toLong())
            else -> fnIdentity()
        }

        remainingString = remainingString.substringAfter("by ")
        val testModulo = remainingString.substringBefore(" If").trim().toLong()
        val test = testDivisibility(testModulo)

        remainingString = remainingString.substringAfter("y ")
        val ifTrue = remainingString.substringBefore(" ").toInt()

        remainingString = remainingString.substringAfter("y ")
        val ifFalse = remainingString.substringBefore(" ").toInt()

        Monkey(id, items, operation, testModulo, test, ifTrue, ifFalse)
    }
    .sortedBy { it.id }
    .toList()

fun aocDay11() = aocTaskWithExample(
    day = 11,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 10_605L,
    expectedOutputPart2 = 2_713_310_158L
)

fun main() {
    aocDay11()
}
