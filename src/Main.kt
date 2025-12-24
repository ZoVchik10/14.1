import java.io.File
import java.util.Scanner

fun main() {
    val file = File("FileTask")
    val scanner = Scanner(System.`in`)
    val lines = mutableListOf<String>()
    while (true) {
        println("добавить строку? (1.Да 2.Нет)")
        val answer = scanner.nextLine()
        when (answer) {
            "1" -> { //добавление строки
                println("Введите строку:")
                val line = scanner.nextLine()
                lines.add(line)
            }
            "2" -> break //выход из цикла
            else -> println("Некорректный ввод")
        }
    }
    file.writeText(lines.joinToString("\n"))//объединение всех строк в одну
    val totalLines = lines.size//считает кол-во строк
    val wordsCount = lines.map { it.split(" ").size }
    val maxWords = if (wordsCount.isNotEmpty()) wordsCount.maxOrNull()!! else 0//проверяет,не пустой ли список и наход макс. знач.
    val minWords = if (wordsCount.isNotEmpty()) wordsCount.minOrNull()!! else 0//проверяет,не пустой ли список и наход мин. знач

    println("\nОбщее количество строк: $totalLines")
    println("Максимальное количество слов: $maxWords")
    println("Минимальное количество слов: $minWords")
    println("Содержимое файла:")
    lines.forEachIndexed { index, line ->//дает нумерацию чему-то из списка
        println("${index + 1}: $line")//нумерация строк начинатся с 1
    }
    println("\nЖелаете заменить строку? (да/нет)")
    val answerReplace = scanner.nextLine().trim().lowercase()//в независимости от написания помогает коду понять
    if (answerReplace == "да" || answerReplace == "д" || answerReplace == "yes" || answerReplace == "y") {//возможные варианты ответа
        println("Введите номер строки для замены (от 1 до $totalLines):")
        val lineNumberInput = scanner.nextLine()//то что введено с клавиатуры пользователем
        val lineNumber = lineNumberInput.toIntOrNull()//преобразование строки в число
        if (lineNumber != null && lineNumber in 1..totalLines) {//проверяет введно ли число и что число в диапозоне от 1 до ...
            println("Введите новую строку:")
            val newLine = scanner.nextLine()
            lines[lineNumber - 1] = newLine//обращение к элементу из списка по индексу
            file.writeText(lines.joinToString("\n"))//перезапись файла с новыми данными
            println("\nОбновленное содержимое файла:")
            lines.forEachIndexed { index, line ->
                println("${index + 1}: $line")//вывод обновленных данных
            }

            val newTotalLines = lines.size
            val newWordsCount = lines.map { it.split(" ").size }
            val newMaxWords = if (newWordsCount.isNotEmpty()) newWordsCount.maxOrNull()!! else 0
            val newMinWords = if (newWordsCount.isNotEmpty()) newWordsCount.minOrNull()!! else 0
            println("\nОбщее количество строк: $newTotalLines")
            println("Максимальное количество: $newMaxWords")
            println("Минимальное количество: $newMinWords")//пересчет и вывод новой статы

        } else {
            println("Некорректный номер")
        }
    }
}