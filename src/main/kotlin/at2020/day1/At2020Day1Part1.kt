package at2020.day1

class At2020Day1Part1 : At2020Day1() {
    override var entriesToReachTargetSum = IntArray(2) { 0 }
    override val puzzleDescription: String = "Day1 part 1 description:\n" +
            "Find the two entries that sum to 2020; what do you get if you multiply them together?\n"

    private fun calculateProduct(): Int {
        return entriesToReachTargetSum[0] * entriesToReachTargetSum[1]
    }

    private fun setEntriesToReachTargetSum(elementToSum: Int) {
        entriesToReachTargetSum[0] = expenseReport[currentIndex]
        entriesToReachTargetSum[1] = elementToSum
    }

    override fun searchEntriesToGetProduct(list: List<Int>) {
        println("list.size: ${list.size}")
        if (list.size < 5) {

            println("list is less than 5, here it is: $list")

            var elementToSumIndex = 0
            while (elementToSumIndex <= list.lastIndex) {
                println("list.lastIndex: ${list.lastIndex}")
                println("currentIndex: $currentIndex")
                println("elementToSumIndex: $elementToSumIndex")
                setEntriesToReachTargetSum(list[elementToSumIndex])

                if (entriesToReachTargetSum.sum() == targetSum) {
                    println("product is: ${calculateProduct()}")
                    break
                }

                elementToSumIndex++

                if (elementToSumIndex > list.lastIndex) {
                    currentIndex++
                    searchEntriesToGetProduct(expenseReport)
                }
            }

            return
        }

        val listMiddleIndex = list.lastIndex / 2
        println("listMiddleIndex: $listMiddleIndex")
        println("lastIndex: ${list.lastIndex}")

        if (listMiddleIndex > 0) {

            println("list[currentIndex]: ${list[currentIndex]}")
            println("list[listMiddleIndex]: ${list[listMiddleIndex]}")

            setEntriesToReachTargetSum(list[listMiddleIndex])

            println("entriesToReachTargetSum: ${entriesToReachTargetSum[0]}, ${entriesToReachTargetSum[1]}")

            if (entriesToReachTargetSum.sum() == targetSum) {
                println("Target sum found!")
                println(calculateProduct())
                return
            }

            val halfList = mutableListOf<Int>()
            if (entriesToReachTargetSum.sum() > targetSum) {
                println("Target sum is in first half, reiterating")
                println(entriesToReachTargetSum.sum())

                list.forEachIndexed { index, number -> if (index <= listMiddleIndex) halfList.add(number)  }

                println("halfList is: $halfList")

                searchEntriesToGetProduct(halfList)
                return
            }

            println("Target sum is in second half, reiterating")
            println(entriesToReachTargetSum.sum())

            list.forEachIndexed { index, number -> if (index >= listMiddleIndex && index <= list.lastIndex ) halfList.add(number)  }

            searchEntriesToGetProduct(halfList)
        }

    }
}