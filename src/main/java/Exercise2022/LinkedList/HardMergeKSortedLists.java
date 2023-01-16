package Exercise2022.LinkedList;


import Exercise2022.ArrayProblems.TestUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HardMergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        ListNode cur = null;
        PriorityQueue<Pair<Integer,Integer>> q = new PriorityQueue<>();
        ListNode[] pointers = Arrays.copyOf(lists, lists.length);
        for (int i = 0; i < lists.length; i++) {
            if (pointers[i] != null) {
                q.add(ImmutablePair.of(pointers[i].val, i));
                pointers[i] = pointers[i].next;
            }
        }
        while(!q.isEmpty()) {
            Pair<Integer, Integer> minItemPair = q.poll();
            if (head == null) {
                head = new ListNode(minItemPair.getLeft());
                cur = head;
            } else {
                cur.next = new ListNode(minItemPair.getLeft());
                cur = cur.next;
            }
            ListNode newHead = pointers[minItemPair.getRight()];
            if (newHead != null) {
                pointers[minItemPair.getRight()] = newHead.next;
                q.add(ImmutablePair.of(newHead.val, minItemPair.getRight()));
            }
        }
        return head;
    }

    public static void main(String[] args) {
        HardMergeKSortedLists p = new HardMergeKSortedLists();
        ListNode[] input = new ListNode[] {
                ListNode.getList(new int[]{1,4,5}),
                ListNode.getList(new int[]{1,3,4}),
                ListNode.getList(new int[]{2,6}),
        };
        ListNode expected = ListNode.getList(new int[]{1,1,2,3,4,4,5,6});
        TestUtil.equals(expected, p.mergeKLists(input));
    }
}
