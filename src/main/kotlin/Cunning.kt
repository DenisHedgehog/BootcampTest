class Cunning(id: Int) : Trader(id) {

    private var nextStep = true

    override fun makeDecision(): Boolean = makeWrongDecision(nextStep)

    override fun makeDeal(coins: Int) {
        super.makeDeal(coins)
        when (coins) {
            4 -> nextStep = true
            2 -> nextStep = false
            5 -> nextStep = true
            1 -> nextStep = false
        }
    }

}