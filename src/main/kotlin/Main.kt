class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val traders = mutableListOf<Trader>()
            fillGuild(traders)
            var input = "start"
            while (input.isNotEmpty()) {
                makeYearOfDeals(traders)
                printResultsOfTheYear(traders)
                kickLosersAndGetNewTraders(traders)
                getStatistic(traders)
                println("Введите любой символ, чтобы прошел ещё год сделок, оставьте поле пустым и нажмите Enter, чтобы завершить выполнение работы")
                input = readLine().toString()
            }
        }

    }

}