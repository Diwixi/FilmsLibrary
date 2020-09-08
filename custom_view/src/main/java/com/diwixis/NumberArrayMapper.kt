package com.diwixis


fun Int.toArray() = when (this) {
    0 -> ZERO
    1 -> ONE
    2 -> TWO
    3 -> THREE
    4 -> FOUR
    5 -> FIVE
    6 -> SIX
    7 -> SEVEN
    8 -> EIGHT
    9 -> NINE
    else -> ZERO
}

private val ZERO = arrayOf(
    arrayOf(true, true, true),
    arrayOf(true, false, true),
    arrayOf(true, false, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
)

private val ONE = arrayOf(
    arrayOf(false, false, true),
    arrayOf(false, true, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
)

private val TWO = arrayOf(
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(true, true, true),
    arrayOf(true, false, false),
    arrayOf(true, true, true),
)

private val THREE = arrayOf(
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(false, true, true),
    arrayOf(false, false, true),
    arrayOf(true, true, true),
)

private val FOUR = arrayOf(
    arrayOf(true, false, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
)

private val FIVE = arrayOf(
    arrayOf(true, true, true),
    arrayOf(true, false, false),
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(true, true, true),
)

private val SIX = arrayOf(
    arrayOf(true, true, true),
    arrayOf(true, false, false),
    arrayOf(true, true, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
)

private val SEVEN = arrayOf(
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
)

private val EIGHT = arrayOf(
    arrayOf(true, true, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
)

private val NINE = arrayOf(
    arrayOf(true, true, true),
    arrayOf(true, false, true),
    arrayOf(true, true, true),
    arrayOf(false, false, true),
    arrayOf(false, false, true),
)