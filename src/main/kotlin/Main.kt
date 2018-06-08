class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val traders = mutableListOf<Trader>()
            fillGuild(traders)
            var input = "1"
            while (input.toIntOrNull() != null) {
                for (i in 1..input.toInt()){
                    traders.shuffle()
                    makeYearOfDeals(traders)
                    printResultsOfTheYear(traders)
                    kickLosersAndGetNewTraders(traders)
                    getStatistic(traders)
                    println("Введите количество лет торговли")
                }
                input = readLine().toString()
            }
        }

    }

}