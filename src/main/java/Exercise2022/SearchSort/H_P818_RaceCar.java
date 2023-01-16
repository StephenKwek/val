package Exercise2022.SearchSort;

/**
 * 818. Race Car
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 */
public class H_P818_RaceCar {
    public int racecar(int target) {
        int speed = 1;
        int steps = 0;
        while (target != 0) {
            while (target > 0) {
                target -= speed;
                speed *= 2;
                steps++;
            }
            if (target != 0) {
                target = -1 * target;
                speed = 1;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        H_P818_RaceCar p = new H_P818_RaceCar();
        System.out.println(p.racecar(3));
        assert 2 == p.racecar(3);
        assert 5 == p.racecar(6);
    }
}
