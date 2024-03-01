package ru.sgu;

import java.math.BigDecimal;
import java.util.Scanner;

public class Calc
{
    static BigDecimal eval(BigDecimal left, BigDecimal right, String op) {
        return switch (op) {
            case "ADD" -> left.add(right);
            case "SUB" -> left.subtract(right);
            case "MULT" -> left.multiply(right);
            case "DIV" -> left.divide(right);
            case "REM" -> left.remainder(right);
            case "POW" -> left.pow(right.intValueExact());
            default ->
                throw new IllegalArgumentException("Wrong name of operand.");
        };
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] ops = line.split("\\s+");
            if (ops.length != 3) {
                System.out.println("Wrong count of operands.");
                continue;
            }
            BigDecimal left, right;
            try {
                left = new BigDecimal(ops[0]);
                right = new BigDecimal(ops[1]);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                continue;
            }
            try {
                BigDecimal res = eval(left, right, ops[2]);
                System.out.println(res);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
