package at2020.day1

import java.io.File

class At2020Day1KTS {
    private val numbers = File("src/main/kotlin/at2020/day1/expense-report.txt").readLines().map(String::toInt)

    private fun partOneEasiestWay(targetSum: Int) {
        for(firstNumber in numbers) {
            for (secondNumber in numbers) {
                if(firstNumber + secondNumber == targetSum) {
                    println("numbers that sum $targetSum: $firstNumber, $secondNumber")
                    println("product from them: ${firstNumber * secondNumber}")
                    return
                }
            }
        }
    }

    private fun partTwoEasiestWay(targetSum: Int) {
        for (firstNumber in numbers) {
            for (secondNumber in numbers) {
                for (thirdNumber in numbers) {
                    if (firstNumber + secondNumber + thirdNumber == targetSum) {
                        println("numbers that sum $targetSum: $firstNumber, $secondNumber, $thirdNumber")
                        println("product from them: ${firstNumber * secondNumber * thirdNumber}")
                        return
                    }
                }
            }
        }
    }

    private fun List<Int>.partOneEfficientWay(targetSum: Int): Pair<Int, Int>? {
        return firstNotNullOfOrNull { number ->
            associateBy { targetSum - it }[number]?.let { complement ->
                Pair(complement, number)
            }
        }
    }

    private fun List<Int>.partTwoEfficientWay(targetSum: Int): Triple<Int, Int, Int>? {
        return firstNotNullOfOrNull { thirdElement ->
            partOneEfficientWay(targetSum - thirdElement)?.let { pair ->
                Triple(pair.first, pair.second, thirdElement)
            }
        }
    }

    fun execute () {
        val targetSum = 2020
        val pair = numbers.partOneEfficientWay(targetSum)
        val triple = numbers.partTwoEfficientWay(targetSum)

        println("Two numbers that sum $targetSum: $pair")
        println( pair?.let { (first, second) -> "Here is there product from these two numbers: ${first * second}" })

        println("Three numbers that sum $targetSum: $triple")
        println(triple?.let { (first, second, third) ->
            "Here is there product from these three numbers: ${first * second * third}" })
    }
}