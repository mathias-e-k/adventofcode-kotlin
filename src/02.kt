import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val input = Path("src/02.txt").readText().trim().lines()
    val reports = input.map {
        it.split(" ").map(String::toInt)
    }

    fun isSafe(report: List<Int>): Boolean {
        val diffs = (1..report.lastIndex).map {
            report[it] - report[it - 1]
        }
        if (!(diffs.all { it > 0 } or diffs.all { it < 0 })) {
            return false
        }
        if (diffs.any { abs(it) > 3 }) {
            return false
        }
        return true
    }

    // Part 1
    var safeReports = 0
    for (report in reports) {
       if (isSafe(report)) {
           safeReports++
       }
    }
    println("Part1: $safeReports")

    // Part 2
    safeReports = 0
    for (report in reports) {
        for (removedLevel in report.indices) {
            val dampenedReport = report.subList(0, removedLevel) + report.subList(removedLevel + 1, report.size)
            if (isSafe(dampenedReport)) {
                safeReports++
                break
            }
        }
    }
    println("Part2: $safeReports")
}