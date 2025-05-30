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
        if (!word.matches("[a-z]+") || word.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        trie.insert(word);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
