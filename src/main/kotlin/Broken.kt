import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Broken (override val id: Int, val points: List<Point>): Figure(id){
    companion object {
        fun createBroken(idString: String, line: String): Broken? {
            val points = mutableListOf<Point>()
            val id = idString.toInt()
            val values = line.split(SEPARATORVALUES)
            try {
                for (value in values){
                    val coordninates = value.split(SEPARATOR_POINT_COORD)
                    if (coordninates.count() != 2)
                    {
                        return null
                    }
                    val coordinatesToAdd = mutableListOf<Int>()
                    for (coordninate in coordninates){
                        val coordninateAdd = coordninate.toInt()
                        if (coordninateAdd <= MINVAL || coordninateAdd >= MAXVAL)
                        {
                            return null
                        }else{
                            coordinatesToAdd.add(coordninateAdd)
                        }
                    }
                    points.add(Point(coordinatesToAdd[0], coordinatesToAdd[1]))
                }
                return Broken(id, points)
            } catch (e: Exception) {
                return null
            }
        }


        val SEPARATORVALUES: String = ";"
        val SEPARATOR_POINT_COORD = "-"
    }

    override fun length(): Double {
        var length = 0.0
        for (i in 1 until points.size){
            length += sqrt((points[i].x - points[i-1].x).toDouble().pow(2) + (points[i].y - points[i-1].y).toDouble().pow(2))
        }
        return length
    }
}