// Класс для торговцев типа "тактичный" – тип, созданный мной.
// Если против него больше раз совершили честную сделку, то совершает честные сделки, в противном случае жульничает.
class Tactical(id: Int) : Trader(id) {

    var otherTradersHonesty = 0.0

    override fun makeDecision(): Boolean = makeWrongDecision(otherTradersHonesty > 0.0)

    override fun makeDeal(coins: Int) {
        super.makeDeal(coins)
        when (coins) {
            1, 2 -> otherTradersHonesty -= 0.5
            else -> otherTradersHonesty += 0.5
        }
    }

}