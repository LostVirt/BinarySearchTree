import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;


public class Main {

    private static String directory = System.getProperty("user.dir"); // User Directory
    private static String litenData = directory + "\\src\\Data\\liten.log"; // Liten Log Fil
    private static String storData = directory + "\\src\\Data\\stor.log"; // Stor Log Fil

    public static void main(String[] args) {

        Tree treeLiten = new Tree();
        Tree treeStor = new Tree();

        long startLitenTid = System.nanoTime();
        addData(treeLiten, litenData);
        long slutLitenTid = System.nanoTime();
        System.out.println("Liten Data Fil Tid: " + TimeUnit.MILLISECONDS.convert((slutLitenTid - startLitenTid), TimeUnit.NANOSECONDS) + "ms");

        checkIfExist(treeLiten, "158.142.123.32", "209.34.182.63", "26.174.140.163", "157.230.186.58", "34.155.72.12", "128.148.68.247", "98.231.65.27", "16.192.128.249", "220.75.13.138", "140.167.51.71");
        checkIfExist(treeLiten, "158.142.123.2", "158.142.123.1", "209.34.182.3", "0.0.0.0");
        System.out.println("Liten Datafil: " + treeMinMax(treeLiten));



        long startStorTid = System.nanoTime();
        addData(treeStor, storData);
        long slutStorTid = System.nanoTime();
        System.out.println("Stor Data Fil Tid: " + TimeUnit.MILLISECONDS.convert((slutStorTid - startStorTid), TimeUnit.NANOSECONDS) + "ms");

        checkIfExist(treeStor, "158.142.123.32", "209.34.182.63", "26.174.140.163", "157.230.186.58", "34.155.72.12", "128.148.68.247", "98.231.65.27", "16.192.128.249", "220.75.13.138", "140.167.51.71");
        checkIfExist(treeStor, "158.142.123.2", "158.142.123.1", "209.34.182.3", "0.0.0.0");
        System.out.println("Stor Datafil: " + treeMinMax(treeStor));

    }

    /**
     * Returnerar min och max för respektive träd
     *
     * @param tree
     * @return
     */
    public static String treeMinMax(Tree tree) {
        return "Tree Max: " + tree.max() + " Tree Min: " + tree.min();
    }

    /**
     * Tar emot träd samt argument och använder get metoden för att kolla om den finns i trädet
     * get returnerar null om den inte finns.
     *
     * @param tree
     * @param args
     */
    public static void checkIfExist(Tree tree, String... args) {
        for (String arg : args) {
            String[] values = tree.get(arg);
            if (values != null) {
                System.out.println("IP Address: " + arg + " Time Stamp: " + values[0] + " File Name: " + values[1]);
            } else System.out.println("IP Address: " + arg + " Does not Exist!");
        }
    }

    /**
     * importerar data in i respektive träd.
     *
     * @param tree Trädet
     * @param path pathen till data filen som ska läses in
     */
    public static void addData(Tree tree, String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(s -> {
                String[] split = s.split(" ");
                int tidPunkt = parseInt(split[0]);
                String ipAddress = split[1];
                long numericIpAddress = toNumeric(split[1]);
                StringBuilder filNamn = new StringBuilder();
                if (split.length > 3) {
                    for (int i = 2; i < split.length; i++) {
                        filNamn.append(split[i]);
                    }
                } else {
                    filNamn = new StringBuilder(split[2]);
                }
                tree.add(numericIpAddress, tidPunkt, filNamn.toString(), ipAddress);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Konverterar ip addressen till ett numeric värde
     * @param ip
     * @return
     */

    public static Long toNumeric(String ip) {
        Scanner sc = new Scanner(ip).useDelimiter("\\.");
        Long l = (sc.nextLong() << 24) + (sc.nextLong() << 16) + (sc.nextLong() << 8)
                + (sc.nextLong());

        sc.close();
        return l;
    }
}
