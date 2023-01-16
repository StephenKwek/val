package Exercise2022.LinkedList;

import Exercise2022.ArrayProblems.TestUtil;

public class MediumP19RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head;
        ListNode back = null;
        while (front != null) {
            front = front.next;
            n--;
            if (front != null && n <= 0) {
                back = back == null ? head : back.next;
            }
        }
        if (back != null && back.next != null) {
            back.next = back.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        MediumP19RemoveNthNodeFromEndOfList p = new MediumP19RemoveNthNodeFromEndOfList();
        ListNode expected = ListNode.getList(new int[] {1,2,3,5});
        ListNode input = ListNode.getList(new int[] {1,2,3,4,5});
        TestUtil.equals(expected, p.removeNthFromEnd(input, 2));
        expected = ListNode.getList(new int[]{1});
        input = ListNode.getList(new int[]{1,2});
        TestUtil.equals(expected, p.removeNthFromEnd(input, 1));
    }
}
