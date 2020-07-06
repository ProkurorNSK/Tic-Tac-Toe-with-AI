import java.util.*;

class Main {

    public static final String[] a = new String[40];
    public static final List<String> result = new ArrayList<>();

    public static void decomposition(int n, int k, int i) {
        if (n < 0) return;
        if (n == 0) {
            result.add(Arrays.stream(a).limit(i).reduce((x, y) -> x + " " + y).orElse(""));
        } else {
            if (n - k >= 0) {
                a[i] = String.valueOf(k);
                decomposition(n - k, k, i + 1);
            }
            if (k - 1 > 0) {
                decomposition(n, k - 1, i);
            }
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = Integer.parseInt(scanner.nextLine());
        decomposition(m, m, 0);
        Collections.reverse(result);
        result.forEach(System.out::println);
    }
}