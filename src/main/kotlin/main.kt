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
    var not_match_indexes = concatenate(circles.second, rectangles.second, broken.second)
    val comparator = Comparator {str1: String, str2: String -> str1.toInt() - str2.toInt()}
    not_match_indexes = not_match_indexes.sortedWith(comparator)
    writeToFile(indexes = not_match_indexes)
}

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

fun writeToFile(file_name:String="exersices/zad1.txt", indexes: List<String>){
    File(file_name).printWriter().use { out ->
        indexes.forEach {
            out.println(it)
        }
    }
}



