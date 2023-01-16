package Exercise2022.LinkedList;

import Exercise2022.ArrayProblems.TestUtil;

public class EasyP206ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode n1 = head;
        if (head.next != null) {
            ListNode n2 = head.next;
            ListNode suffix = reverseList(head.next);
            n2.next = n1;
            n1.next = null;
            return suffix;
        } else {
            n1.next = null;
            return n1;
        }
    }

    public static void main(String[] args) {
        EasyP206ReverseList p = new EasyP206ReverseList();
        TestUtil.equals(ListNode.getList(new int[] {5,4,3,2,1}),
                p.reverseList(ListNode.getList(new int[]{1,2,3,4,5})));
    }
}
