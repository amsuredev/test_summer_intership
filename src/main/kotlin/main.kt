/*import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths*/
import java.io.File

fun main(args: Array<String>) {
   /* val reader = Files.newBufferedReader(Paths.get("src/main/resources/figures.csv"))
    // TODO CATCH NOSUCHFILE EXCEPTION
    val csvParser = (reader, CSVFormat.DEFAULT)*/
    val reader = CSVReader()
    val circles = reader.getMatchedCircle()
    val a = 5
//    val b = mutableListOf<Point>(Point(5, 3))
//    b.add()
}


