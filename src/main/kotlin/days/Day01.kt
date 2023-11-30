package days

import java.io.File

class Day01 {

    fun part1(): Any
    {
        val input = getInput()
        println(input)
        return ""
    }

    fun part2(): Any
    {
        return ""
    }


    private fun getInput(): List<String>
    {
        val file = File("src/main/resources/input1.txt")
        return file.useLines { it.toList() }
    }
}