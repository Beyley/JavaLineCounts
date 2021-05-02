package org.beyley.javalinecounts;

public class App {
    public static void main(String[] args) {
        LineCounter counter = new LineCounter("/home/beyley/PROGRAMMING/JAVA/osu2007server/osu2007server/");

        counter.allowedExtensions = LineCounter.javaExtensions;

        counter.start();

        System.out.println(counter.lines);
    }
}
