import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int smallBook = sc.nextInt();
        int mediumBook = sc.nextInt();
        int bigBook = sc.nextInt();
        int shelveLength = sc.nextInt();
        int totalBooks = smallBook + mediumBook + bigBook;
        int shelvesNeeded = 0;

        if (smallBook > 0 && mediumBook == 0 && bigBook == 0) {
            if (smallBook == 1) System.out.println(2);
            else {
                System.out.println((int) Math.ceil((double) smallBook / shelveLength));
            }
        } else {

            while (totalBooks != 0) {
                int spaceLeft = shelveLength;
                boolean isBigBookInShelf = false;
                while (spaceLeft != 0) {

                    if (bigBook != 0 && spaceLeft >= 3) {
                        spaceLeft -= 3;
                        bigBook--;
                        isBigBookInShelf = true;
                    } else if (isBigBookInShelf && spaceLeft == 1 && mediumBook > 1) {
                        bigBook++;
                        spaceLeft += 3;
                        mediumBook -= 2;
                        spaceLeft -= 4;

                    } else if (mediumBook != 0 && spaceLeft >= 2) {
                        spaceLeft -= 2;
                        mediumBook--;
                    } else if (smallBook != 0) {
                        spaceLeft -= 1;
                        smallBook--;
                    } else {
                        break;
                    }
                    totalBooks--;
                }
                shelvesNeeded++;

            }
            System.out.println(shelvesNeeded);

        }
    }
}