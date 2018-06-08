// Родительский класс для всех торговцев, в нём реализован общий для всех торговцев функционал
abstract class Trader(val id: Int) {

    // Счетчик сделок для торговца
    var steps = 0
    // Заработок торговца
    var earnings = 0

    // Функция, которая с шансом 5% делает неверное решение
    protected fun makeWrongDecision(deal: Boolean): Boolean = if (java.util.Random().nextInt(100) > 94) !deal else deal

    // Функция для прибавления заработка и подсчета количества сделок
    open fun makeDeal(coins: Int) {
        earnings += coins
        steps++
    }

    override fun toString(): String =
            when (this) {
                is Altruist -> "альтруист"
                is Swindler -> "кидала"
                is Cunning -> "хитрый"
                is Unpredictable -> "непредсказуемый"
                is Rancorous -> "злопамятный"
                is Quirky -> "ушлый"
                is Tactical -> "тактичный"
                else -> "торговец"
            }

    // Абстрактная функция, с помощью которой торговец решает сжульничать ему или провести честную сделку
    // Переопределена для каждого типа торговев
    abstract fun makeDecision(): Boolean

}