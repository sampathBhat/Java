package leetcode.hard;

public class MergeList {
	
	   public ListNode mergeKLists(ListNode[] lists) {
			return split(lists,0,lists.length-1);
	        
	    }
		
		public static ListNode split(ListNode[] lists, int start, int end) {
			if(start == end) return lists[start];
			else if(start<end) {
				int mid = start + (end-start)/2;
				ListNode l1 = split(lists, start, mid);
				ListNode l2 = split(lists, mid+1, end);
				return merge(l1,l2);
			}
	        else return null;
		}
		
		public static ListNode merge(ListNode l1, ListNode l2) {
			if(l1==null)return l2;
			if(l2==null)return l1;
			if(l1.val<l2.val) {
				l1.next=merge(l1.next, l2);
				return l1;
			}
			else {
				l2.next=merge(l2.next, l1);
				return l2;
			}
	    }


}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}