package io.fedelopez.robot

fun move(commands: String): String {
    val matrix = doMove(commands, false, 0, mapOf(*(0..9).map { it to 0 }.toTypedArray()))
    return matrix.values.joinToString("") { Integer.toHexString(it) }
}

private tailrec fun doMove(commands: String, picked: Boolean, currentIndex: Int, matrix: Map<Int, Int>): Map<Int, Int> {
    if (commands.isEmpty()) {
        return matrix
    }
    return when (commands[0]) {
        'M' -> doMove(commands.substring(1), picked, currentIndex + 1, matrix)
        'L' -> {
            val total = matrix.getOrDefault(currentIndex, 0)
            if (picked && currentIndex < 10 && total < 15) {
                doMove(commands.substring(1), false, 0, matrix.plus(currentIndex to total + 1))
            } else {
                doMove(commands.substring(1), picked, currentIndex, matrix)
            }
        }
        'P' -> doMove(commands.substring(1), true, 0, matrix)
        else -> doMove(commands.substring(1), picked, currentIndex, matrix)
    }
}