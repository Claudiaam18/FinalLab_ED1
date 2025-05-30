package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class Trie {
    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        node.count++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) return 0;
            node = node.children.get(ch);
        }
        return node.count;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) return 0;
            node = node.children.get(ch);
        }
        return countPrefix(node);
    }

    private int countPrefix(TrieNode node) {
        int sum = node.count;
        for (TrieNode child : node.children.values()) {
            sum += countPrefix(child);
        }
        return sum;
    }

    public void erase(String word) {
        eraseHelper(root, word, 0);
    }

    private boolean eraseHelper(TrieNode node, String word, int index) {
        if (index == word.length()) {
            if (node.count > 0) node.count--;
            return node.children.isEmpty() && node.count == 0;
        }

        char ch = word.charAt(index);
        if (!node.children.containsKey(ch)) return false;

        boolean shouldDelete = eraseHelper(node.children.get(ch), word, index + 1);
        if (shouldDelete) node.children.remove(ch);

        return node.children.isEmpty() && node.count == 0;
    }
}
