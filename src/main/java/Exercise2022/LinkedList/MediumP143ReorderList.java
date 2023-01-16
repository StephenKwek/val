package Exercise2022.LinkedList;

import Exercise2022.ArrayProblems.TestUtil;
import org.apache.commons.lang3.tuple.Pair;

public class MediumP143ReorderList {
    private ListNode[] partition(ListNode head) {
        ListNode slow = null;
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow == null ? head : slow.next;
                fast = fast.next;
            }
        }
        ListNode p2 = slow.next;
        slow.next = null;
        return new ListNode[]{head, p2};
    }
    public void reorderList(ListNode head) {
        EasyP206ReverseList easyP206ReverseList = new EasyP206ReverseList();
        ListNode[] halves = partition(head);
        halves[1] = easyP206ReverseList.reverseList(halves[1]);
        ListNode root = halves[0];
        ListNode cur1 = halves[0];
        ListNode cur2 = halves[1];
        while (halves[0] != null && halves[1] != null) {
            halves[0] = halves[0].next;
            halves[1] = halves[1].next;
            cur1.next = cur2;
            cur1 = halves[0];
            if (halves[0] != null) {
                cur2.next = halves[0];
            }
            cur2 = halves[1];
        }

        head = root;
    }

    public static void main(String[] args) {
        MediumP143ReorderList p = new MediumP143ReorderList();
        ListNode expected = ListNode.getList(new int[] {1,4,2,3});
        ListNode head = ListNode.getList(new int[] {1,2,3,4});
        p.reorderList(head);
        TestUtil.equals(expected, head);

        head = ListNode.getList(new int[] {1,2,3,4,5});
        expected = ListNode.getList(new int[] {1,5,2,4,3});
        p.reorderList(head);
        TestUtil.equals(expected, head);
    }
}