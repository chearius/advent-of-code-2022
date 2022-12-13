package dev.siller.aoc2022

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Aoc2022Tests {
    @Test
    fun `Run tests for Day 01`() {
        val (resultPart1, resultPart2) = aocDay01()

        assert(resultPart1.isPresent)
        assertEquals(69_836, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(207_968, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 02`() {
        val (resultPart1, resultPart2) = aocDay02()

        assert(resultPart1.isPresent)
        assertEquals(14_069, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(12_411, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 03`() {
        val (resultPart1, resultPart2) = aocDay03()

        assert(resultPart1.isPresent)
        assertEquals(7_848, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(2_616, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 04`() {
        val (resultPart1, resultPart2) = aocDay04()

        assert(resultPart1.isPresent)
        assertEquals(542, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(900, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 05`() {
        val (resultPart1, resultPart2) = aocDay05()

        assert(resultPart1.isPresent)
        assertEquals("VPCDMSLWJ", resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals("TPWCGNCCG", resultPart2.get())
    }

    @Test
    fun `Run tests for Day 06`() {
        val (resultPart1, resultPart2) = aocDay06()

        assert(resultPart1.isPresent)
        assertEquals(1_235, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(3_051, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 07`() {
        val (resultPart1, resultPart2) = aocDay07()

        assert(resultPart1.isPresent)
        assertEquals(1_844_187, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(4_978_279, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 08`() {
        val (resultPart1, resultPart2) = aocDay08()

        assert(resultPart1.isPresent)
        assertEquals(1_785, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(345_168, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 09`() {
        val (resultPart1, resultPart2) = aocDay09()

        assert(resultPart1.isPresent)
        assertEquals(6_023, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(2_533, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 10`() {
        val (resultPart1, resultPart2) = aocDay10()

        assert(resultPart1.isPresent)
        assertEquals(14_160, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(245, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 11`() {
        val (resultPart1, resultPart2) = aocDay11()

        assert(resultPart1.isPresent)
        assertEquals(78_960, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(14_561_971_968, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 12`() {
        val (resultPart1, resultPart2) = aocDay12()

        assert(resultPart1.isPresent)
        assertEquals(456, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(454, resultPart2.get())
    }

    @Test
    fun `Run tests for Day 13`() {
        val (resultPart1, resultPart2) = aocDay13()

        assert(resultPart1.isPresent)
        assertEquals(5_330, resultPart1.get())
        assert(resultPart2.isPresent)
        assertEquals(27_648, resultPart2.get())
    }
}
