import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) return;
        int numberOfStrings = Integer.parseInt(first.trim());

        ArrayList<String> strings = new ArrayList<>(numberOfStrings);
        for (int i = 0; i < numberOfStrings; i++) {
            String s = br.readLine();
            if (s == null) s = "";
            strings.add(s);
        }

        ArrayList<String> combinations = new ArrayList<>(numberOfStrings - 1);
        for (int i = 0; i < numberOfStrings - 1; i++) {
            String op = br.readLine();
            if (op == null) op = "";
            combinations.add(op);
        }

        // construct JoinStrings (it prints the final result in constructor)
        new JoinStrings(numberOfStrings, strings, combinations);
    }
}
