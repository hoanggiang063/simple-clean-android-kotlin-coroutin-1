package com.architecture.business

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Arrays.copyOf

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // Complete the gridlandMetro function below.

    fun gridlandMetro(rows: Int, columns: Int, k: Int, tracks: Array<Array<Int>>): Int {
        var countPlaces: Int = 0;
        for (i in 1..rows) {
            for (j in 1..columns) {
                var isOk: Boolean = true
                tracks.forEach {
                    if (it.get(0) == i) {
                        if (j >= it.get(1) && j <= it.get(2) || j <= it.get(1) && j >= it.get(2)) {
                            isOk = false;
                        }
                    }
                }
                if (isOk) {
                    countPlaces = countPlaces.inc()
                }
            }
        }
        return countPlaces;
    }

    fun quickSort(arr: Array<Int>): Array<Int> {
        val left: MutableList<Int> = arrayListOf()
        val right: MutableList<Int> = arrayListOf()
        val equal: MutableList<Int> = arrayListOf()
        arr.forEach {
            var pivot = arr.get(0);
            if (pivot > it) {
                left.add(it)
            } else if(pivot<it){
                right.add(pivot)
            } else{
                equal.add(it)
            }
        }
        left.addAll(equal)
        left.addAll(right)
        return left.toTypedArray();
    }
}
