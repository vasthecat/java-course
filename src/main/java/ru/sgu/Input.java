package ru.sgu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input
{
    static boolean isValid(String name) {
        for (char ch : name.toCharArray()) {
            if (!( ch >= 'A' && ch <= 'Z'
                || ch >= 'a' && ch <= 'z'
                || ch >= 'А' && ch <= 'Я'
                || ch >= 'а' && ch <= 'я'
                || ch == 'ё' || ch == 'Ё')) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        File myObj = new File("input.txt");
        Scanner input;
        try {
            input = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] ops = line.split("\\s+", 4);
            if (ops.length < 3) {
                System.out.println();
                continue;
            }
            String surname = ops[0], name = ops[1], paternal = ops[2];
            if (!isValid(surname) || !isValid(name) || !isValid(paternal)) {
                System.out.println();
                continue;
            }
            String nameNormal =
                name.substring(0, 1).toUpperCase()
                + name.substring(1).toLowerCase();
            System.out.printf(
                "%s %s.%s.\n",
                nameNormal,
                surname.substring(0, 1).toUpperCase(),
                paternal.substring(0, 1).toUpperCase()
            );
        }
        input.close();
    }
}
