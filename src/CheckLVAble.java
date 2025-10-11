public class CheckLVAble {

    int inputLength;
    String inputString;
    int index = 0;

    public CheckLVAble(int length, String inString) {
        this.inputLength = length;
        this.inputString = inString;

        checkStringForLV(inputString);

    }


    public void checkStringForLV (String testString) {

        if (recursiveStringCheckWithNoOperations(testString)) {
            System.out.println(0);
        } else {
            recursiveStringCheckWithOneOperations(testString);
        }
    }

    private Boolean recursiveStringCheckWithNoOperations(String inputString) {

        if (index == inputLength - 1) {
            index = 0;
            return false;
        }

        if (inputString.charAt(index) == 'l' && inputString.charAt(index + 1) == 'v')
        {
            index = 0;
            return true;
        } else {
            index++;
            return recursiveStringCheckWithNoOperations(inputString);
        }

    }

    private void recursiveStringCheckWithOneOperations(String inputString) {
        if (index == inputLength -1 || (inputString.charAt(index) == 'v' && index == 0)) {
            System.out.println(2);
            index = 0;
        } else if (inputString.charAt(index) == 'l' || inputString.charAt(index) == 'v') {
            System.out.println(1);
            index = 0;
        } else {
            index++;
            recursiveStringCheckWithOneOperations(inputString);
        }
    }
}
