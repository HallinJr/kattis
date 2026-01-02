import java.util.ArrayList;

public class Brexit implements Cloneable {

    private ReadInput input;
    private ArrayList<Integer>[] updatedPartners;
    private final int[] numberOfPartnersFromStart;
    private final int[] numberOfPartnersCurrent;
    private boolean hasChanged;
    private final int[] hasLeft;

    public Brexit(ReadInput readInput) {
        this.input = readInput;
        hasLeft = new int[input.getTotalCountries()];
        updatedPartners = input.getPartners();
        numberOfPartnersFromStart = input.getOriginalNumberOfPartners();
        numberOfPartnersCurrent = input.getOriginalNumberOfPartners().clone();
        if (input.getMyCountry() == input.getFirstToLeave()) {
            System.out.println("leave");
            System.exit(0);
        } else if (input.getOriginalNumberOfPartnersForCountry(input.getMyCountry()) == 0) {
            System.out.println("gay");
            System.exit(0);
        }

        anotherMasterAlgorithm();
    }


    public void anotherMasterAlgorithm() {
        hasChanged = true;

        masterAlgorithm(input.getFirstToLeave());

        while (hasChanged) {
            int firstCount = sumOfAll(numberOfPartnersCurrent);
            for (int i = 0; i < input.getTotalCountries(); i++) {
                if (i + 1 == hasLeft[i]) {
                    continue;
                }
                if (is50PercentOrMoreGone(numberOfPartnersFromStart[i], numberOfPartnersCurrent[i])) {

                    masterAlgorithm(i + 1);
                }
            }
            int secondCount = sumOfAll(numberOfPartnersCurrent);
            if (firstCount == secondCount) {
                hasChanged = false;
            }
        }
        System.out.println("stay");

    }

    public void masterAlgorithm (int remove) {
        hasLeft[remove - 1] = remove;


        if (is50PercentOrMoreGone(numberOfPartnersFromStart[input.getMyCountry() - 1], numberOfPartnersCurrent[input.getMyCountry() - 1])) {
            System.out.println("leave");
            System.exit(0);
        }

        int firstCount = sumOfAll(numberOfPartnersFromStart);

        int index = 0;
        for (ArrayList<Integer> pair : updatedPartners) {

            if (pair.isEmpty()) {
                continue;
            }

            if (pair.getFirst() == remove) {
                numberOfPartnersCurrent[pair.getLast() - 1]--;
                updatedPartners[index].removeAll(pair);
            } else if (pair.getLast() == remove) {
                numberOfPartnersCurrent[pair.getFirst() - 1]--;
                updatedPartners[index].removeAll(pair);
            }


            index++;
        }

        int secondCount = sumOfAll(numberOfPartnersCurrent);
        if (firstCount == secondCount) {
            hasChanged = false;
        }



    }

    private int sumOfAll(int[] toCount) {
        int sum = 0;
        for (int i = 0; i < toCount.length; i++) {
            sum += toCount[i];
        }
        return sum;
    }

    public boolean is50PercentOrMoreGone(int original, int newNum) {
        double half =  original / 2;
        return half >= (double) newNum;
    }
}
