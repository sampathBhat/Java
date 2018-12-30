package leetcode.tree;

public class SameTree {
	public static void main(String[] args) {
		
	}
public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)return true;
        if((p==null && q!=null ) || (q==null && p!=null ))return false;
        if(p.x!=q.x)return true;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
      
    }

}

class TreeNode{
	int x;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x){
		this.x=x;
		this.left=null;
		this.right=null;
	}
	
}
