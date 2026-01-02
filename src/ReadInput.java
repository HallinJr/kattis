import java.util.Scanner;

public class ReadInput {
    private final Scanner sc;
    private int numberOfHotDogs;
    private int numberOfSodas;

    public ReadInput(Scanner scanner) {
        this.sc = scanner;
        initValues();
    }

    public void initValues() {
        this.numberOfHotDogs = sc.nextInt();
        this.numberOfSodas = sc.nextInt();
    }

    public int getNumberOfHotDogs() {
        return numberOfHotDogs;
    }

    public int getNumberOfSodas() {
        return numberOfSodas;
    }
}
