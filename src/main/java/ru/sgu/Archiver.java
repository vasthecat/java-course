package ru.sgu;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

public class Archiver
{
    static ArrayList<String> getPaths(File dirfile) {
        var filepaths = new ArrayList<String>();
        var dirs = new LinkedList<File>();
        dirs.add(dirfile);

        while (!dirs.isEmpty()) {
            File dir = dirs.poll();
            String[] paths = dir.list();
            for (String filename : paths) {
                File subpath = new File(dir, filename);
                if (subpath.isFile()) {
                    filepaths.add(subpath.getPath());
                }
                else if (subpath.isDirectory()) {
                    dirs.add(subpath);
                }
            }
        }
        return filepaths;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        input.close();
        String[] ops = line.split("\\s+");
        if (ops.length < 2) {
            System.out.println("Wrong number of arguments.");
            return;
        }
        String dirpath = ops[0], substr = ops[1].toLowerCase();
        var dirfile = new File(dirpath);

        var files = getPaths(dirfile);
        var filtered = new ArrayList<String>();
        for (String path : files) {
            if (path.toLowerCase().contains(substr)) {
                filtered.add(path);
            }
        }

        try {
            var fos = new FileOutputStream(dirfile.getName() + ".zip");
            var bos = new BufferedOutputStream(fos);
            var zip = new ZipOutputStream(bos);
            for (String path : filtered) {
                zip.putNextEntry(new ZipEntry(path));
            }
            zip.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
