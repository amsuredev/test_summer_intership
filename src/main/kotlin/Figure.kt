abstract class Figure(open val id: Int) {
    abstract fun length(): Double

    companion object {
        open val MINVAL = 0
        open val MAXVAL = 10000
    }
}