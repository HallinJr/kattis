import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Alehouse {

    ArrayList<String> residents ;
    int myInterval;
    int numberOfResidents;



    public Alehouse(ArrayList<String> list, int myInterval, int numberOfResidents) {
        this.residents = list;
        this.myInterval = myInterval;
        this.numberOfResidents = numberOfResidents;
        checkMaximumFriendsForInterval();
    }

    private int getMaxEndTime() {
        Integer[] endTimes = new Integer[numberOfResidents];
        for (int i = 0; i < numberOfResidents; i++) {
            int endTime = getResidentsTime(i)[1];
            endTimes[i] = endTime;
        }
        Arrays.sort(endTimes, Collections.reverseOrder());

        return endTimes[0];
    }

    private int[] getResidentsTime(int i) {
        String resident = residents.get(i);

        String[] splitResident = resident.split(" ");

        int startTime = Integer.parseInt(splitResident[0]);
        int endTime = Integer.parseInt(splitResident[1]);
        return new int[]{startTime, endTime};
    }

    private void checkMaximumFriendsForInterval() {
        int[] startTimes = new int[numberOfResidents];
        int[] endTimes = new int[numberOfResidents];
        ArrayList<Integer> friendsMet = new ArrayList<>();

        for (int i= 0; i < numberOfResidents; i++) {
            startTimes[i] = getResidentsTime(i)[0];
            endTimes[i] = getResidentsTime(i)[1];
        }

        for (int i = 0; i < numberOfResidents; i++) {
            int myStartTime = startTimes[i] - myInterval;
            int myEndTime = myStartTime + myInterval;
            int numberOfFriendsMetAtThatTime = 0;


            while (myStartTime < endTimes[i]) {
                for (int j = i + 1; j < numberOfResidents; j++) {
                    if (myStartTime <= getResidentsTime(j)[0] && myEndTime >= getResidentsTime(j)[0]) {
                        numberOfFriendsMetAtThatTime++;
                    } else if (getResidentsTime(j)[1] >= myStartTime && getResidentsTime(j)[1] <= myEndTime) {
                        numberOfFriendsMetAtThatTime++;
                    }
                    myStartTime++;
//                    myEndTime++;
                }
            }

                friendsMet.add(numberOfFriendsMetAtThatTime);
        }

        Collections.sort(friendsMet);
        Collections.reverse(friendsMet);
//        System.out.println(friendsMet);
        System.out.println(friendsMet.getFirst());
    }

}
