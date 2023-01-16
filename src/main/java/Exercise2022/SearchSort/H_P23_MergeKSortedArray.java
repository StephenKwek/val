package Exercise2022.SearchSort;

import Exercise2022.LinkedList.ListNode;

import java.util.Arrays;
import java.util.List;

public class H_P23_MergeKSortedArray {
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        ListNode l = l1 == null ? l2 : l1;
        while (l != null) {
            cur.next = l;
            cur = cur.next;
            l = l.next;
        }
        return head;
    }

    public ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (right - left == 0) {
           return null;
        } else if (right - left == 1) {
            return lists[left];
        } else {
            int half = (right - left) / 2;
            ListNode leftHalf = mergeKLists(lists, left, left + half);
            ListNode rightHalf = mergeKLists(lists, left + half, right);
            return merge2Lists(leftHalf, rightHalf);
        }
    }

    public static void main(String[] args) {
        H_P23_MergeKSortedArray p = new H_P23_MergeKSortedArray();
        System.out.println(p.mergeKLists(new ListNode[0], 0, 0));

        ListNode[] lists = new ListNode[3];
        lists[0] = ListNode.getList(new int[] {1,4,5});
        lists[1] = ListNode.getList(new int[] {1,3,4});
        lists[2] = ListNode.getList(new int[] {2,6});
        ListNode soln = p.mergeKLists(lists, 0, lists.length);
        while (soln != null) {
            System.out.println(soln.val);
            soln = soln.next;
        }
    }
}
