package dev.siller.aoc2022

private val example = """
    Sabqponm
    abcryxxl
    accszExk
    acctuvwj
    abdefghi
""".trimIndent()

data class Coordinates(val x: Int, val y: Int)

data class Point(
    val x: Int,
    val y: Int,
    val elevation: Int,
    val startPoint: Boolean = false,
    val endPoint: Boolean = false
)

private fun part1(input: List<String>): Int = getShortestPathLength(input) { points ->
    setOf(points.first { p -> p.startPoint })
}

private fun part2(input: List<String>): Int = getShortestPathLength(input) { points ->
    points.filter { p -> p.elevation == 1 }.toSet()
}

private fun getShortestPathLength(input: List<String>, startingPointsSelector: (Set<Point>) -> Set<Point>): Int {
    val points = input.flatMapIndexed { rowIndex, line ->
        line.toList().mapIndexed { columnIndex, e ->
            val elevation = when (e) {
                in 'a'..'z' -> e - 'a' + 1
                'S' -> 1
                'E' -> 'z' - 'a' + 1
                else -> 0
            }

            Point(columnIndex, rowIndex, elevation, e == 'S', e == 'E')
        }
    }.toSet()

    val endPoint = points.first { p -> p.endPoint }

    val map = points.associateBy { p -> Coordinates(p.x, p.y) }

    var steps = 0
    var currentPoints = startingPointsSelector(points)

    while (true) {
        steps++

        currentPoints = currentPoints.flatMap { currentPoint ->
            setOfNotNull(
                map[Coordinates(currentPoint.x + 1, currentPoint.y)],
                map[Coordinates(currentPoint.x, currentPoint.y + 1)],
                map[Coordinates(currentPoint.x - 1, currentPoint.y)],
                map[Coordinates(currentPoint.x, currentPoint.y - 1)]
            ).filter { p -> p.elevation <= currentPoint.elevation + 1 }
        }.toSet()

        if (endPoint in currentPoints) {
            break
        }
    }

    return steps
}

fun aocDay12() = aocTaskWithExample(
    day = 12,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 31,
    expectedOutputPart2 = 29
)

fun main() {
    aocDay12()
}
