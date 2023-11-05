fun main() {
    val trie = Trie()
    trie.insertDictionary("src/main/resources/words_alpha.txt")

//    while (true) {
//        print("Enter word to check > ")
//        val input = readlnOrNull()
//        if (input != null) {
//            val validWord = trie.checkWord(input)
//            if (validWord)
//                println("$input is a valid word")
//            else
//                println("$input is not a valid word")
//        }
//    }

    val answers = trie.spellingBee('t', listOf('h', 'd', 'n', 'g', 'o', 'i'))
    for (a in answers)
        println(a)
}