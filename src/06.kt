import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val labMap = Path("src/06.txt").readText().trim().lines()
    val height = labMap.size
    val width = labMap[0].length
    val visitedMap = MutableList(height) { ".".repeat(width).toMutableList() }
    var guardY = labMap.indexOf(labMap.first { "^" in it })
    var guardX = labMap[guardY].indexOf("^")
    var inMap = true
    visitedMap[guardY][guardX] = '#'
    val directions = listOf("up", "right", "down", "left")
    var direction = "up"
    while (inMap) {
        if (direction == "up") {
            if (guardY == 0) {
                inMap = false
            }
            else if (labMap[guardY - 1][guardX] == '#') {
                direction = directions[(directions.indexOf(direction) + 1) % 4]
            }
            else {
                guardY--
                visitedMap[guardY][guardX] = '#'
            }
        }
        if (direction == "right") {
            if (guardX == 129) {
                inMap = false
            }
            else if (labMap[guardY][guardX + 1] == '#') {
                direction = directions[(directions.indexOf(direction) + 1) % 4]
            }
            else {
                guardX++
                visitedMap[guardY][guardX] = '#'
            }
        }
        if (direction == "down") {
            if (guardY == 129) {
                inMap = false
            }
            else if (labMap[guardY + 1][guardX] == '#') {
                direction = directions[(directions.indexOf(direction) + 1) % 4]
            }
            else {
                guardY++
                visitedMap[guardY][guardX] = '#'
            }
        }
        if (direction == "left") {
            if (guardX == 0) {
                inMap = false
            }
            else if (labMap[guardY][guardX - 1] == '#') {
                direction = directions[(directions.indexOf(direction) + 1) % 4]
            }
            else {
                guardX--
                visitedMap[guardY][guardX] = '#'
            }
        }
    }
    var visitedSpaces = 0
    visitedMap.forEach { visitedSpaces += it.count { it == '#' }; println(it) }
    println(visitedSpaces)
}