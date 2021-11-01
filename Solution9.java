import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Solution9 {
    public static void main(String[] args) {
        Record[] records = {
            new Record(123, 10, 22),
            new Record(124, 20, 22),
            new Record(125, 7, 12),
            new Record(126, 12, 32),
            new Record(127, 10, 20),
        };
        RecordContext ctx = new RecordContext(List.of(records));
        
        List<Record> res = ctx.query(10);
        res.forEach(r -> System.out.printf("{%d, (%d, %d)}\n", 
                                        r.orderId, r.inTime, r.outTime));
    }
}

class RecordContext {
    
    public List<Record> query(int A) {
        // bin search inIndex, find element <= A
        List<Record> result = new LinkedList<>();
        int l = 0, r = records.size() - 1;
        int inRef = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            Index elm = inIndexs.get(mid);
            if (elm.time == A) {
                inRef = Math.max(inRef, mid); // if there are equal elms, keep the last one
                r = mid - 1;
            } else if (elm.time > A) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (inRef == -1) {
            return result;
        }

        return inIndexs.subList(0, inRef + 1)
                    .stream()
                    .map(index -> records.get(index.ref))
                    .filter(record -> record.outTime >= A)
                    .sorted((a, b) -> a.orderId - b.orderId)
                    .collect(Collectors.toList());
    }

    public RecordContext(List<Record> r) {
        records = new ArrayList<>(r);
        createIndexs();
    }

    private List<Record> records;

    private List<Index> inIndexs;

    private void createIndexs() {
        inIndexs = new ArrayList<>(records.size());
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            inIndexs.add(new Index(record.inTime, i));
        }
        inIndexs.sort((a, b) -> a.time - b.time);
    }


    class Index {
        int time;
        int ref;

        Index(int _time, int _ref) {
            time = _time;
            ref = _ref;
        }
    }
}

class Record {
    int orderId;
    int inTime;
    int outTime;

    Record(int _orderId, int _inTime, int _outTime) {
        orderId = _orderId;
        inTime = _inTime;
        outTime = _outTime;
    }
}
