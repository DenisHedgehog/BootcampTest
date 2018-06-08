// Класс для торговцев типа "альтруист"
class Altruist(id: Int) : Trader(id) {

    override fun makeDecision(): Boolean = makeWrongDecision(true)

}