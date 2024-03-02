package ru.sgu;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

class DataRow {
    public String name, surname, paternal;
    public String company;
    public Integer rating;

    public DataRow(String surname, String name, String paternal, String company, Integer rating) {
        this.surname = surname;
        this.name = name;
        this.paternal = paternal;
        this.company = company;
        this.rating = rating;
    }

    public String toString() {
        return String.format(
            "%s %s %s %s %d",
            this.surname,
            this.name,
            this.paternal,
            this.company,
            this.rating
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DataRow)) {
            return false;
        }
        DataRow row = (DataRow) o;
        return row != null
            && row.surname.equals(this.surname)
            && row.name.equals(this.name)
            && row.paternal.equals(this.paternal)
            && row.company.equals(this.company)
            && row.rating.equals(this.rating);
    }

    @Override
    public int hashCode() {
        return this.surname.hashCode()
             + this.name.hashCode()
             + this.paternal.hashCode()
             + this.company.hashCode()
             + this.rating;
    }
}

public class Stonks
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        var set = new HashSet<DataRow>();
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] ops = line.split("\\s+");
            if (ops.length != 5) {
                System.out.println("Wrong number of columns.");
                continue;
            }
            Integer rating = Integer.parseInt(ops[4]);
            DataRow row = new DataRow(ops[0], ops[1], ops[2], ops[3], rating);
            set.add(row);
        }

        var data = new ArrayList<DataRow>(set);
        data.sort((r1, r2) -> r2.rating.compareTo(r1.rating));
        data.sort((r1, r2) -> r1.surname.compareTo(r2.surname));
        data.sort((r1, r2) -> r2.name.compareTo(r1.name));
        data.sort((r1, r2) -> r1.paternal.compareTo(r2.paternal));

        for (DataRow row : data) {
            System.out.println(row.toString());
        }
    }
}
