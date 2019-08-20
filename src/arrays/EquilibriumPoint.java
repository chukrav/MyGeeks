package arrays;

/*Given an array A of N positive numbers. The task is to find the position where
 equilibrium first occurs in the array. Equilibrium position in an array is a position
  such that the sum of elements before it is equal to the sum of elements after it.
Input:
2
1
1
5
1 3 5 2 2

Output:
1
3
-----------------
1
2
26 26
 */

import java.util.ArrayList;

import static arrays.MajorityElement.getMajorityElement;
import static arrays.testParceInput.usingBufferedReader2;

public class EquilibriumPoint {

    public static void getEquilibriumPt(Integer[] arr, int size){
        int ii = 0, jj = size - 1;
        int sumL = 0, sumR = 0;
        while(ii < jj){
            if (sumL == sumR) {
                sumL += arr[ii++];
                sumR += arr[jj--];
            } else if (sumL > sumR){
                 sumR += arr[jj--];
            } else {
                sumL += arr[ii++];
            }
            System.out.println("SL: "+sumL+", SR: "+sumR+", ii: "+ii+", jj: "+jj);
        }
        int diff = sumL - sumR;
        if (diff == 0){
            System.out.println(""+(ii+1));
        } else {
            System.out.println("-1");
        }

    }


    public static void main(String[] args) {
//        Integer[] arr = {1, 3, 5, 2, 2};
//        int size = 5;
//        Integer[] arr = {2};
//        int size = 1;


        ArrayList<Integer[]> listList = new ArrayList<>();
        listList = usingBufferedReader2(listList);
        int testNum = listList.size();
        for(int i = 1; i < testNum; i += 2){
            //getEquilibriumPt(listList.get(i+1), listList.get(i)[0]);
            GGgetEquilibriumPt(listList.get(i+1), listList.get(i)[0]);
        }
    }

    public static void GGgetEquilibriumPt(Integer[] arr, int size){
        int sum = 0;
        int sumL = arr[0];
        int eqPoint = -1;
        if (size == 1) {
            System.out.println("1");
            return;
        }

        for (int i = 1; i < size; ++i) {
            sum += arr[i];
        }

        for (int i = 1; i<size; ++i){
//            sumL += arr[i];
            int tmp = arr[i];
            sum -= tmp;
            if (sum == sumL){
                System.out.println(""+(i+1));
                return;
            } else if (sumL > sum){
                break;
            }
            sumL += tmp;
        }
        System.out.println("-1");
    }

    public static int GGgetEquilibriamPointSolut(int arr[], int n){
        int pre[] = new int[n];
        int sum1 = 0, sum2 = 0, ans = -1;
        for(int i=0; i<n; i++){
            sum1+=arr[i];
            pre[i]=sum1;
        }
        for(int i=n-1; i>=0; i--){
            sum2 += arr[i];
            if(sum2 == pre[i]) ans = i+1;
        }
        if(ans==0){
            return 0;
        }
        return ans;
    }

}
