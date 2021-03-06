import kotlin.math.*

data class Circle(override val id: Int, val radius: Int, val center: Point) : Figure(id) {
    companion object {
        fun create(idString: String, line: String): Circle? {
            val id = idString.toInt()
            val values = line.split(SEPARATORVALUES)
            if (values.count() != 2) {
                return null
            }
            try {
                val radius: Int = values[1].toInt()
                val coordString = values[0].split(SEPARATOR_POINT_COORD)
                val x = coordString[0].toInt()
                val y = coordString[1].toInt()
                if (!(inZone(x, y, radius)) || radius <= 0) {
                    return null
                }
                val point: Point = Point(x, y)
                return Circle(id, radius = radius, center = point)
            } catch (e: Exception) {
                return null
            }
        }

        private fun inZone(x: Int, y: Int, radius: Int): Boolean {
            return !(x + radius >= MAXVAL || x - radius <= MINVAL || y + radius >= MAXVAL || y - radius <= MINVAL)
        }

        val SEPARATORVALUES: String = ";"
        val SEPARATOR_POINT_COORD = "-"
    }

    fun square(): Double {
        return PI * radius.toDouble().pow(2.0)
    }

    override fun length(): Double {
        return 2 * PI * radius
    }

    override fun minXMaxXMinYMaxY(): List<Int> {
        return listOf(center.x - radius, center.x + radius, center.y - radius, center.y + radius)
    }
}
