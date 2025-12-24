
import java.util.*;

public class CalcIsShortForCalculator {
    ArrayList<String> seq;
    int numberOfSeq;

    public CalcIsShortForCalculator(ArrayList<String> s, int seqLength) {
       this.seq = s;
       this.numberOfSeq = seqLength;

       for (int i = 0; i < numberOfSeq; i++) {
           testInOrder(i);
       }

    }

    public void testInOrder(int index) {
        String rootVal = "";
        String test = seq.get(index);
        ArrayList<String>[] levels = new ArrayList[8];

        for (int i = 0; i < 8; i++) {
            levels[i] = new ArrayList<>();
        }

//        System.out.println(test);
        ArrayList<String> newSeq = new ArrayList<>();
        newSeq.addAll(Arrays.asList(test.split(" ")) );

        ArrayList<String> newSeqWithoutPar = new ArrayList<>();


        for (String temp : newSeq) {
            String newTemp1 = temp.substring(0, 0) + temp.substring(1);
            String newTemp2 = newTemp1.substring(0, newTemp1.length() - 1);
            newSeqWithoutPar.add(newTemp2);
        }

        for (String temp : newSeqWithoutPar) {
            String pos;
            String value;
            try {
                pos = temp.split(",")[1];
                value =(temp.split(",")[0]);
                
                
            } catch (Exception e) {
                // Find root

                value = temp.substring(0, temp.length() - 1);
                pos = "";
            }

            if (pos.isEmpty()) {
                rootVal = temp.substring(0, temp.length() - 1);
            } else {
                int level = pos.length() - 1;

                pos = pos.replace('L', '1');
                pos = pos.replace('R', '2');
                String s = value + "," + (pos);
//                if (pos.length() > 1) {
//                    System.out.println(findPosToCompare(s));
//                }

                levels[level].add(s);
            }
        }

//        System.out.println(rootVal);
        for (int i = 0; i < levels.length; i++) {
            levels[i].sort((a, b) -> {
                int numA = Integer.parseInt(a.split(",")[1]);
                int numB = Integer.parseInt(b.split(",")[1]);
                return Integer.compare(numA, numB);
            });
//            System.out.println(levels[i]);
        }



        if (rootVal.equals("")) {
            System.out.println("incomplete");
            return;
        }
        if (!levels[0].isEmpty()) {
            for (int i = 1; i < levels.length; i++) {

                for (int j = 0; j < levels[i].size(); j++) {
                    ArrayList<String> prevLevel = levels[i-1];
                    String curPos = getPos(levels[i].get(j));
                    String curPosToCompare= findPosToCompare(levels[i].get(j));

                    boolean foundMatch = false;
                    for(String prev : prevLevel) {

                        if (getPos(prev).equals(curPosToCompare)) {
                            foundMatch = true;
                            break;
                        }
                    }
                    if (!foundMatch) {
                        System.out.println("incomplete");
                        return;
                    }


                }
            }
        }

        StringBuilder out = new StringBuilder(rootVal);
//        System.out.println(out);
        for (ArrayList<String> level : levels) {
            for (String s : level) {
                out.append(" ").append(getNodeValue(s));
            }
        }
        System.out.println(out);


    }

    private String findPosToCompare(String s) {
        String pos = s.split(",")[1];
        pos = pos.substring(0, pos.length() - 1);
        return pos;
    }

    private String getPos(String s) {
        return s.split(",")[1];
    }

    private String getNodeValue (String s) {
        return s.split(",")[0];
    }

}
