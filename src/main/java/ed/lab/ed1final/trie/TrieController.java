package ed.lab.ed1final.trie;
import java.util.*;

public class TrieController {
    private final Trie trie;
    public TrieController(Trie trie){
        this.trie = trie;
    }
    @PostMapping("/{word}")
    public ResponseEntity<Void> insertword(@PathVariable)
}

