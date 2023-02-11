public class Main {
    public static void main(String[] args) {
        int evenSum = 0;
        int oddSum = 0;
        for (int i = 0; i < args.length; i++) {
            int number = Integer.parseInt(args[i]);
            if (number % 2 == 0) {
                evenSum += number;
            } else {
                oddSum += number;
            }
        }
        System.out.println("Сумма чётных чисел в последовательности: " + evenSum);
        System.out.println("Сумма нечётных чисел в последовательности: " + oddSum);
    }
}