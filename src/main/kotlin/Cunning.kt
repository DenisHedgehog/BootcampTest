// Класс для торговцев типа "хитрец"
class Cunning(id: Int) : Trader(id) {

    private var nextStep = true

    override fun makeDecision(): Boolean = makeWrongDecision(nextStep)

    override fun makeDeal(coins: Int) {
        super.makeDeal(coins)
        when (coins) {
            4, 5 -> nextStep = true
            1, 2 -> nextStep = false
        }
    }

}