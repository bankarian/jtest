import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution7 {
    public static void main(String[] args) {
        MasterContext ctx = new MasterContext();
        String[] ss = { "can I take this orange", "orange is my favorite fruit", "fruit is beneficial to our health" };
        ctx.runJob(List.of(ss)).whenComplete((res, err) -> {
            for (Map.Entry<String, Integer> entry : ctx.result.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        });
    }
}

class MasterContext {
    ConcurrentMap<String, Integer> result = new ConcurrentHashMap<>();
    private BlockingQueue<Intermediate> intermediates = new LinkedBlockingQueue<>();
    
    CompletableFuture<Void> runJob(List<String> lines) {
        CompletableFuture<Void> mapFuture = CompletableFuture.runAsync(() -> {
            for (String line : lines) {
                map(line);
            }
        });

        CompletableFuture<Void> reduceFuture = CompletableFuture.runAsync(() -> {
            while (!mapFuture.isDone() || !intermediates.isEmpty()) {
                Intermediate intermediate = intermediates.poll();
                if (intermediate != null) {
                    reduce(intermediate);
                }
            }  
        });

        return CompletableFuture.allOf(mapFuture, reduceFuture);
    }

    private void map(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            intermediates.offer(new Intermediate(word, 1));
        }
    }

    private void reduce(Intermediate intermediate) {
        result.put(intermediate.word, result.getOrDefault(intermediate.word, 0) + intermediate.value);
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