import kotlin.system.exitProcess

const val cardDayTransferLimit = 150_000
const val cardMonthTransferLimit = 600_000

const val vkPayDayTransferLimit = 15000
const val vkPayMonthTransferLimit = 40_000

val paymentMethods = arrayOf("mastercard", "maestro", "visa", "mir", "vk pay")

fun main() {

    var cardType = "vk pay"
    var monthTransferAmount = 0
    var transferAmount = 0

    while (true) {
        println(
            """
            Выберите действие:
            1. Ввести тип оплаты. (Сейчас - $cardType)
            2. Ввести сумму переводов в этом месяце. (Сейчас - $monthTransferAmount)
            3. Ввести сумму перевода. (Сейчас - $transferAmount)
            4. Посчитать сумму перевода с учётом комиссии.
            """
        )
        when (readln().toInt()) {
            1 -> {
                println("\nВведите тип оплаты:")
                cardType = readln().lowercase()
            }

            2 -> {
                println("\nВведите сумму переводов в этом месяце:")
                monthTransferAmount = readln().toInt()
            }

            3 -> {
                println("Введите сумму перевода:")
                transferAmount = readln().toInt()
            }

            4 -> {
                if (paymentMethods.contains(cardType)) {
                    println(
                        "Сумма перевода с учётом комиссии : ${
                            calculateFee(cardType, monthTransferAmount, transferAmount)
                        } рублей."
                    )
                    cardType = "vk pay"
                    monthTransferAmount = 0
                    transferAmount = 0

                } else println("Тип оплаты \"$cardType\" не поддерживается.\n")
            }
        }
    }
}

fun calculateFee(cardType: String = "vk pay", monthTransferAmount: Int = 0, transferAmount: Int): Int {
    return if (checkLimits(cardType, monthTransferAmount, transferAmount)) {
        when (cardType) {
            "mastercard" -> transferFeeMastercardMaestro(transferAmount)
            "maestro" -> transferFeeMastercardMaestro(transferAmount)
            "visa" -> transferFeeVisaMir(transferAmount)
            "mir" -> transferFeeVisaMir(transferAmount)
            "vk pay" -> transferAmount
            else -> 0
        }
    } else {
        println("Превышены лимиты на перевод\n")
        exitProcess(-1)
    }
}

fun transferFeeMastercardMaestro(amount: Int): Int {
    return when {
        amount < 75000 -> amount
        else -> amount + (amount * 0.006 + 20).toInt()
    }
}

fun transferFeeVisaMir(amount: Int): Int {
    if (amount <= 35) {
        println("Сумма перевода не должна быть меньше размера комиссии (35 рублей).\n")
        exitProcess(-1)
    } else return when {
        amount * 0.0075 < 35 -> amount + 35
        else -> amount + (amount * 0.0075).toInt()
    }
}

fun checkLimits(cardType: String = "vk pay", monthTransferAmount: Int = 0, transferAmount: Int): Boolean {
    return when {
        (cardType == "mastercard" || cardType == "maestro"
                || cardType == "visa" || cardType == "mir")
                && monthTransferAmount < cardMonthTransferLimit
                && transferAmount < cardDayTransferLimit -> true

        cardType == "vk pay"
                && monthTransferAmount < vkPayMonthTransferLimit
                && transferAmount < vkPayDayTransferLimit -> true

        else -> false
    }
}