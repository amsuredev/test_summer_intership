/*import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths*/
import java.io.File

fun main(args: Array<String>) {
    val reader = CSVReader()
    val data = reader.getParsedDatas()
    val circles = reader.getMatchedCircle()
    val rectangles = reader.getMatchedRectangle()
    val broken = reader.getMatchedBroken()
    val a = 5
}



