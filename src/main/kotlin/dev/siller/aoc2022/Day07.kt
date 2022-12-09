package dev.siller.aoc2022

private val example = """
    ${'$'} cd /
    ${'$'} ls
    dir a
    14848514 b.txt
    8504156 c.dat
    dir d
    ${'$'} cd a
    ${'$'} ls
    dir e
    29116 f
    2557 g
    62596 h.lst
    ${'$'} cd e
    ${'$'} ls
    584 i
    ${'$'} cd ..
    ${'$'} cd ..
    ${'$'} cd d
    ${'$'} ls
    4060174 j
    8033020 d.log
    5626152 d.ext
    7214296 k
""".trimIndent()

private interface FSItem {
    val name: String
    val parent: FSDir?
    val size: Int
}

private data class FSFile(override val name: String, override val parent: FSDir?, override val size: Int) : FSItem {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FSFile) return false

        if (name != other.name) return false
        if (parent != other.parent) return false
        if (size != other.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + size
        return result
    }

    override fun toString(): String = "FSFile(name='$name', size=$size)"
}

private class FSDir(
    override val name: String,
    override val parent: FSDir?,
    val items: MutableSet<FSItem> = mutableSetOf()
) : FSItem {
    override val size: Int
        get() = items.map(FSItem::size).sum()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FSDir) return false

        if (name != other.name) return false
        if (parent != other.parent) return false

        return true
    }

    fun flatten(): List<FSDir> = items.filterIsInstance(FSDir::class.java).flatMap(FSDir::flatten) + this

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (parent?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "FSDir(name='$name', items=$items)"
}

private const val TOTAL_DISK_SIZE = 70_000_000

private const val FREE_SPACE_NEEDED = 30_000_000

private fun part1(input: List<String>): Int = getFileSystem(input)
    .flatten()
    .map(FSDir::size)
    .filter { s -> s < 100_000 }
    .sum()

private fun part2(input: List<String>): Int = getFileSystem(input).let { rootDir ->
    val currentFreeSpace = TOTAL_DISK_SIZE - rootDir.size
    val spaceToBeFreed = FREE_SPACE_NEEDED - currentFreeSpace

    rootDir.flatten()
        .map(FSDir::size)
        .filter { size -> size >= spaceToBeFreed }
        .min()
}

private fun getFileSystem(input: List<String>): FSDir {
    val rootDir = FSDir("/", null)

    var currentDir = rootDir

    input.forEach { line ->
        when {
            line == "$ cd /" -> currentDir = rootDir
            line == "$ cd .." -> currentDir = currentDir.parent ?: rootDir
            line.matches("\\$ cd .+".toRegex()) ->
                currentDir = currentDir.items
                    .filterIsInstance(FSDir::class.java)
                    .find { d -> d.name == line.substringAfter("cd ") }
                    ?: error("Unknown dir to cd")
            line == "$ ls" -> {}
            line.matches("dir .+".toRegex()) ->
                currentDir.items += FSDir(line.substringAfter(' '), currentDir)
            line.matches("[0-9]+ .+".toRegex()) ->
                currentDir.items += FSFile(line.substringAfter(' '), currentDir, line.substringBefore(' ').toInt())
            else -> error("Unknown command $line")
        }
    }

    return rootDir
}

fun aocDay07() = aocTaskWithExample(
    day = 7,
    part1 = ::part1,
    part2 = ::part2,
    exampleInput = example,
    expectedOutputPart1 = 95_437,
    expectedOutputPart2 = 24_933_642
)

fun main() {
    aocDay07()
}
