package Exercise2022.Interval;

public class Interval {
    public int start;
    public int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean leftOf(Interval other) {
        return this.end < other.start;
    }

    public boolean rightOf(Interval other) {
        return other.end < this.start;
    }

    public boolean encloseBy(Interval other) {
        return this.start < other.start && other.end < this.end;
    }

    public boolean enclose(Interval other) {
        return other.start < this.start && this.end < other.end;
    }

    public boolean overlap(Interval other) {
        return !leftOf(other) && !rightOf(other);
    }

}
