package dev.siller.aoc2022

import kotlin.math.max

private val example = """
    30373
    25512
    65332
    33549
    35390
""".trimIndent()

private fun part1(input: List<String>): Int = input
    .flatMap { l -> l.toList().map { c -> c.digitToInt() } }
    .let { forest ->
        val height = input.size
        val width = forest.size / height
        val treeCount = forest.size

        val edges = 2 * width + 2 * (height - 2)

        edges + forest.indices
            .toList()
            .filterNot { i -> i < width || i >= treeCount - width || i % width == 0 || i % width == width - 1 }
            .map { index ->
                val treeHeight = forest[index]
                val currentRow = index / width
                val currentColumn = index % width

                val visible = (0 until currentRow).map { row -> forest[row * width + currentColumn] }.all { h -> h < treeHeight } ||
                    ((currentRow + 1) until height).map { row -> forest[row * width + currentColumn] }.all { h -> h < treeHeight } ||
                    (0 until currentColumn).map { column -> forest[currentRow * width + column] }.all { h -> h < treeHeight } ||
                    ((currentColumn + 1) until width).map { column -> forest[currentRow * width + column] }.all { h -> h < treeHeight }

                visible
            }.count { visible ->
                visible
            }
    }
private fun part2(input: List<String>): Int = input
    .flatMap { l -> l.toList().map { c -> c.digitToInt() } }
    .let { forest ->
        val height = input.size
        val width = forest.size / height
        val treeCount = forest.size

        forest.indices
            .toList()
            .filterNot { i -> i < width || i >= treeCount - width || i % width == 0 || i % width == width - 1 }
            .map { index ->
                val treeHeight = forest[index]
                val currentRow = index / width
                val currentColumn = index % width

                val up = currentRow - max(0,
                    (0 until currentRow)
                        .map { row -> forest[row * width + currentColumn] }
                        .indexOfLast { h -> h >= treeHeight })
                val down = height - currentRow -1 -max(0,
                    ((currentRow + 1) until height)
                        .reversed()
                        .map { row -> forest[row * width + currentColumn] }
                        .indexOfLast { h -> h >= treeHeight  }
                )
                val left = currentColumn - max(0,
                    (0 until currentColumn)
                        .map { column -> forest[currentRow * width + column] }
                        .indexOfLast { h -> h >= treeHeight }
                )
                val right = width - currentColumn - 1 - max(0,
                    ((currentColumn + 1) until width)
                        .reversed()
                        .map { column -> forest[currentRow * width + column] }
                        .indexOfLast { h -> h >= treeHeight  }
                )

                up * left * down * right
            }
            .max()
    }

fun main() = aocTaskWithExample(
    day = 8,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 21,
    expectedOutputPart2 = 8
)
