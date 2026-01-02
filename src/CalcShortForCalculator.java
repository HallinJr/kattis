public class CalcShortForCalculator {

    private ReadInput input;
    private int deal1Price;
    private int deal2Price;
    private int hotDogPrice;
    private int sodaPrice;
    private int originalDogCount;
    private int originalSodaCount;
    private int dogToSodaRatio;
    private int curDogCount;
    private int curSodaCount;
    private int curBalance;

    public CalcShortForCalculator(ReadInput readInput) {
        this.input = readInput;
        this.originalDogCount = input.getNumberOfHotDogs();
        this.originalSodaCount = input.getNumberOfSodas();
        this.curDogCount = 0;
        this.curSodaCount = 0;
        this.deal1Price = 499;
        this.deal2Price = 549;
        this.hotDogPrice = 299;
        this.sodaPrice = 249;
        calc();
    }



    public void calc() {


            if (originalDogCount == 0) {
                System.out.println(originalSodaCount * sodaPrice);
                return;
            }

            if (isEven(originalDogCount)) {
                deal2(originalDogCount / 2);
                if (originalSodaCount - curSodaCount > 0) {
                    curBalance += (originalSodaCount - curSodaCount) * sodaPrice ;
                }
            } else {
                if (originalDogCount > 2) {
                    int howManyDeals = (originalDogCount - 1) / 2;
                    deal2(howManyDeals);

                    if (originalSodaCount > curSodaCount) {
                        deal1(1);
                    } else {
                        curBalance += hotDogPrice;
                    }

                    if (originalSodaCount > curSodaCount) {
                        curBalance += (originalSodaCount - curSodaCount) * sodaPrice;
                    }


                } else {
                    if (originalSodaCount > 0) {
                        deal1(1);
                        curBalance += (originalSodaCount - 1) * sodaPrice;
                    } else {
                        curBalance += hotDogPrice;
                    }
                }
            }
        System.out.println(curBalance);
        }


    private void deal1(int x) {
        curDogCount += x;
        curSodaCount += x;
        curBalance += x * deal1Price;
    }

    private void deal2 (int x) {
        int adjustDogCount = x * 2;
        int adjustSodaCount = x;
        curDogCount += adjustDogCount;
        curSodaCount += adjustSodaCount;
        curBalance += x * deal2Price;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}
