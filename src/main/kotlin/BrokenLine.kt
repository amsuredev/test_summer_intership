import java.awt.geom.Line2D
import kotlin.math.pow
import kotlin.math.sqrt

data class BrokenLine(override val id: Int, val points: List<Point>) : Figure(id) {
    companion object {
        fun create(idString: String, line: String): BrokenLine? {
            val points = mutableListOf<Point>()
            val id = idString.toInt()
            val values = line.split(SEPARATORVALUES)
            try {
                for (value in values) {
                    val coordninates = value.split(SEPARATOR_POINT_COORD)
                    if (coordninates.count() != 2) {
                        return null
                    }
                    val coordinatesToAdd = mutableListOf<Int>()
                    for (coordninate in coordninates) {
                        val coordninateAdd = coordninate.toInt()
                        if (coordninateAdd <= MINVAL || coordninateAdd >= MAXVAL) {
                            return null
                        } else {
                            coordinatesToAdd.add(coordninateAdd)
                        }
                    }
                    points.add(Point(coordinatesToAdd[0], coordinatesToAdd[1]))
                }
                return BrokenLine(id, points)
            } catch (e: Exception) {
                return null
            }
        }

        val SEPARATORVALUES: String = ";"
        val SEPARATOR_POINT_COORD = "-"
    }

    override fun length(): Double {
        var length = 0.0
        for (i in 1 until points.size) {
            length += sqrt(
                (points[i].x - points[i - 1].x).toDouble().pow(2) + (points[i].y - points[i - 1].y).toDouble().pow(2)
            )
        }
        return length
    }

    override fun minXMaxXMinYMaxY(): List<Int> {
        var minX = 10000
        var maxX = 0
        var minY = 10000
        var maxY = 0
        for (point in points) {
            if (point.x < minX) {
                minX = point.x
            }
            if (point.x > maxX) {
                maxX = point.x
            }
            if (point.y < minY) {
                minY = point.y
            }
            if (point.y > maxY) {
                maxY = point.y
            }
        }
        return listOf(minX, maxX, minY, maxY)
    }

    fun crossBySelf(): Boolean {
        var segments = mutableListOf<Segment>()
        for (i in 1 until points.count()) {
            segments.add(Segment(points[i - 1], points[i]))
        }
        for (i in 0 until segments.count() - 1) {
            if (curSegmentCrossAfterSegments(i, segments)) {
                return true
            }
        }
        return false
    }

    private fun curSegmentCrossAfterSegments(index: Int, segments: List<Segment>): Boolean {
        var count = 0
        for (i in index + 1 until segments.count()) {
            if (Line2D.linesIntersect(
                    segments[index].startPoint.x.toDouble(),
                    segments[index].startPoint.y.toDouble(),
                    segments[index].endPoint.x.toDouble(),
                    segments[index].endPoint.y.toDouble(),
                    segments[i].startPoint.x.toDouble(),
                    segments[i].startPoint.y.toDouble(),
                    segments[i].endPoint.x.toDouble(),
                    segments[i].endPoint.y.toDouble()
                )
            ) {
                count += 1
            }
            if (count > 1) {
                return true
            }
        }
        return false
    }
}