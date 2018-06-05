class Rancorous(id: Int) : Trader(id) {

    private var nextStep = true

    override fun makeDecision(): Boolean = makeWrongDecision(nextStep)

    override fun becameVictimOfFraud() {
        super.becameVictimOfFraud()
        nextStep = false
    }

    override fun madeUnfairDeal() {
        super.madeUnfairDeal()
        nextStep = false
    }

}