import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class Solution7 {
    public static void main(String[] args) {
        MasterContext ctx = new MasterContext();
        String[] ss = { "can I take this orange", "orange is my favorite fruit", "fruit is beneficial to our health" };
        ctx.countWords(List.of(ss)).whenComplete((res, err) -> {
            for (Map.Entry<String, Integer> entry : ctx.result.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        });
    }
}

class MasterContext {
    Queue<Intermediate> intermediates = new LinkedList<>();
    Map<String, Integer> result = new HashMap<>();

    CompletableFuture<Void> countWords(List<String> lines) {
        return CompletableFuture.runAsync(() -> {
            for (String line : lines) {
                map(line);
            }
        }).thenRun(() -> {
            reduce();
        });
    }

    private void map(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            intermediates.offer(new Intermediate(word, 1));
        }
    }

    private void reduce() {
        for (Intermediate intermediate : intermediates) {
            result.put(intermediate.word, result.getOrDefault(intermediate.word, 0) + intermediate.value);
        }
    }
}

class Intermediate {
    String word;
    int value;

    Intermediate(String _word, int _value) {
        word = _word;
        value = _value;
    }
}