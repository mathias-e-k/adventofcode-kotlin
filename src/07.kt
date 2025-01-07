import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val (values, equations) = Path("src/07.txt").readText().trim().lines().map { line ->
        val first = line.split(": ")[0].toLong()
        val second = line.split(": ")[1].split(" ").map { it.toLong() }
        first to second
    }.unzip()
}