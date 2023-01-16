package Exercise2022.DynamicProgramming;

public class E_P70_ClimbingStairs {
    public int climbStairs(int n) {
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;
        if (n > 1) {
            for (int i = 2; i <= n; i++) {
                steps[i] = steps[i - 1] + steps[i - 2];
            }
        }
        return steps[n];
    }

    public static void main(String[] args) {
        E_P70_ClimbingStairs p = new E_P70_ClimbingStairs();
        assert 2 == p.climbStairs(2);
        assert 3 == p.climbStairs(3);
    }
}
