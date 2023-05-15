const val CARD_DAY_TRANSFER_LIMIT = 150_000
const val CARD_MONTH_TRANSFER_LIMIT = 600_000

const val VK_PAY_DAY_TRANSFER_LIMIT = 15000
const val VK_PAY_MONTH_TRANSFER_LIMIT = 40_000

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
                    val fee = calculateFee(cardType, monthTransferAmount, transferAmount)
                    if (fee != 0) {
                        println("Сумма перевода с учётом комиссии : $fee рублей.")
                    }
                    cardType = "vk pay"
                    monthTransferAmount = 0
                    transferAmount = 0

                } else println("Тип оплаты \"$cardType\" не поддерживается.\n")
            }
        }
    }
}

fun calculateFee(cardType: String, monthTransferAmount: Int , transferAmount: Int): Int {
    return if (checkLimits(cardType, monthTransferAmount, transferAmount)) {
        when (cardType) {
            "mastercard", "maestro" -> transferFeeMastercardMaestro(transferAmount, monthTransferAmount)
            "visa", "mir" -> transferFeeVisaMir(transferAmount)
            "vk pay" -> transferAmount
            else -> 0
        }
    } else {
        println("Превышены лимиты на перевод\n")
        0
    }
}

fun transferFeeMastercardMaestro(amount: Int, monthTransferAmount: Int): Int {
    return when {
        monthTransferAmount + amount < 75000 -> amount
        else -> amount + (amount * 0.006 + 20).toInt()
    }
}

fun transferFeeVisaMir(amount: Int): Int {
    return if (amount <= 35) {
        println("Сумма перевода не должна быть меньше размера комиссии (35 рублей).\n")
        0
    } else when {
        amount * 0.0075 < 35 -> amount + 35
        else -> amount + (amount * 0.0075).toInt()
    }
}

fun checkLimits(cardType: String , monthTransferAmount: Int , transferAmount: Int): Boolean {
    return when {
        (cardType == "mastercard" || cardType == "maestro"
                || cardType == "visa" || cardType == "mir")
                && transferAmount + monthTransferAmount < CARD_MONTH_TRANSFER_LIMIT
                && transferAmount < CARD_DAY_TRANSFER_LIMIT -> true

        cardType == "vk pay"
                && transferAmount + monthTransferAmount < VK_PAY_MONTH_TRANSFER_LIMIT
                && transferAmount < VK_PAY_DAY_TRANSFER_LIMIT -> true

        else -> false
    }
}