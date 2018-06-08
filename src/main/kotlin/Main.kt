class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val traders = mutableListOf<Trader>()
            // Первоначальное заполнение гильдии
            fillGuild(traders)
            // Количество лет торговли при старте программы
            var input = "0"
            // Проведение сделок, пока пользователь вводит числа
            while (input.toIntOrNull() != null) {
                // Провести сделки за введенное количество лет
                for (i in 1..input.toInt()) {
                    // Перемешивание списка торговцев для уменьшения влияния их местоположения в списке на заработок
                    traders.shuffle()
                    // Проведение года сделок
                    makeYearOfDeals(traders)
                    // Вывод итогов года
                    printResultsOfTheYear(traders)
                    // Изгнание неуспешных торговцев и набор новых
                    kickLosersAndGetNewTraders(traders)
                    // Вывод статистики по торговцам
                    getStatistic(traders)
                }
                println("Введите количество лет торговли")
                // Ожидание введения числа
                input = readLine().toString()
            }
        }

    }

}