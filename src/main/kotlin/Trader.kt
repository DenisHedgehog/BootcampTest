abstract class Trader(val id: Int) {

    var steps = 0
    var earnings = 0

    protected fun makeWrongDecision(deal: Boolean): Boolean = if (java.util.Random().nextInt(100) > 94) !deal else deal

    open fun makeDeal(coins: Int) {
        earnings += coins
        steps++
    }

    override fun toString(): String =
            when (this) {
                is Altruist -> "альтруист"
                is Swindler -> "кидала"
                is Cunning -> "хитрый"
                is Unpredictable -> "непредсказуемый"
                is Rancorous -> "злопамятный"
                is Quirky -> "ушлый"
                is Tactical -> "тактичный"
                else -> "торговец"
            }

    abstract fun makeDecision(): Boolean

}