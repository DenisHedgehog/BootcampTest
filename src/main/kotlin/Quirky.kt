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

    override fun madeFairDeal() {
        super.madeFairDeal()
        nextStep = true
    }

    override fun madeUnfairDeal() {
        super.madeUnfairDeal()
        if (steps == 1) {
            isVictim = true
        }
        nextStep = false
    }

    override fun madeSuccessfulFraud() {
        super.madeSuccessfulFraud()
        nextStep = true
    }

    override fun becameVictimOfFraud() {
        super.becameVictimOfFraud()
        if (steps < 4) {
            isVictim = true
        }
        nextStep = false
    }

}