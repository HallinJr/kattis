import java.util.ArrayList;
import java.util.Scanner;

public class ReadInput {
    Scanner sc;
    private int totalCountries;
    private int numberOfPartnerShips;
    private int myCountry;
    private int firstToLeave;
    private final String firstLine;
    private int[] partners;



    public ReadInput(Scanner scanner) {
        this.sc = scanner;
        this.firstLine = sc.nextLine();
        getFirstLineValues();
        partners = new int[totalCountries];
    }

    public void getFirstLineValues() {
        totalCountries = splitLine(0);
        numberOfPartnerShips = splitLine(1);
        myCountry = splitLine(2);
        firstToLeave = splitLine(3);
    }

    private int splitLine(int index) {

        return Integer.parseInt(firstLine.split(" ")[index]);
    }

    public ArrayList<Integer>[] getPartners() {
        final ArrayList<Integer>[] partnerShip = new ArrayList[getNumberOfPartnerShips()];

        for (int i = 0; i < getNumberOfPartnerShips(); i++) {
            partnerShip[i] = new ArrayList<>();
        }

        for (int i = 0; i < getNumberOfPartnerShips(); i++) {
            String pair = sc.nextLine();
            int firstVal = Integer.parseInt(pair.split(" ")[0]);
            int secondVal = Integer.parseInt(pair.split(" ")[1]);
            /*  print for visual    */
//            System.out.println(pair);
            partnerShip[i].add(firstVal);
            partners[firstVal - 1]++;
            partnerShip[i].add(secondVal);
            partners[secondVal - 1]++;

        }

        /*  print for visual    */
//        for (int i = 0; i < numberOfPartnerShips; i++) {
//            System.out.print(partnerShip[i].get(0));
//            System.out.println(partnerShip[i].get(1));
//        }

        return partnerShip;
    }

    public int[] getOriginalNumberOfPartners() {
        return partners;
    }

    public int getOriginalNumberOfPartnersForCountry(int country) {
        return partners[country - 1];
    }

    public int getTotalCountries() {
        return totalCountries;
    }

    public int getNumberOfPartnerShips() {
        return numberOfPartnerShips;
    }

    public int getMyCountry() {
        return myCountry;
    }

    public int getFirstToLeave() {
        return firstToLeave;
    }

}
