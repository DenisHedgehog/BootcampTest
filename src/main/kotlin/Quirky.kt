// Класс для торговцев типа "ушлый"
class Quirky(id: Int) : Trader(id) {

    private var nextStep = true
    private var isVictim = false

    override fun makeDecision(): Boolean {
        return when (steps) {
            0 -> true
            1 -> false
            2 -> true
            3 -> true
            else -> {
                if (isVictim) {
                    makeWrongDecision(false)
                } else {
                    makeWrongDecision(nextStep)
                }
            }
        }
    }

    override fun makeDeal(coins: Int) {
        super.makeDeal(coins)
        if (steps < 4 && (coins == 2 || coins == 1)) {
            isVictim = true
        }
        if (!isVictim && steps >= 4) {
            when (coins) {
                4, 5 -> nextStep = true

                1, 2 -> {
                    if (steps < 4) {
                        isVictim = true
                    }
                    nextStep = false
                }
            }
        }
    }

}