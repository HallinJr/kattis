import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        int residents = sc.nextInt();
        int myTime = sc.nextInt();

        for (int i = 0; i < residents; i++) {
            int startTime = sc.nextInt();
            int endTime = sc.nextInt();
            String startAndEndTime = (startTime + " " + endTime);

            list.add(startAndEndTime);
        }
        new Alehouse(list, myTime, residents);

    }
}