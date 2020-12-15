public class Tree {
    // Roten av binära sök trädet
    Node root;

    // Konstruktör
    Tree()
    {
        root = null;
    }

    // Kallar metoden insert
    void add(long key, int timeStamp, String fileName, String ipAddress)
    {
        root = insert(root, key, timeStamp, fileName, ipAddress);
    }

    // Metod för att rekursivt infoga ny nyckel in det binära sök trädet
    Node insert(Node root, long key, int timeStamp, String fileName, String ipAddress)
    {

        // Om trädet är tomt returnera en ny nod
        if (root == null)
        {
            root = new Node(key, timeStamp, fileName, ipAddress);
            return root;
        }

        // Annars fortsätt rekursivt genom trädet
        if (key < root.key)
            root.left = insert(root.left, key, timeStamp, fileName, ipAddress);
        else if (key > root.key)
            root.right = insert(root.right, key, timeStamp, fileName, ipAddress);

        // Returnerar oförändrad nodpekare
        return root;
    }

    // Kallar metoden printOrderedTree
    public void printTree()
    {
        printOrderedTree(root);
    }

    // Navigerar trädet i ordning och printar ut värdet för varje nod
    private void printOrderedTree(Node root) {
        if (root != null) {
            printOrderedTree(root.left);
            System.out.println(root.key + " " + root.ipAddress + " " + root.timeStamp + " " + root.fileName);
            printOrderedTree(root.right);
        }
    }

    // Kallar metoden searchTree
    public String[] get(String key) {
        return searchTree(root, Main.toNumeric(key));
    }

    private String[] searchTree(Node root, long key) {
        
        // Om ip addressen inte finns i trädet så returnera null
        if (root==null)
            return null;

        // Om ip addressen är hittad skapa en Array av Strings där tidstämpeln samt filnamnet matas in och sedan returneras till get metoden
        if (root.key == key) {
            String[] nodeValues = new String[2];
            nodeValues[0] = String.valueOf(root.timeStamp);
            nodeValues[1] = root.fileName;
            return nodeValues;
        }

        // Om ip addressen är större än rotens ip address
        if (root.key < key)
            return searchTree(root.right, key);

        // Om ip addressen är mindre än rotens ip address
        return searchTree(root.left, key);
    }

    public String min() {
        Node current = root;

        // Loopar igenom trädet till vänster tills den når slutet sedan returnerar ip addressen på sista platsen
        while (current.left != null) {
            current = current.left;
        }
        return (current.ipAddress);
    }

    public String max() {
        Node current = root;

        // Loopar igenom trädet till höger tills den når slutet sedan returnerar ip addressen på sista platsen
        while (current.right != null) {
            current = current.right;
        }
        return (current.ipAddress);
    }
}
