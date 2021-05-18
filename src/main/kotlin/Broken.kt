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
}