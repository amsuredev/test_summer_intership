abstract class Figure(open val id: Int) {
    abstract fun length(): Double
    abstract fun minXMaxXMinYMaxY(): List<Int>

    companion object {
        open val MINVAL = 0
        open val MAXVAL = 10000
    }
}