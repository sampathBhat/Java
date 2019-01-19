package sam.stringManipulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockValidString {

    // Complete the isValid function below.
    static String isValid(String s) {
    char[] al = s.toCharArray();
    int[] dict = new int[26];
    boolean flag=true;
    for(int i=0;i<al.length; i++) {
        ++dict[al[i]-'a'];
    }
    Arrays.parallelSort(dict);
    
    int i=dict.length-1;
    while(dict[i]!=0 && i>1){
      if(dict[i]!=dict[i-1]){
          if(dict[i]-dict[i-1]>1 && i>2 && dict[i-2]!=0){flag=false;break;}
          if(i<dict.length-1 && i>2 && dict[i-2]!=0){
          flag=false;
          break;
          }
          i--;
          while(dict[i]==dict[i-1] && dict[i]!=0){
              i--;
          }
          if(dict[i-1]==0)break;
          flag=false;
          break;
      }
      i--;
    }

    if(flag)return "YES"; else return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
