package days

import java.io.File

class Day01 {

    fun part1(): Any
    {
        val input = getInput()
        val firstDigits = input.map {
            it.first { it.isDigit() }

        }
        val lastDigits = input.map {
            it.last { it.isDigit() }
        }

        var value = 0

        for (i in input.indices)
        {
            val emptyString = ""
            val fistAndLastDigit = emptyString.plus(firstDigits[i]).plus(lastDigits[i]).toInt()
            value += fistAndLastDigit
        }

        return value
    }

    data class Digit(
        var firstValue: Int? = null,
        var lastValue: Int? = null,
        var firstKey: Int? = null,
        val lastKey: Int? = null,
    )

    fun part2(): Any
    {
        val list = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        val endList = mutableListOf<Digit>()
        val input = getInput()

        input.map { text ->
            var start = 0
            var end = 0
            var first = 0
            var firstKey = 0
            var second = 0
            var secondKey = 0
            var tempLastKey = 0
            var notFound = true
            var containted = false

            while (notFound)
            {
                if (list.contains(text.substring(start, end)))
                {
                    list.forEach {
                        if (text.substring(start, end).contains(it.key))
                        {
                            if (first != 0)
                            {
                                second = it.value
                                secondKey = end
                            }
                            else
                            {
                                first = it.value
                                firstKey = start
                                tempLastKey = end
                                containted = true
                            }
                        }
                    }
                }
                if ((end - start) == 5 || end == text.length)
                {
                    start++
                    end = start
                }
                if (end != text.length)
                    end++

                if (start == text.length)
                {
                    if (second == 0)
                        second = first
                    if (secondKey == 0)
                        secondKey = tempLastKey

                    if (!containted && first == 0)
                    {
                        firstKey = 100
                    }
                    endList.add(Digit(firstValue = first, lastValue = second, firstKey = firstKey, lastKey = secondKey))
                    notFound = false
                }
            }

        }

        input.mapIndexed { index, digit ->
            val indexOfFirst = digit.indexOfFirst { it.isDigit() }
            if (indexOfFirst < (endList[index].firstKey ?: 100))
            {
                endList[index].firstValue = digit[indexOfFirst].digitToInt()
            }
            val indexOfLast = digit.indexOfLast { it.isDigit() }

            if (indexOfLast >= (endList[index].lastKey ?: 0))
            {
                endList[index].lastValue = digit[indexOfLast].digitToInt()
            }

        }
        var count = 0
        endList.map {
            count += "".plus(it.firstValue).plus(it.lastValue).toInt()
        }


        return count
    }


    private fun getInput(): List<String>
    {
        val file = File("src/main/resources/input1.txt")
        return file.useLines { it.toList() }
    }
}