import java.util.*
import kotlin.math.absoluteValue

var count = 0
var yearCount = 0

fun makeDeals(traderOne: Trader, traderTwo: Trader) {
    val numberOfDeals = Random().nextInt(6) + 5
    println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} провели $numberOfDeals сделок.")
    for (i in 1..numberOfDeals) {
        val decision1 = traderOne.makeDecision()
        val decision2 = traderTwo.makeDecision()
        when {
            decision1 == decision2 -> {
                if (decision1) {
                    traderOne.madeFairDeal()
                    traderTwo.madeFairDeal()
                    println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} совершили честную сделку, каждый получает по 4 монеты.")
                } else {
                    traderOne.madeUnfairDeal()
                    traderTwo.madeUnfairDeal()
                    println("Торговец ${traderOne.id} и Торговец ${traderTwo.id} совершили нечестную сделку, каждый получает по 2 монеты.")
                }
            }
            decision1 != decision2 -> {
                if (decision1) {
                    traderOne.becameVictimOfFraud()
                    traderTwo.madeSuccessfulFraud()
                    println("Торговец ${traderOne.id} был обманут и получает 1 монету, Торговец ${traderTwo.id} успешно сжульничал и получает 5 монет.")
                } else {
                    traderOne.madeSuccessfulFraud()
                    traderTwo.becameVictimOfFraud()
                    println("Торговец ${traderTwo.id} был обманут и получает 1 монету, Торговец ${traderOne.id} успешно сжульничал и получает 5 монет.")
                }
            }
        }
        count++
    }
}

fun fillGuild(traders: MutableList<Trader>) {
    for (i in 1..10) {
        traders.add(Altruist(i))
        traders.add(Swindler(i + 10))
        traders.add(Cunning(i + 20))
        traders.add(Unpredictable(i + 30))
        traders.add(Rancorous(i + 40))
        traders.add(Quirky(i + 50))
    }
}

fun kickLosersAndGetNewTraders(traders: MutableList<Trader>) {
    val maxId = traders.maxBy { it.id }!!.id

    var trader: Trader
    for (i in 1..12) {
        trader = traders.last()
        println("Торговец ${trader.id} ($trader) с позором изгнан из гильдии.")
        traders.remove(trader)
    }

    for (i in 1..12) {
        when (traders[i - 1]) {
            is Altruist -> addNewTrader(Altruist(maxId + i), traders)
            is Swindler -> addNewTrader(Swindler(maxId + i), traders)
            is Cunning -> addNewTrader(Cunning(maxId + i), traders)
            is Unpredictable -> addNewTrader(Unpredictable(maxId + i), traders)
            is Rancorous -> addNewTrader(Rancorous(maxId + i), traders)
            is Quirky -> addNewTrader(Quirky(maxId + i), traders)
        }
    }
}

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

fun addNewTrader(newTrader: Trader, traders: MutableList<Trader>) {
    traders.add(newTrader)
    println("Приветствуем нового члена гильдии – Торговец ${newTrader.id} ($newTrader)")
}

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

    tradersCounts.toList().sortedByDescending { it.second }.map { println("Торговцев типа ${it.first} в гильдии ${it.second} из ${traders.size} (${it.second.toFloat()/tradersSize.toFloat()*100}%)") }
}