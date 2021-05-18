import kotlin.math.max
import kotlin.math.min

data class Rectangle (override val id: Int, val leftDownPoint: Point, val rightUpPoint: Point): Figure(id){
    companion object {
        fun createRectangleByString(idString: String, line: String): Rectangle? {
            val id = idString.toInt()
            val values = line.split(SEPARATORVALUES)
            if (values.count() != 2) {
                return null
            }
            try {
                val x1 = values[0].split(SEPARATOR_POINT_COORD)[0].toInt()
                val y1 = values[0].split(SEPARATOR_POINT_COORD)[1].toInt()
                val x2 = values[1].split(SEPARATOR_POINT_COORD)[0].toInt()
                val y2 = values[1].split(SEPARATOR_POINT_COORD)[1].toInt()
                val leftDownPoint = Point(min(x1, x2), min(y1, y2))
                val rightUpPoint = Point(max(x1, x2), max(y1, y2))
                if (!(inZone(leftDownPoint, rightUpPoint)) || x1 == x2 || y1 == y2)
                {
                    return null
                }
                return Rectangle(id, leftDownPoint, rightUpPoint)
            } catch (e: Exception) {
                return null
            }
        }

        private fun inZone(leftDownPoint: Point, rightUpPoint: Point): Boolean{
            return !(leftDownPoint.x <= MINVAL || leftDownPoint.y <= MINVAL || rightUpPoint.x >= MAXVAL || rightUpPoint.y >= MAXVAL)
        }

        val SEPARATORVALUES: String = ";"
        val SEPARATOR_POINT_COORD = "-"
    }
}