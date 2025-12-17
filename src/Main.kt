import java.io.File

fun main() {
    val fileName = "strings.txt"
    val file = File(fileName)
    while (true) {
        println("Хотите добавить строку в файл? (да/нет):")
        val answer = readlnOrNull()?.lowercase()

        if (answer == "да") {
            println("Введите строку:")
            val line = readlnOrNull()?.trim() ?: ""

            if (file.exists()) {
                file.appendText("$line\n")
            } else {
                file.writeText("$line\n")
            }
        } else if (answer == "нет") {
            break
        } else {
            println("Пожалуйста, ответьте 'да' или 'нет'")
        }
    }

    val lines = if (file.exists()) {
        file.readLines().filter { it.isNotBlank() }
    } else {
        emptyList()
    }

    if (lines.isEmpty()) {
        println("Файл пуст.")
        return
    }

    println("\n=== СОДЕРЖИМОЕ ФАЙЛА ===")
    lines.forEachIndexed { index, line ->
        println("${index + 1}: $line")
    }

    val wordCounts = lines.map { it.split("\\s+".toRegex()).filter { word -> word.isNotBlank() }.size }

    val maxWords = wordCounts.maxOrNull() ?: 0
    val minWords = wordCounts.minOrNull() ?: 0

    println("\n=== ИНФОРМАЦИЯ О ФАЙЛЕ ===")
    println("Общее количество строк: ${lines.size}")
    println("Максимальное количество слов в строке: $maxWords")
    println("Минимальное количество слов в строке: $minWords")

    println("\nКоличество слов в каждой строке:")
    wordCounts.forEachIndexed { index, count ->
        println("Строка ${index + 1}: $count слов")
    }

    while (true) {
        println("\nХотите заменить строку? (да/нет):")
        val answer = readlnOrNull()?.lowercase()

        if (answer == "да") {
            println("Введите номер строки для замены (1-${lines.size}):")
            val lineNumber = readlnOrNull()?.toIntOrNull()

            if (lineNumber == null || lineNumber < 1 || lineNumber > lines.size) {
                println("Неверный номер строки. Должно быть число от 1 до ${lines.size}")
                continue
            }

            println("Введите новую строку:")
            val newLine = readlnOrNull()?.trim() ?: ""

            val updatedLines = lines.toMutableList()
            updatedLines[lineNumber - 1] = newLine

            val cleanedLines = updatedLines.filter { it.isNotBlank() }
            file.writeText(cleanedLines.joinToString("\n"))

            println("\n=== ОБНОВЛЕННОЕ СОДЕРЖИМОЕ ФАЙЛА ===")
            cleanedLines.forEachIndexed { index, line ->
                println("${index + 1}: $line")
            }

            val newWordCounts = cleanedLines.map {
                it.split("\\s+".toRegex()).filter { word -> word.isNotBlank() }.size
            }

            println("\nКоличество слов в каждой строке:")
            newWordCounts.forEachIndexed { index, count ->
                println("Строка ${index + 1}: $count слов")
            }

            break

        } else if (answer == "нет") {
            break
        } else {
            println("Пожалуйста, ответьте 'да' или 'нет'")
        }
    }

    println("\nПрограмма завершена.")
}