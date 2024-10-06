package ru.netology

const val MONTH_LIMIT = 600_000
const val TRANSACTION_LIMIT = 150_000
const val MONETARY_THRESHOLD = 75_000

fun main() {
    val cardType = "Mastercard"
    val transferAmount = 100000
    val totalTransfersThisMonth = 0
    calculateCommission(cardType, totalTransfersThisMonth, transferAmount)
}

fun calculateCommission(cardType: String = "Мир", totalTransfersThisMonth: Int = 0, transferAmount: Int): Double {
    var commission = 0.0

    if (totalTransfersThisMonth + transferAmount > MONTH_LIMIT || transferAmount > TRANSACTION_LIMIT) {
        println("Операция заблокирована. Сумма перевода превышает лимит.")
        return commission
    }

    when (cardType) {
        "Mastercard" -> {
            if (totalTransfersThisMonth > MONETARY_THRESHOLD) {
                commission = transferAmount * 0.006 + 20
            } else if (transferAmount > MONETARY_THRESHOLD) {
                (totalTransfersThisMonth + transferAmount > MONETARY_THRESHOLD)
                commission = (totalTransfersThisMonth + transferAmount - MONETARY_THRESHOLD) * 0.006 + 20
            }
        }
        "Visa" -> {
            commission = transferAmount * 0.0075
            if (commission < 35) {
                commission = 35.0
            }
        }
        "Мир" -> {
            commission = 0.0
        }
        else -> {
            println("Неизвестный тип карты")
            return commission
        }
    }

    println("Комиссия за перевод: $commission рублей")
    return commission
}