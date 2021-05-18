/*import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths*/
import java.awt.geom.Line2D
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
    writeToFile(content = not_match_indexes)
    writeToFile(file_name = "src/exercises/zad2.txt", content = getContentEx2(circles.first,rectangles.first, broken.first))

    println(getFullLength(circles.first, rectangles.first, broken.first))
    println(getCircleRectangleSquare(circles.first, rectangles.first))
    println(getNumGeometries(circles.first, rectangles.first, broken.first))


    //print(Line2D.linesIntersect(1.0, 1.0, 4.0, 1.0, 4.0, 2.0, 6.0, 1.0))
}

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

fun writeToFile(file_name:String="src/exercises/zad1.txt", content: List<String>){
    File(file_name).printWriter().use { out ->
        content.forEach {
            out.println(it)
        }
    }
}

fun getFullLength(vararg figures: List<Figure>): Double{
    val figures = concatenate(*figures)
    var fullLength = 0.0
    for (figure in figures){
        fullLength += figure.length()
    }
    return fullLength
}

fun getCircleRectangleSquare(circles: List<Circle>,rectangles: List<Rectangle>): Double{
    var fullSquare = 0.0
    for (figure in circles){
        fullSquare += figure.square()
    }
    for (figure in rectangles){
        fullSquare += figure.square()
    }
    return fullSquare
}

fun getNumGeometries(vararg figures: List<Figure>): Int{
    val geometries = concatenate(*figures)
    return geometries.count()
}

fun getContentEx2(circles: List<Circle>,rectangles: List<Rectangle>, brokens: List<Broken>): List<String>{
    var ex2Content = mutableListOf<String>()
    ex2Content.add("Calkowita Dlugosc: " + getFullLength(circles, rectangles, brokens))
    ex2Content.add("Pole powierzchi prostokatow: " + getCircleRectangleSquare(circles, rectangles))
    ex2Content.add("Liczba geometrii Okrag: " + getNumGeometries(circles))
    ex2Content.add("Liczba geometrii Prostokat: " + getNumGeometries(rectangles))
    ex2Content.add("Liczba geometrii Lamana: " + getNumGeometries(brokens))
    return ex2Content
}


