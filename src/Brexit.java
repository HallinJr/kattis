import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Brexit implements Cloneable {

    private ReadInput input;
    private int totalCountries;
    int myCountry;
    private final int[] numberOfPartnersFromStart;
    private final int[] numberOfPartnersCurrent;
    private final int[] hasLeft;
    private final ArrayList<Integer>[] partners;

    public Brexit(ReadInput readInput) {
        this.input = readInput;
        this.partners = input.getPartners();
        this.totalCountries = input.getTotalCountries();
        this.myCountry = input.getMyCountry();
        this.hasLeft = new int[input.getTotalCountries() + 1];
        this.numberOfPartnersFromStart = input.getOriginalNumberOfPartners();
        this.numberOfPartnersCurrent = input.getOriginalNumberOfPartners().clone();

        masterAlgorithm(input.getFirstToLeave());
    }


    public void masterAlgorithm(int firstToLeave) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(firstToLeave);

        while (!queue.isEmpty()) {
            int leaving = queue.poll();

            if (hasLeft[leaving] == leaving) continue;
            hasLeft[leaving] = leaving;

            if (leaving == input.getMyCountry()) {
                System.out.println("leave");
                return;
            }

            for (int partner : partners[leaving]) {
                if (hasLeft[partner] == partner) continue;

                numberOfPartnersCurrent[partner]--;

                if (is50PercentOrMoreGone(numberOfPartnersFromStart[partner], numberOfPartnersCurrent[partner])) {
                    queue.add(partner);
                }
            }
        }
        System.out.println("stay");

    }

    public boolean is50PercentOrMoreGone(int original, int newNum) {
        double half =  original / 2;
        return half >= (double) newNum || newNum == 0.0;
    }
}
