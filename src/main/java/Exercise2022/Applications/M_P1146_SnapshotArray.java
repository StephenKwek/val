package Exercise2022.Applications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_P1146_SnapshotArray {
    static class SnapshotArray {
        private List<Map<Integer, Integer>> version = new ArrayList<>();
        private int currentVersion = 0;
        private int[] data;

        public SnapshotArray(int length) {
            data = new int[length];
            version.add(new HashMap<>());
        }

        public void set(int index, int val) {
            version.get(currentVersion).put(index, val);
        }

        public int snap() {
            version.add(new HashMap<>(version.get(currentVersion++)));
            return currentVersion - 1;
        }

        public int get(int index, int snap_id) {
            return version.get(snap_id).getOrDefault(index, data[index]);
        }
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(0, 5);
        assert 0 == snapshotArr.snap();
        snapshotArr.set(0,6);
        assert 5 == snapshotArr.get(0,0);
    }
}
