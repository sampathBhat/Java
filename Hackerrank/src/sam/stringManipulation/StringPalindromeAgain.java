package sam.stringManipulation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class StringPalindromeAgain {



    // Complete the substrCount function below.
    static long substrCount(int n, String s){
List<Letter> let = getListOfLetter(s);
        long res=0;
        for(int i=0;i<let.size();i++){
            int repeat = let.get(i).repeat;
            res+= repeat * (repeat+1)/2;
            
            if(i>0 && i<let.size()-1){
                if(let.get(i-1).ch == let.get(i+1).ch && let.get(i).repeat==1){
                    res+=Math.min(let.get(i-1).repeat, let.get(i+1).repeat);
                }
            }
        }

        
        return res;

    }
    
    static List<Letter> getListOfLetter(String s){
        List<Letter> letterList = new ArrayList<Letter>();
        char[] cArray= s.toCharArray();
        char letter = cArray[0];
        int length=1;
        for(int i=1;i<cArray.length;i++){
            if(cArray[i] == letter){
                length++;
            }else{
                letterList.add(new Letter(letter, length));
                length=1;
            }
                letter=cArray[i];
        }
        letterList.add(new Letter(letter,length));
        return letterList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	/*BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\sampa\\Desktop\\new1.txt"));
    	String s= br.readLine();
    	System.out.println(s);
        long result = substrCount(727310, s);
        System.out.println(result);*/
    	int[] numbers = {1,2,3,5,6};
    	HashSet<Integer> in = new HashSet<Integer>();
        int res=0;
        for(int i=0;i<numbers.length;i++){
        	System.out.println("in for "+i);
            if(in.contains(i)){
            	System.out.println("in if ");
                res++;
            }
            
                in.add(i);
            
        }
        System.out.println(Arrays.toString(in.toArray()));
        System.out.println("res"+res);
    }
    
}
class Letter{
    char ch;
    int repeat;
    Letter(char ch, int repeat){
        this.ch=ch;
        this.repeat=repeat;
    }
    
}
