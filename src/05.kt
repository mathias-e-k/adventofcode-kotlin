import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val (rules, updates) = Path("src/05.txt").readText().trim().split("\r\n\r\n").map { it ->
        it.lines().map { it.split(Regex("[|,]")).map(String::toInt) }
    }
    var sum = 0
    val incorrectUpdates = mutableListOf<List<Int>>()
    for (update in updates) {
        val applicableRules = rules.filter { update.containsAll(it) }
        if (applicableRules.all {update.indexOf(it[0]) < update.indexOf(it[1]) }) {
            sum += update[update.lastIndex / 2]
        }
        else {
            incorrectUpdates.add(update)
        }
    }
    println("Part1: $sum")
    sum = 0
    for (update in incorrectUpdates) {
        var applicableRules = rules.filter { update.containsAll(it) }
        val fixedUpdate = mutableListOf<Int>()
        while (fixedUpdate.size != update.size) {
            val availableNumbers = update.filterNot { fixedUpdate.contains(it) }
            val nextNumber = availableNumbers.filterNot { it -> applicableRules.map { it[1] }.contains(it) }[0]
            fixedUpdate.add(nextNumber)
            applicableRules = applicableRules.filterNot { it.contains(nextNumber) }
        }
        sum += fixedUpdate[fixedUpdate.lastIndex / 2]
    }
    println("Part2: $sum")
}