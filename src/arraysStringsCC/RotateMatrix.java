package arraysStringsCC;

/*    1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
    bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

    How do we perform this four-way edge swap? One option is to copy the top edge to an array, and then
    move the left to the top, the bottom to the left, and so on. This requires O(N) memory, which is actually
    unnecessary.
    A better way to do this is to implement the swap index by index. In this case, we do the following:
    1 for i = 0 to n
    2 temp= top[i];
    3 top[i] = left[i]
    4 left[i] = bottom[i]
    5 bottom[i]= right[i]
    6 right[i] = temp

    This algorithm is O ( N2 ), which is the best we can do since any algorithm must touch all N2 elements.
    pp 214 */

//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class RotateMatrix {



    public static boolean rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // zero dim or not square!
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; // save top
                // left ->top
                matrix[first][i] = matrix[last - offset][first];
                // bottom ->left
                matrix[last - offset][first] = matrix[last][last - offset];
                // right ->bottom
                matrix[last][last - offset] = matrix[i][last];
                // top ->right
                matrix[i][last] = top; //  right<-saved top
            }
        }
        return true;
    }




    public static void main(String[] args) {

        int [][] A = {{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
        printMatrix(A);
        rotate(A);
        rotate(A);
        System.out.println("---------------");
        printMatrix(A);


    }

    public static void test(){
        int [][] A = {{1,1,1},{2,2,2},{3,3,3}};
        System.out.println("matrix.length: "+A.length);     // => 3
        System.out.println("matrix[0].length: "+A[0].length);   // => 3
        System.out.println("5/2: "+5/2);    // => 2
        System.out.println("last == A.length-1 :"+A[0][A.length-1]);
    }

    public static void printMatrix(int [][] A){
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(""+A[i][j]+", ");
            }
            System.out.println("");

        }
    }
}
