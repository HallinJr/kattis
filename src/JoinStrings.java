import java.util.ArrayList;

public class JoinStrings {

    int numberOfStrings;
    ArrayList<String> strings;
    ArrayList<String> combinations;

    // Internal node representing an immutable input segment
    private static final class Part {
        final String s;
        Part next;
        Part(String s) { this.s = s; }
    }

    public JoinStrings(int count, ArrayList<String> list1, ArrayList<String> list2) {
        this.numberOfStrings = count;
        this.strings = list1;
        this.combinations = list2;

        System.out.println(processJoins());
    }

    private String processJoins() {
        if (numberOfStrings == 0) return "";
        if (numberOfStrings == 1) {
            // safe: list1 had one string
            return strings.get(0);
        }

        // Prepare linked-list heads/tails and lengths for each index
        Part[] head = new Part[numberOfStrings];
        Part[] tail = new Part[numberOfStrings];
        int[] len = new int[numberOfStrings];

        for (int i = 0; i < numberOfStrings; i++) {
            String s = strings.get(i);
            if (s == null) s = "";
            Part p = new Part(s);
            head[i] = p;
            tail[i] = p;
            len[i] = s.length();
        }

        int lastTarget = -1;

        // Perform the n-1 join operations in order
        for (int i = 0; i < combinations.size(); i++) {
            String op = combinations.get(i).trim();
            int space = op.indexOf(' ');
            int a = Integer.parseInt(op.substring(0, space)) - 1; // joinTo
            int b = Integer.parseInt(op.substring(space + 1)) - 1; // toJoin

            // if b is already empty, nothing to append
            if (head[b] == null) {
                lastTarget = a;
                continue;
            }

            if (head[a] == null) {
                // a is empty -> move b's list to a
                head[a] = head[b];
                tail[a] = tail[b];
            } else {
                // append b after a
                tail[a].next = head[b];
                tail[a] = tail[b];
            }
            len[a] += len[b];

            // empty b
            head[b] = null;
            tail[b] = null;
            len[b] = 0;

            lastTarget = a;
        }

        // find the index with data (prefer lastTarget)
        int idx = -1;
        if (lastTarget >= 0 && len[lastTarget] > 0) idx = lastTarget;
        else {
            for (int i = 0; i < numberOfStrings; i++) {
                if (len[i] > 0) { idx = i; break; }
            }
        }

        if (idx == -1) return "";

        // One final copy: allocate char[] and copy every segment into it
        char[] out = new char[len[idx]];
        int pos = 0;
        for (Part p = head[idx]; p != null; p = p.next) {
            String s = p.s;
            int L = s.length();
            s.getChars(0, L, out, pos);
            pos += L;
        }

        return new String(out);
    }
}
