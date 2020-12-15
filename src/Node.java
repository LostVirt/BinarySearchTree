
public class Node {
    long key; // Numeriska värdet för ip addressen
    int timeStamp; // Tidstämpeln i datan
    String fileName; // Filnamnet i datan
    String ipAddress; // ip addressen i datan
    Node left, right;

    public Node(long item, int time, String file, String ip)
    {
        key = item;
        timeStamp = time;
        fileName = file;
        ipAddress = ip;
        left = right = null;
    }
}
