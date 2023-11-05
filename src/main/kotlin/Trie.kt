import java.io.File

data class Node(
    var valid: Boolean = false,
    val next: Array<Node?> = Array(26) { null }
)

class Trie {
    private val head = Node()

    fun insertWord(word: String) {
        var currentNode = head
        for (c in word) {
            if (currentNode.next[c - 'a'] == null) {
                currentNode.next[c - 'a'] = Node()
            }
            currentNode = currentNode.next[c - 'a']!!
        }
        currentNode.valid = true
    }

    fun insertDictionary(file: String) = File(file).forEachLine { insertWord(it) }

    fun checkWord(word: String): Boolean {
        var currentNode = head
        for (c in word) {
            if (currentNode.next[c - 'a'] == null) {
                return false
            }
            currentNode = currentNode.next[c - 'a']!!
        }
        return currentNode.valid
    }

    fun spellingBee(middle: Char, outers: List<Char>): List<String> {
        val candidates = hiddenBee(listOf(middle) + outers, head)

        // Candidates is a list of "words" but each word is a list of characters and needs to be checked and converted to string
        val answers = mutableListOf<String>()
        for (c in candidates) {
            if (c.contains(middle)) {
                answers.add(c.joinToString(""))
            }
        }

        return answers.filter { it.length >= 4}.sortedByDescending { it.length }
    }

    private fun hiddenBee(characters: List<Char>, currentNode: Node): List<List<Char>> {
        val answers = mutableListOf<List<Char>>()

        // If this node is valid, then this is a proper ending spot. Return empty string to get the path to here
        if (currentNode.valid)
            answers.add(emptyList())

        // Traverse next nodes
        for (c in characters) {
            val next = currentNode.next[c - 'a']
            if (next != null) {
                // Each suffix that's returned is a valid word and needs this character appended to it
                val suffixes = hiddenBee(characters, next)
                for (s in suffixes) {
                    answers.add(listOf(c) + s)
                }
            }
        }

        return answers
    }
}