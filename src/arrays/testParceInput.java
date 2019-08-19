package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
int[] data = {1,2,3,4,5,6,7,8,9,10};

// To boxed array
        Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
        Integer[] ever = IntStream.of( data ).boxed().toArray( Integer[]::new );

// To boxed list
        List<Integer> you  = Arrays.stream( data ).boxed().collect( Collectors.toList() );
        List<Integer> like = IntStream.of( data ).boxed().collect( Collectors.toList() );
2
5 12
1 2 3 7 5
10 15
1 2 3 4 5 6 7 8 9 10

1
74 665
142 112 54 69 148 45 63 158 38 60 124 142 130 179 117 36 191 43 89 107 41 143 65 49 47 6 91 130 171 151 7 102 194 149 30 24 85 155 157 41 167 177 132 109 145 40 27 124 138 139 119 83 130 142 34 116 40 59 105 131 178 107 74 187 22 146 125 73 71 30 178 174 98 113
*/
public class testParceInput {

    public static void main(String[] args) {
      /*  List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8,9);

        for (int i:list) {
            System.out.println("--: "+i);
        } */
        //ArrayList<List<Integer>> listList = new ArrayList<>();
//        listList = usingBufferedReader(listList);
//
// listList.add(Arrays.asList(2));
//        listList.add(Arrays.asList(5, 12));
//        listList.add(Arrays.asList(1, 2, 3, 7, 5));
//        listList.add(Arrays.asList(10, 15));
//        listList.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer[]> listList = new ArrayList<>();
        listList = usingBufferedReader2(listList);
        printInputInt(listList);
        Integer [] ar = listList.get(2);

        int n = listList.get(0)[0];

    }

    private static ArrayList<List<Integer>> usingBufferedReader(ArrayList<List<Integer>> listList){
        //listList = new ArrayList<>();
        //System.out.println("Name: ");
        try{
            BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
            int n;
            n = Integer.parseInt(s.readLine());//  .nextInt();
            List<Integer> list = Arrays.asList(n);
//            list.get(0);
            listList.add(list);
            while (n-- > 0){
                int LEN = 2;  // Read 2 lines: 1) Parameters; 2) Data
                for (int i = 0; i < LEN; ++i) {
                    String input = s.readLine();
                    int[] ar = Arrays.stream(input.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
//
                    //List<Integer>: int[] to List<Integer>
                    list = Arrays.stream(ar).boxed().collect(Collectors.toList());
                    listList.add(list);
                }
            }
//            return listList;
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return listList;
    }

    public static void printInput(ArrayList<List<Integer>> listList){
        int LEN = listList.size();
        System.out.println("\nList size: "+LEN);
        for (int i = 0; i < LEN; ++i){
            List<Integer> list = listList.get(i);
            for (int j:list) {
                System.out.print(""+j+" ");
            }
            System.out.println("");
        }
    }

    public static void printInputInt(ArrayList<Integer[]> listList){
        int LEN = listList.size();
        System.out.println("\nList size: "+LEN);
        for (int i = 0; i < LEN; ++i){
            Integer[] list = listList.get(i);
            for (int j:list) {
                System.out.print(""+j+" ");
            }
            System.out.println("");
        }
    }

    public static ArrayList<Integer[]> usingBufferedReader2(ArrayList<Integer[]> listList){
        try{
            BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
            Integer[] n = {Integer.parseInt(s.readLine())};//  .nextInt();
            listList.add(n);
            int num = n[0];
            while (num-- > 0){
                int LEN = 2;  // Read 2 lines: 1) Parameters; 2) Data
                for (int i = 0; i < LEN; ++i) {
                    String input = s.readLine();
                    int[] ar = Arrays.stream(input.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
//
                    //List<Integer>: int[] to List<Integer>
                    //Integer[] what = Arrays.stream( data ).boxed().toArray( Integer[]::new );
                    Integer[] list = Arrays.stream(ar).boxed().toArray( Integer[]::new );
                    listList.add(list);
                }
            }

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return listList;
    }

}
