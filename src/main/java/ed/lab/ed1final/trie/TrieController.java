package ed.lab.ed1final.trie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/trie")
public class TrieController {

    private final Trie trie;

    public TrieController(Trie trie) {
        this.trie = trie;
    }

    @PostMapping("/{word}")
    public ResponseEntity<Void> insertWord(@PathVariable String word) {
        // Validar que la palabra sea en minúsculas y solo contenga letras
        if (word == null || word.isEmpty() || !word.matches("[a-z]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        trie.insert(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{word}/count")
    public ResponseEntity<Map<String, Object>> countWord(@PathVariable String word) {
        return ResponseEntity.ok(Map.of(
                "word", word,
                "wordsEqualTo", trie.countWordsEqualTo(word)
        ));
    }

    @GetMapping("/{prefix}/prefix")
    public ResponseEntity<Map<String, Object>> countPrefix(@PathVariable String prefix) {
        return ResponseEntity.ok(Map.of(
                "word", prefix,
                "wordsStartingWith", trie.countWordsStartingWith(prefix)
        ));
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<Void> deleteWord(@PathVariable String word) {
        trie.erase(word);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
