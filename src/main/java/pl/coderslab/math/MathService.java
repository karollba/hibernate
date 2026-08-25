package pl.coderslab.math;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }

    public long factorial(int a) {
        long res = 1;
        for (int i = 2; i <= a; i++) {
            res *= i;
        }
        return res;
    }
    public int mult(int a, int b) {
        return a * b;
    }
    public int div(int a, int b) {
       if (b == 0) {
           throw new ArithmeticException("Cannot divide by 0!");
       }
        return a / b;
    }
}