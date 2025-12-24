import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int howManySeq = 0;
        
        String s = sc.nextLine();
        for (int i = 0; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            char nextCh = s.charAt(i + 1);
            if (ch == '(' && nextCh == ')') {
                howManySeq++;
            }
        }
        ArrayList<String> sortSeq = new ArrayList<>();
        sortSeq.addAll(Arrays.asList(s.split("\\s*\\(\\)\\s*")).subList(0, howManySeq));

        new CalcIsShortForCalculator(sortSeq, howManySeq);

    }
}
