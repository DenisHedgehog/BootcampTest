import java.util.*

class Unpredictable(id: Int) : Trader(id) {

    override fun makeDecision(): Boolean = makeWrongDecision(Random().nextBoolean())

}