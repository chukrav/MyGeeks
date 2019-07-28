package arraysStringsCC;

/*
1.3 URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
        has sufficient space at the end to hold the additional characters, and that you are given the "true"
        length of the string. (Note: if implementing in Java, please use a character array so that you can
        perform this operation in place.)
        EXAMPLE
        Input: "Mr John Smith ", 13
        Output: "Mr%20John%20Smith"
        SOLUTION:
A common approach in string manipulation problems is to edit the string starting from the end and working
backwards. This is useful because we have an extra buffer at the end, which allows us to change characters
without worrying about what we're overwriting.*/


public class URLify {

    public static String replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = trueLength + spaceCount * 2;
        System.out.println("spaceCount: " + spaceCount + ", index: "+index +
                ", str.length: "+str.length);
        //char[] str = new char[index];

//        if (trueLength < str.length) str[trueLength] = '\0';
        if(index <= str.length) {
            for (i = trueLength - 1; i >= 0; i--) {
                if (str[i] == ' ') {
                    str[index - 1] = '0';
                    str[index - 2] = '2';
                    str[index - 3] = '%';
                    index = index - 3;
                } else {
                    str[index - 1] = str[i];
                    index--;
                }
            }
            return new String(str);
        } else{
            char[] bstr = new char[index];
            for (i = trueLength - 1; i >= 0; i--) {
                if (str[i] == ' ') {
                    bstr[index - 1] = '0';
                    bstr[index - 2] = '2';
                    bstr[index - 3] = '%';
                    index = index - 3;
                } else {
                    bstr[index - 1] = str[i];
                    index--;
                }
            }
            return new String(bstr);

        }

    }


    public static void main(String[] args) {
        String strInput = "Mr John Smith    "; // String in origin code mast be index length at list!!!
//        Otherwise, you can't perform operation in place!
        int trueLength = 13;
        //Output: "Mr%20John%20Smith";

        String out = replaceSpaces(strInput.toCharArray(), trueLength);
        System.out.println("Inp: " + strInput);
        System.out.println("Out: " + out);
    }
}
