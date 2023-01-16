package Exercise2022.LinkedList;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode getList(int[] vals) {
        assert vals.length > 0;
        ListNode root = new ListNode(vals[0]);
        ListNode pre = root;
        for (int i = 1; i < vals.length; i++) {
            ListNode cur = new ListNode(vals[i]);
            pre.next = cur;
            pre = cur;
        }
        return root;
    }

    public ListNode getNext() {
        return next;
    }

    public int getVal() {
        return val;
    }
}
