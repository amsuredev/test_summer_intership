import java.io.File

class CSVReader {
    fun getParsedDatas(): MutableList<List<String>>{
        val listOfFigures = mutableListOf<List<String>>()
        try {

            var lines: List<String> = File(FILEPATH).readLines()
            lines.forEach{line -> listOfFigures.add(line.split(SEPARATOR))}
        }
        catch(e: Exception){
            e.printStackTrace()
        }finally{
            println("CSV READ FINISHED")
        }
        return listOfFigures
    }

    fun getMatchedCircle(parsedData: MutableList<List<String>> = getParsedDatas()): Pair<MutableList<Circle>, MutableList<String>> {
        var matchedCircle = mutableListOf<Circle>()
        var notMatchedIndexesCircle = mutableListOf<String>()
        for (line in parsedData){
            if (line[1] == "C"){
                val circle: Circle? = Circle.createCircleByString(line[0], line[2])
                if (circle != null){
                    matchedCircle.add(circle)
                }else{
                    notMatchedIndexesCircle.add(line[0])
                }
            }
        }
        return Pair(matchedCircle, notMatchedIndexesCircle)
    }

    fun getMatchedRectangle(parsedData: MutableList<List<String>> = getParsedDatas()): Pair<MutableList<Rectangle>, MutableList<String>> {
        var matchedRectangle = mutableListOf<Rectangle>()
        var notMatchedIndexesRectangle = mutableListOf<String>()
        for (line in parsedData){
            if (line[1] == "R"){
                val rectangle: Rectangle? = Rectangle.createRectangleByString(line[0], line[2])
                if (rectangle != null){
                    matchedRectangle.add(rectangle)
                }else{
                    notMatchedIndexesRectangle.add(line[0])
                }
            }
        }
        return Pair(matchedRectangle, notMatchedIndexesRectangle)
    }

    fun getMatchedBroken(parsedData: MutableList<List<String>> = getParsedDatas()): Pair<MutableList<Broken>, MutableList<String>> {
        var matchedBroken = mutableListOf<Broken>()
        var notMatchedIndexesBroken = mutableListOf<String>()
        for (line in parsedData){
            if (line[1] == "L"){
                val broken: Broken? = Broken.createBroken(line[0], line[2])
                if (broken != null){
                    matchedBroken.add(broken)
                }else{
                    notMatchedIndexesBroken.add(line[0])
                }
            }
        }
        return Pair(matchedBroken, notMatchedIndexesBroken)
    }

    companion object{
        var FILEPATH = "src/main/resources/figures.csv"
        val SEPARATOR = ","
    }
}