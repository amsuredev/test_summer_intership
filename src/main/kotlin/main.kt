import java.io.File

fun main(args: Array<String>) {
    val reader = CSVReader()
    val circlesNotMatchIndexes = reader.getMatchedCircle()
    val rectanglesNotMatchIndexes = reader.getMatchedRectangle()
    val brokensNotMatchedIndexes = reader.getMatchedBroken()
    var notMatchIndexes = concatenate(circlesNotMatchIndexes.second, rectanglesNotMatchIndexes.second, brokensNotMatchedIndexes.second)
    val comparator = Comparator { str1: String, str2: String -> str1.toInt() - str2.toInt() }
    notMatchIndexes = notMatchIndexes.sortedWith(comparator)
    writeToFile(content = notMatchIndexes)
    writeToFile(
        file_name = "src/exercises/zad2.txt",
        content = getContentEx2(circlesNotMatchIndexes.first, rectanglesNotMatchIndexes.first, brokensNotMatchedIndexes.first)
    )
    writeToFile(file_name = "src/exercises/zad3.txt",
                content = getContentEx3(circlesNotMatchIndexes.first, rectanglesNotMatchIndexes.first, brokensNotMatchedIndexes.first))
}

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

fun writeToFile(file_name: String = "src/exercises/zad1.txt", content: List<String>) {
    File(file_name).printWriter().use { out ->
        content.forEach {
            out.println(it)
        }
    }
}

fun mediumLengthOfBroken(brokens: List<BrokenLine>): Double {
    return getFullLength(brokens) / brokens.count()
}

fun getFullLength(vararg figures_p: List<Figure>): Double {
    val figures = concatenate(*figures_p)
    var fullLength = 0.0
    for (figure in figures) {
        fullLength += figure.length()
    }
    return fullLength
}

fun getCircleRectangleSquare(circles: List<Circle>, rectangles: List<Rectangle>): Double {
    var fullSquare = 0.0
    for (figure in circles) {
        fullSquare += figure.square()
    }
    for (figure in rectangles) {
        fullSquare += figure.square()
    }
    return fullSquare
}

fun getNumGeometries(vararg figures: List<Figure>): Int {
    val geometries = concatenate(*figures)
    return geometries.count()
}

fun getContentEx2(circles: List<Circle>, rectangles: List<Rectangle>, brokens: List<BrokenLine>): List<String> {
    var ex2Content = mutableListOf<String>()
    ex2Content.add("Calkowita Dlugosc: " + getFullLength(circles, rectangles, brokens))
    ex2Content.add("Pole powierzchi prostokatow: " + getCircleRectangleSquare(circles, rectangles))
    ex2Content.add("Liczba geometrii Okrag: " + getNumGeometries(circles))
    ex2Content.add("Liczba geometrii Prostokat: " + getNumGeometries(rectangles))
    ex2Content.add("Liczba geometrii Lamana: " + getNumGeometries(brokens))
    return ex2Content
}

fun countCrossBySelfBroken(brokens: List<BrokenLine>): Int {
    var count = 0
    for (broken in brokens) {
        if (broken.crossBySelf()) {
            count += 1
        }
    }
    return count
}

fun getMinBoundRectangle(vararg figures_p: List<Figure>): String {
    val minXMaxXMinYMaxY = mutableListOf<Int>(10000, 0, 10000, 0)
    val figures = concatenate(*figures_p)
    var index = 0
    for (figure in figures) {
        val minXMaxXMinYMaxY_candidate = figure.minXMaxXMinYMaxY()
        if (minXMaxXMinYMaxY_candidate[0] < minXMaxXMinYMaxY[0]) {
            minXMaxXMinYMaxY[0] = minXMaxXMinYMaxY_candidate[0]
        }
        if ((minXMaxXMinYMaxY_candidate[1] > minXMaxXMinYMaxY[1])) {
            minXMaxXMinYMaxY[1] = minXMaxXMinYMaxY_candidate[1]
        }
        if (minXMaxXMinYMaxY_candidate[2] < minXMaxXMinYMaxY[2]) {
            minXMaxXMinYMaxY[2] = minXMaxXMinYMaxY_candidate[2]
        }
        if ((minXMaxXMinYMaxY_candidate[3] > minXMaxXMinYMaxY[3])) {
            minXMaxXMinYMaxY[3] = minXMaxXMinYMaxY_candidate[3]
        }
    }
    return "leftDownPoint x: " + minXMaxXMinYMaxY[0] + " y: " + minXMaxXMinYMaxY[2] +
            "\n" + "Top right point x: " + minXMaxXMinYMaxY[1] + " y: " + minXMaxXMinYMaxY[3]
}

fun getContentEx3(circles: List<Circle>, rectangles: List<Rectangle>, brokens: List<BrokenLine>): List<String>{
    return listOf<String>("Srednia dlugosc Lamancyh: " + mediumLengthOfBroken(brokens),
    "Ilosc lamanych przecinajacych samych siebie: " + countCrossBySelfBroken(brokens),
    "Minimalny prostokat: " + getMinBoundRectangle(circles, rectangles, brokens)
    )
}

