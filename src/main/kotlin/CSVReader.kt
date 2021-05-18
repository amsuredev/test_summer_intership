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
        val a = 5
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

    companion object{
        var FILEPATH = "src/main/resources/figures.csv"
        val SEPARATOR = ","
        val MINZONE = 0
        val MAXZONE = 10000
    }
}