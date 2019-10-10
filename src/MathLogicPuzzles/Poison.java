package MathLogicPuzzles;

/* 6. 1 O Poison: You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which
can be used to detect poison. A single drop of poison will turn the test strip positive permanently.
You can put any number of drops on a test strip at once and you can reuse a test strip as many times
as you'd like (as long as the results are negative). However, you can only run tests once per day and
it takes seven days to return a result. How would you figure out the poisoned bottle in as few days
as possible?

Optimized Approach (10 days)
As noted in the beginning of the solution, it might be more optimal to run multiple tests at once.
If we divide the bottles up into 10 groups (with bottles O - 99 going to strip 0, bottles 100 - 199 going to strip
1, bottles 200 - 299 going to strip 2, and so on), then day 7 will reveal the first digit of the bottle number. A
positive result on strip i at day 7 shows that the first digit (1OO's digit) of the bottle number is i.
Dividing the bottles in a different way can reveal the second or third digit. We just need to run these tests
on different days so that we don't confuse the results.
        day-7 day-8 day-9 day-10
Strip-0 Oxx   xOx   xxO   xx9
Strip-1 lxx   xlx   xxl   xxO
Strip-2 2xx   x2x   xx2   xxl
Strip-3 3xx   x3x   xx3   xx2
Strip-4 4xx   x4x   xx4   xx3
Strip-5 Sxx   xSx   xxS   xx4
Strip-6 6xx   x6x   xx6   xxS
Strip-7 7xx   xlx   xx7   xx6
Strip-8 8xx   x8x   xx8   xx7
Strip-9 9xx   x9x   xx9   xx8

       pp. 309 */

import java.util.ArrayList;
import java.util.HashSet;

public class Poison {

    class Bottle {
        private boolean poisoned = false;
        private int id;

        public Bottle(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setAsPoisoned() {
            poisoned = true;
        }

        public boolean isPoisoned() {
            return poisoned;
        }
    }

    static class TestStrip {
        public static int DAYS_FOR_RESULT = 7;
        private ArrayList<ArrayList<Bottle>> dropsByDay = new ArrayList<ArrayList<Bottle>>();
        private int id;

        public TestStrip(int id) {
            this.id = id;
        }

        public int getid() {
            return id;
        }

        /* Resize list of days/drops to be large enough. */
        private void sizeDropsForDay(int day) {
            while (dropsByDay.size() <= day) {
                dropsByDay.add(new ArrayList<Bottle>());
            }
        }

        /* Add drop from bottle on specific day. */
        public void addDropOnDay(int day, Bottle bottle) {
            sizeDropsForDay(day);
            ArrayList<Bottle> drops = dropsByDay.get(day);
            drops.add(bottle);
        }

        /* Checks if any of the bottles in the set are poisoned. */
        private boolean hasPoison(ArrayList<Bottle> bottles) {
            for (Bottle b : bottles) {
                if (b.isPoisoned()) {
                    return true;
                }
            }
            return false;
        }

        /* Gets bottles used in the test DAYS_FOR_RESULT days ago. */
        public ArrayList<Bottle> getlastWeeksBottles(int day) {
            if (day < DAYS_FOR_RESULT) {
                return null;
            }
            return dropsByDay.get(day - DAYS_FOR_RESULT);
        }

        /* Checks for poisoned bottles since before DAYS_FOR_RESULT */
        public boolean isPositiveOnDay(int day) {
            int testDay = day - DAYS_FOR_RESULT;
            if (testDay < 0 || testDay >= dropsByDay.size()) {
                return false;
            }
            for (int d = 0; d <= testDay; d++) {
                ArrayList<Bottle> bottles = dropsByDay.get(d);
                if (hasPoison(bottles)) {
                    return true;
                }
            }
            return false;
        }
    }

    int findPoisonedBottle(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips) {
        if (bottles.size() > 1000 || strips.size() < 10) return -1;

        int tests = 4; // three digits, plus one extra
        int nTestStrips = strips.size();

        /* Run tests. */
        for (int day = 0; day < tests; day++) {
            runTestSet(bottles, strips, day);
        }

        /* Get results. */
        HashSet<Integer> previousResults = new HashSet<Integer>();
        int[] digits = new int[tests];
        for (int day = 0; day < tests; day++) {
            int resultDay = day + TestStrip.DAYS_FOR_RESULT;
            digits[day] = getPositiveOnDay(strips, resultDay, previousResults);
            previousResults.add(digits[day]);
        }

        /* If day l 's results matched day 0' s, update the digit. */
        if (digits[1] == -1) {
            digits[1] = digits[0];
        }

        /* If day 2 matched day 0 or day 1, check day 3. Day 3 is the s ame as day 2, but
         * incremented by 1. */
        if (digits[2] == -1) {
            if (digits[3] == -1) {/* Day 3 didn't give new result*/
                /* Digit 2 equals digit 0 or digit 1. But, digit 2, when incremented also
                 * matches digit 0 or digit 1. This means that digit 0 incremented matches
                 * digit 1, or the other way around.*/
                digits[2] = ((digits[0] + 1) % nTestStrips) == digits[1] ?
                        digits[0] : digits[1];
            } else {
                digits[2] = (digits[3] - 1 + nTestStrips) % nTestStrips;
            }
        }

        return digits[0] * 100 + digits[1] * 10 + digits[2];
    }

    /* Run set of tests for this day.*/
    void runTestSet(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips, int day) {
        if (day > 3) return;// only works for 3 days (digits)+one extra

        for (Bottle bottle : bottles) {
            int index = getTestStripindexForDay(bottle, day, strips.size());
            TestStrip testStrip = strips.get(index);
            testStrip.addDropOnDay(day, bottle);
        }
    }

    /* Get strip that should be used on this bottle on this day.*/
    int getTestStripindexForDay(Bottle bottle, int day, int nTestStrips) {
        int id = bottle.getId();
        switch (day) {
            case 0:
                return id / 100;
            case 1:
                return (id % 100) / 10;
            case 2:
                return id % 10;
            case 3:
                return (id % 10 + 1) % nTestStrips;
            default:
                return -1;
        }
    }

    /* Get results that are positive for a particular day, excluding prior results.*/
    int getPositiveOnDay(ArrayList<TestStrip> testStrips, int day,
                         HashSet<Integer> previousResults) {
        for (TestStrip testStrip : testStrips) {
            int id = testStrip.getid();
            if (testStrip.isPositiveOnDay(day) && !previousResults.contains(id)) {
                return testStrip.getid();
            }
        }
        return -1;
    }


}
