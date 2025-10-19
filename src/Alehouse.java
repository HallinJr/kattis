import java.util.ArrayList;
import java.util.Comparator;

public class Alehouse {

    ArrayList<int[]> residents ;
    int myInterval;
    int numberOfResidents;
    int lastPosition = 0;
    ArrayList<Integer> friendsMet = new ArrayList<>();



    public Alehouse(ArrayList<int[]> list, int myInterval, int numberOfResidents) {
        this.residents = list;
        this.myInterval = myInterval;
        this.numberOfResidents = numberOfResidents;
        sortedAfterStartTime();
        checkMaximumFriendsForInterval();
    }


    private void sortedAfterStartTime() {
        residents.sort(Comparator.comparingInt(a -> a[0]));
    }

    private void checkMaximumFriendsForInterval() {

        int myStartTime = residents.getFirst()[0] - myInterval;
        int myEndTime = myStartTime + myInterval;
        lastPosition = myStartTime;


        for (int i=0; i < numberOfResidents; i++) {
            if (myStartTime < lastPosition) {
                myStartTime = residents.get(i)[0] - myInterval;
                continue;
            }
            while (myStartTime <= residents.get(i)[1] + myInterval) {
                int friendsMetAtInterval = 0;

                for (int j=i+1; j < numberOfResidents; j++) {
                    if (myStartTime <= residents.get(j)[0] && myEndTime >= residents.get(j)[0] ||
                            residents.get(j)[1] >= myStartTime && residents.get(j)[1] <= myEndTime ||
                            residents.get(j)[0] <= myStartTime && residents.get(j)[1] >= myEndTime) {
                        friendsMetAtInterval++;

                    }
                }
                lastPosition = myEndTime;
                myStartTime++;
                friendsMet.add(friendsMetAtInterval);

            }

        }
        friendsMet.sort(Comparator.reverseOrder());
        System.out.println(friendsMet.getFirst());

    }

}