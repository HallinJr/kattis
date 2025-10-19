import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Alehouse {

    ArrayList<String> residents ;
    int myInterval;
    int numberOfResidents;
    ArrayList<Integer> friendsMet = new ArrayList<>();


    public Alehouse(ArrayList<String> list, int myInterval, int numberOfResidents) {
        this.residents = list;
        this.myInterval = myInterval;
        this.numberOfResidents = numberOfResidents;
        checkMaximumFriendsForInterval();
    }

    private int[] getMaxEndTimeAndFirstStartTime() {
        Integer[] endTimes = new Integer[numberOfResidents];
        Integer[] startTimes = new Integer[numberOfResidents];

        for (int i = 0; i < numberOfResidents; i++) {
            int[] times = getResidentsTime(i);
            startTimes[i] = times[0];
            endTimes[i] = times[1];
        }

        Arrays.sort(endTimes, Collections.reverseOrder());
        Arrays.sort(startTimes);

        return new int[]{startTimes[0], endTimes[0]};
    }

    private int[] getResidentsTime(int i) {
        String resident = residents.get(i);

        String[] splitResident = resident.split(" ");

        int startTime = Integer.parseInt(splitResident[0]);
        int endTime = Integer.parseInt(splitResident[1]);
        return new int[]{startTime, endTime};
    }

    private void checkMaximumFriendsForInterval() {
        int myStartTime = getMaxEndTimeAndFirstStartTime()[0];

        while (myStartTime + myInterval <= getMaxEndTimeAndFirstStartTime()[1]) {
            int myEndTime = myStartTime + myInterval;
            int numberOfFriends = 0;
            for (int i = 0; i < numberOfResidents; i++) {

                if (myStartTime <= getResidentsTime(i)[0] && myEndTime >= getResidentsTime(i)[0] ||
                        getResidentsTime(i)[1] >= myStartTime && getResidentsTime(i)[1] <= myEndTime ||
                        getResidentsTime(i)[0] <= myStartTime && getResidentsTime(i)[1] >= myEndTime) {
                    numberOfFriends++;
                }
            }
            friendsMet.add(numberOfFriends);


            myStartTime++;

        }
        Collections.sort(friendsMet);

        System.out.println(friendsMet.getLast());
    }
}