import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs
fun main() {
    fun part1(input: List<String>): Int {
        val leftCol = mutableListOf<Int>()
        val rightCol = mutableListOf<Int>()
        for (line in input) {
            val (left, right) = line.split("   ")
            leftCol.add(left.toInt())
            rightCol.add(right.toInt())
        }
        leftCol.sort()
        rightCol.sort()
        var totalDistance = 0
        for (i in leftCol.indices) {
            totalDistance += abs(leftCol[i] - rightCol[i])
        }
        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val leftCol = mutableListOf<Int>()
        val rightCol = mutableListOf<Int>()
        for (line in input) {
            val (left, right) = line.split("   ")
            leftCol.add(left.toInt())
            rightCol.add(right.toInt())
        }
        val count = rightCol.groupingBy { it }.eachCount()
        var similarityScore = 0
        for (i in leftCol) {
            if (i in count.keys) {
                similarityScore += i * count[i]!!
            }
        }
        return similarityScore
    }

    val input = Path("src/01.txt").readText().trim().lines()
    println(part1(input))
    println(part2(input))
}
