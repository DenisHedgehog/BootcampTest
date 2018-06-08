// Класс для торговцев типа "злопамятный"
class Rancorous(id: Int) : Trader(id) {

    private var nextStep = true

    override fun makeDecision(): Boolean = makeWrongDecision(nextStep)

    override fun makeDeal(coins: Int) {
        super.makeDeal(coins)
        if (coins == 2 || coins == 1) {
            nextStep = false
        }
    }
}