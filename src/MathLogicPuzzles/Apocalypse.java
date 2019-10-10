package MathLogicPuzzles;

import java.util.Random;

/*We know that every family has exactly one girl. How many boys does each family have, on average? To
compute this, we can look at the expected value of the number of boys.
Sure enough, if you run this on large values of n, you should get something very close to 0.5
  pp 306 */

public class Apocalypse {

    double runNFamilies(int n) {
        int boys = 0;
        int girls = 0;
        for (int i = 0; i < n; i++) {
            int[] genders = runOneFamily();
            girls += genders[0];
            boys += genders[1];
        }
        return girls / (double) (boys + girls);
    }

    int[] runOneFamily() {
        Random random = new Random();
        int boys = 0;
        int girls = 0;
        while (girls == 0) { // until we have a girl
            if (random.nextBoolean()) { // girl
                girls += 1;
            } else { // boy
                boys += 1;
            }
        }
        int[] genders = {girls, boys};
        return genders;
    }


}
