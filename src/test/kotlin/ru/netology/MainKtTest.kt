package ru.netology

import calculateFee
import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateFeeMastercardTest1() {
        val cardType = "mastercard"
        val monthTransferAmount = 0
        val transferAmount = 1000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(1000, result)
    }

    @Test
    fun calculateFeeMastercardTest2() {
        val cardType = "mastercard"
        val monthTransferAmount = 0
        val transferAmount = 76000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(76476, result)
    }
    @Test
    fun calculateFeeMastercardTest3() {
        val cardType = "mastercard"
        val monthTransferAmount = 0
        val transferAmount = 151000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }
    @Test
    fun calculateFeeMastercardTest4() {
        val cardType = "mastercard"
        val monthTransferAmount = 70_000
        val transferAmount = 6000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(6056, result)
    }
    @Test
    fun calculateFeeMastercardTest5() {
        val cardType = "mastercard"
        val monthTransferAmount = 590_000
        val transferAmount = 20_000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }
    @Test
    fun calculateFeeMastercardTest6() {
        val cardType = "incorrect"
        val monthTransferAmount = 0
        val transferAmount = 20_000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }
    @Test
    fun calculateFeeMaestroTest1() {
        val cardType = "maestro"
        val monthTransferAmount = 0
        val transferAmount = 1000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(1000, result)
    }
    @Test
    fun calculateFeeVisaTest1() {
        val cardType = "visa"
        val monthTransferAmount = 0
        val transferAmount = 1000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(1035, result)
    }
    @Test
    fun calculateFeeVisaTest2() {
        val cardType = "visa"
        val monthTransferAmount = 0
        val transferAmount = 30

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }
    @Test
    fun calculateFeeVisaTest3() {
        val cardType = "visa"
        val monthTransferAmount = 0
        val transferAmount = 10_000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(10075, result)
    }
    @Test
    fun calculateFeeMirTest1() {
        val cardType = "mir"
        val monthTransferAmount = 0
        val transferAmount = 1000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(1035, result)
    }
    @Test
    fun calculateFeeVkPayTest1() {
        val cardType = "vk pay"
        val monthTransferAmount = 0
        val transferAmount = 1000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(1000, result)
    }
    @Test
    fun calculateFeeVkPayTest2() {
        val cardType = "vk pay"
        val monthTransferAmount = 0
        val transferAmount = 16000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }
    @Test
    fun calculateFeeVkPayTest3() {
        val cardType = "vk pay"
        val monthTransferAmount = 30_000
        val transferAmount = 20_000

        val result = calculateFee(cardType, monthTransferAmount, transferAmount)

        assertEquals(0, result)
    }

}