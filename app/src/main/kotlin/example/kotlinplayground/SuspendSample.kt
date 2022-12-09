package example.kotlinplayground

import kotlinx.coroutines.delay

class SuspendSample {
    suspend fun susFunc(): Int {
        // Block(1)-----
        func1()
        susFuncA()
        // Block(2)-----
        func2()
        susFuncB()
        // Block(3)-----
        func3()
        susFuncC()
        // Block(3)-----
        return getInt()
    }

    suspend fun susFuncA() {
        delay(1000)
        println("susFuncA")
    }
    suspend fun susFuncB() {
        delay(1000)
        println("susFuncB")
    }
    suspend fun susFuncC() {
        delay(1000)
        println("susFuncC")
    }

    private fun func1() = println("func1")
    private fun func2() = println("func2")
    private fun func3() = println("func3")
    private fun getInt(): Int {
        println("getInt")
        return 0
    }
}
