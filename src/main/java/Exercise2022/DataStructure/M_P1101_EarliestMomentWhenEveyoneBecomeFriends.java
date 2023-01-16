package Exercise2022.DataStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1101. The Earliest Moment When Everyone Become Friends
 * There are n people in a social group labeled from 0 to n - 1. You are given an array logs
 * where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
 *
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a.
 * Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
 *
 * Return the earliest time for which every person became acquainted with every other person.
 * If there is no such earliest time, return -1.
 */
public class M_P1101_EarliestMomentWhenEveyoneBecomeFriends {
    Set<Integer> roots = new HashSet<>();
    Map<Integer, Integer> parent = new HashMap<>();

    int findHead(int item) {
        while (parent.containsKey(item) && parent.get(item) != null) {
            item = parent.get(item);
        }
        return item;
    }
    public int earliestAcq(int[][] logs, int n) {
        roots.clear();
        parent.clear();
        int i = 0;
        for (; i < logs.length && (parent.size() != n || roots.size() != 1); i++) {
            int xHead = findHead(logs[i][1]);
            int yHead = findHead(logs[i][2]);
            if (xHead < yHead) {
                roots.remove(xHead);
                parent.put(xHead, yHead);
                if (!parent.containsKey(yHead)) {
                    parent.put(yHead, null);
                    roots.add(yHead);
                }
            } else if (xHead > yHead) {
                roots.remove(yHead);
                parent.put(yHead, xHead);
                if (!parent.containsKey(xHead)) {
                    parent.put(xHead, null);
                    roots.add(xHead);
                }
            }
        }
        return (parent.size() == n && roots.size() == 1)
                ? logs[i-1][0]
                : -1;
    }

    public static void main(String[] args) {
        M_P1101_EarliestMomentWhenEveyoneBecomeFriends p = new M_P1101_EarliestMomentWhenEveyoneBecomeFriends();
        int[][] logs = {{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},
                {20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}};

        assert 20190301 == p.earliestAcq(logs, 6);
        logs = new int[][] {{0,2,0},{1,0,1},{3,0,3},{4,1,2},{7,3,1}};
        assert 3 == p.earliestAcq(logs, 4);
    }
}
