import java.util.ArrayList;
import java.util.Collections;

/**
 * The Alehouse class calculates the maximum number of friends
 * that can be present in the alehouse within a given time interval.
 */
public class Alehouse {

    // List of residents' time intervals (start and end times)
    ArrayList<int[]> residents;
    // The maximum time interval (myInterval) to consider
    int myInterval;
    // Total number of residents
    int numberOfResidents;

    /**
     * Constructor for the Alehouse class.
     *
     * @param list            List of residents' time intervals.
     * @param myInterval      The time interval to consider.
     * @param numberOfResidents Total number of residents.
     */
    public Alehouse(ArrayList<int[]> list, int myInterval, int numberOfResidents) {
        this.residents = list;
        this.myInterval = myInterval;
        this.numberOfResidents = numberOfResidents;
        // Calculate and print the maximum number of friends
        System.out.println(checkMaximumFriendsForInterval());
    }

    /**
     * This method calculates the maximum number of friends
     * that can be present in the alehouse within the given time interval.
     *
     * @return The maximum number of friends.
     */
    private int checkMaximumFriendsForInterval() {
        // Separate the start and end times of all residents
        ArrayList<Integer> startTimes = new ArrayList<>();
        ArrayList<Integer> endTimes = new ArrayList<>();

        for (int[] resident : residents) {
            startTimes.add(resident[0]); // Add start time
            endTimes.add(resident[1]);  // Add end time
        }

        // Sort the start and end times to process them in order
        Collections.sort(startTimes);
        Collections.sort(endTimes);

        int maxFriends = 0; // Variable to store the maximum number of friends
        int j = 0;          // Pointer for the end times

        // Iterate through the sorted start times
        for (int i = 0; i < startTimes.size(); i++) {
            // Adjust the window to ensure it fits within `myInterval`
            while (startTimes.get(i) - endTimes.get(j) > myInterval) {
                j++; // Move the end pointer forward
            }

            // Calculate the number of friends in the current window
            maxFriends = Math.max(maxFriends, i - j + 1);
        }

        return maxFriends; // Return the maximum number of friends
    }
}