import kotlin.io.path.Path
import kotlin.io.path.readText
fun main() {
    val memory = Path("src/03.txt").readText().trim()
    val mulPattern = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    val instructions = mulPattern.findAll(memory)
    var sum = 0
    instructions.forEach {
        val (num1, num2) = it.groupValues[0].substringAfter("(").substringBefore(")").split(",").map { it.toInt() }
        sum += num1 * num2
    }
    println("Part1: $sum")

    val pattern = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")
    val partTwoInstructions = pattern.findAll(memory)
    sum = 0
    var on = true
    partTwoInstructions.forEach {
        val instruction = it.groupValues[0]
        if (instruction == "do()") { on = true }
        else if (instruction == "don't()") { on = false }
        else {
            if (on) {
                val (num1, num2) = it.groupValues[0].substringAfter("(").substringBefore(")").split(",")
                    .map { it.toInt() }
                sum += num1 * num2
            }
        }
    }
    println("Part2: $sum")
}