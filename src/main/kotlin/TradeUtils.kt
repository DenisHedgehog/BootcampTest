import kotlin.math.absoluteValue

// Файл с вспомогательными функциями, с помощью которых совершаются сделки мжде торговцами


// Счетчик сделок
var count = 0
// Счетчик лет
var yearCount = 0

// Константы, хранящие количество монет для каждого исхода сделки
const val fairDealCoins = 4
const val unfairDealCoins = 2
const val victimDealCoins = 1
const val stealDealCoins = 5

// Функция, которая проводит от 5 до 10 сделок между двумя торговцами
fun makeDeals(traderOne: Trader, traderTwo: Trader) {
    val numberOfDeals = java.util.Random().nextInt(6) + 5
    println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} провели $numberOfDeals сделок.")
    for (i in 1..numberOfDeals) {
        val decision1 = traderOne.makeDecision()
        val decision2 = traderTwo.makeDecision()
        when {
            decision1 && decision2 -> {
                traderOne.makeDeal(fairDealCoins)
                traderTwo.makeDeal(fairDealCoins)
                println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} совершили честную сделку, " +
                        "каждый получает по $fairDealCoins монеты.")
            }
            !decision1 && !decision2 -> {
                traderOne.makeDeal(unfairDealCoins)
                traderTwo.makeDeal(unfairDealCoins)
                println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} совершили нечестную сделку, " +
                        "каждый получает по $unfairDealCoins монеты.")
            }
            decision1 && !decision2 -> {
                traderOne.makeDeal(victimDealCoins)
                traderTwo.makeDeal(stealDealCoins)
                println("Торговец ${traderOne.id} был обманут и получает $victimDealCoins монету, " +
                        "Торговец ${traderTwo.id} успешно сжульничал и получает $stealDealCoins монет.")
            }
            !decision1 && decision2 -> {
                traderOne.makeDeal(stealDealCoins)
                traderTwo.makeDeal(victimDealCoins)
                println("Торговец ${traderTwo.id} был обманут и получает $victimDealCoins монету, " +
                        "Торговец ${traderOne.id} успешно сжульничал и получает $stealDealCoins монет.")
            }
        }
        count++
    }
}

// Функция, с помощью которой в первый раз заполняется гильдия
fun fillGuild(traders: MutableList<Trader>) {
    for (i in 1..10) {
        traders.add(Altruist(i))
        traders.add(Swindler(i + 10))
        traders.add(Cunning(i + 20))
        traders.add(Unpredictable(i + 30))
        traders.add(Rancorous(i + 40))
        traders.add(Quirky(i + 50))
        traders.add(Tactical(i + 60))
    }
}

// Функция для изгнания неуспешных торговцев и привлечения новых
fun kickLosersAndGetNewTraders(traders: MutableList<Trader>) {
    val maxId = traders.maxBy { it.id }!!.id
    val unsuccessfulTradersCount = (traders.size * 0.2).toInt()
    var trader: Trader
    for (i in 1..unsuccessfulTradersCount) {
        trader = traders.last()
        println("Торговец ${trader.id} ($trader) с позором изгнан из гильдии.")
        traders.remove(trader)
    }

    for (i in 1..unsuccessfulTradersCount) {
        when (traders[i - 1]) {
            is Altruist -> addNewTrader(Altruist(maxId + i), traders)
            is Swindler -> addNewTrader(Swindler(maxId + i), traders)
            is Cunning -> addNewTrader(Cunning(maxId + i), traders)
            is Unpredictable -> addNewTrader(Unpredictable(maxId + i), traders)
            is Rancorous -> addNewTrader(Rancorous(maxId + i), traders)
            is Quirky -> addNewTrader(Quirky(maxId + i), traders)
            is Tactical -> addNewTrader(Tactical(maxId + i), traders)
        }
    }
}

// Функция, которая совершает сделки для каждой пары торговцев
fun makeYearOfDeals(traders: MutableList<Trader>) {
    for ((i, traderOne) in traders.withIndex()) {
        if (i != traders.size - 1) {
            for (j in i + 1 until traders.size) {
                makeDeals(traderOne, traders[j])
            }
        }
    }
    yearCount++
    println("Количество сделок за ${yearCount}й год равно $count.")
}

// Функция выводящая итоги года
fun printResultsOfTheYear(traders: MutableList<Trader>) {
    traders.sortByDescending { it.earnings }
    var place = 1
    traders.map {
        println("#$place\tТорговец ${it.id} ($it) заработал ${it.earnings} монет за ${it.steps} сделок.")
        place++
        it.earnings = 0
        it.steps = 0
        count = 0
    }
}

// Функция для добавления нового торговца
fun addNewTrader(newTrader: Trader, traders: MutableList<Trader>) {
    traders.add(newTrader)
    println("Приветствуем нового члена гильдии – Торговец ${newTrader.id} ($newTrader)")
}

// Функция выводящая количество торговцев каждого типа, которые состоят в гильдии
fun getStatistic(traders: MutableList<Trader>) {
    val tradersCounts = mutableMapOf<String, Int>()
    traders.map {
        val type = it.toString()
        if (tradersCounts.containsKey(type)) {
            tradersCounts[type] = tradersCounts[type]!!.absoluteValue + 1
        } else {
            tradersCounts.put(type, 1)
        }
    }

    val tradersSize = traders.size
    tradersCounts.toList().sortedByDescending { it.second }.map { println("Количество торговцев типа ${it.first} " +
            "в гильдии ${it.second} из ${traders.size} (${it.second.toFloat()/tradersSize.toFloat()*100}%)") }
}