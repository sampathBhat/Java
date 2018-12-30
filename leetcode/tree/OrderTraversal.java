package leetcode.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class OrderTraversal {

	public static void main(String[] args) {
		
		Tree tree= new Tree(1);
		tree.left= new Tree(2);
		tree.right = new Tree(3);
		tree.left.left=new Tree(4);
		tree.left.right = new Tree(5);
		tree.right.left = new Tree(6);
		tree.right.right= new Tree(7);
		
		inOrder(tree);
		System.out.println();
		preOrder(tree);
		System.out.println();
		postOrder(tree);
		HashMap<String, Integer> username = new HashMap<String,Integer>();
		
		
		

	}
	
	
	/*
	 *                 1
	 *             2       3
	 *           4   5   6   7
	 *           
	 *           o/p =  4 -> 2 -> 5 -> 1 -> 6 -> 3 -> 7
	 * */
	private static void inOrder(Tree x) {       // left -> root -> right
		if(x == null)return;                    
		inOrder(x.left);
		System.out.print(" -> "+x.value);
		inOrder(x.right);
	}

	/*
	 *                 1
	 *             2       3
	 *           4   5   6   7
	 *           
	 *           o/p =  1 -> 2 -> 4 -> 5 -> 3 -> 6 -> 7
	 * */
	
	private static void preOrder(Tree x) {

		if(x == null)return;
		System.out.print(" -> "+x.value);
		preOrder(x.left);
		preOrder(x.right);

	}

	/*
	 *                 1
	 *             2       3
	 *           4   5   6   7
	 *           
	 *           o/p =  4 -> 5 -> 2 -> 6 -> 7 -> 3 -> 1
	 * */
	
	private static void postOrder(Tree x) {

		if(x == null)return;
		postOrder(x.left);
		postOrder(x.right);
		System.out.print(" -> "+x.value);

	}
	
	public int GetMostVisited(int n, List<Integer> sprints)
    {
        int[] sprInc = new int[n + 2];
        for (int i = 0; i < sprints.size() - 1; i++)
        {
            int start = Math.min(sprints.get(i), sprints.get(i + 1));
            int end = Math.max(sprints.get(i), sprints.get(i + 1));
            sprInc[start]++;
            sprInc[end + 1]--;
        }

        int[] scores = new int[n + 1];
        int score = 0;
        for (int i = 1; i < n + 1; i++)
        {
            score += sprInc[i];
            scores[i] = score;
        }

        int key=0;
        		int value =0;

        for (int i = 1; i < n + 1; i++)
        {
            if (scores[i] > value)
            {
                key=i;
                value=scores[i];
            }
        }
        MyComparator mc = new MyComparator();
        PriorityQueue<Bid> pq= new PriorityQueue<>(mc);
        int[] q= new int[10];
        LinkedList<Integer> target = new LinkedList<>(Arrays.stream(q).sorted().boxed().collect(Collectors.toList()));
        return key;
}
	


}

class MyComparator implements Comparator<Bid>{

	@Override
	public int compare(Bid l1, Bid l2) {
					if(l1.bidPrice != l2.bidPrice)
					return l1.bidPrice-l2.bidPrice;
					else
						return l2.timeStamp-l1.timeStamp;
	}
	
}

class Bid{
    int userID;
    int numOfShareReq;
    int bidPrice;
    int timeStamp;
    public Bid(List<Integer> a){
        this.userID=a.get(0);
        this.numOfShareReq=a.get(1);
        this.bidPrice=a.get(2);
        this.timeStamp=a.get(3);
    }
    
}

class Tree{
	
	int value;
	Tree left = null, right= null;
	
	Tree(int x){
		this.value=x;
		
	}
}






class Result {

    /*
     * Complete the 'getMostVisited' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY sprints
     */

    public static int getMostVisited(int n, List<Integer> sprints) {
    // Write your code here
        
        int[] sprInc = new int[n + 2];
        for (int i = 0; i < sprints.size() - 1; i++)
        {
            int start = Math.min(sprints.get(i), sprints.get(i + 1));
            int end = Math.max(sprints.get(i), sprints.get(i + 1));
            sprInc[start]++;
            sprInc[end + 1]--;
        }

        int[] scores = new int[n + 1];
        int score = 0;
        for (int i = 1; i < n + 1; i++)
        {
            score += sprInc[i];
            scores[i] = score;
        }

        int key=0;
                int value =0;

        for (int i = 1; i < n + 1; i++)
        {
            if (scores[i] > value)
            {
                key=i;
                value=scores[i];
            }
        }
        return key;

        
        

    }

}


