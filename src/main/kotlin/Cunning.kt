class Cunning(id: Int) : Trader(id) {

    private var nextStep = true

    override fun makeDecision(): Boolean = makeWrongDecision(nextStep)

    override fun madeFairDeal() {
        super.madeFairDeal()
        nextStep = true
    }

    override fun madeUnfairDeal() {
        super.madeUnfairDeal()
        nextStep = false
    }

    override fun madeSuccessfulFraud() {
        super.madeSuccessfulFraud()
        nextStep = true
    }

    override fun becameVictimOfFraud() {
        super.becameVictimOfFraud()
        nextStep = false
    }

}