//Minh Ma
//Assignment4 - linked list within an AVL tree - JAVA
//CS202
//Tree implementation

package Assignment4;
import java.util.*;
import java.io.*;
import java.util.Scanner;


public class Tree {
    private Scanner scanner;

    protected Tree_Node root;
    protected int size;

    public Tree() {
        root = null;
        size = 0;
    }

    //open text file
    public void openFile() {
        try {
            scanner = new Scanner(new File("data.txt"));
        } catch (Exception e) {
            System.out.println("File can not open. File was not found");
        }
    }

    // c
    //read data from file
    public void readFile() {
        scanner.useDelimiter(",|\r\n");
        if (scanner.hasNext()) {
            String type = scanner.next();
            String serviceName = scanner.next();
            String providerName = scanner.next();
            String description = scanner.next();
            String price = scanner.next();
            //scanner.nextLine();
            addTree(type, serviceName, providerName, description, price);
            readFile();
        }
    }
    //close file after done reading
    public void closeFile() {
        scanner.close();
    }
    //return the height of the tree
    public int height() {
        if (root == null) {
            return 0;
        }
        return root.height;
    }

    // add new type(node) into the tree
    public void addTree(String type, String serviceName, String providerName, String description, String price) {
        Tree_Node newNode = new Tree_Node(type, serviceName, providerName, description, price);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        root = addTree(root, newNode, type, serviceName, providerName, description, price);
        size++;
    }

    public Tree_Node addTree(Tree_Node root, Tree_Node newNode, String type,
                             String serviceName, String providerName, String description, String price) {

        if (root == null) {
            return newNode;
        }
        if (newNode.service.name.compareTo(root.service.name) < 0) {
            root.left = addTree(root.left, newNode, type, serviceName, providerName, description, price);
        } else if (newNode.service.name.compareTo(root.service.name) > 0) {
            root.right = addTree(root.right, newNode, type, serviceName, providerName, description, price);
        } else {
            addService_List(root, type, serviceName, providerName, description, price);
        }
        update(root);
        return balance(root);
    }

    //update height of the tree
    protected void update(Tree_Node node) {
        int left = (node.left == null) ? -1 : node.left.height;
        int right = (node.right == null) ? -1 : node.right.height;

        node.height = 1 + Math.max(left, right);
        node.balanceFac = right - left;
    }

    //Update or rotate tree after adding new tree node into tree
    protected Tree_Node balance(Tree_Node node) {
        if (node.balanceFac == -2) {
            if (node.left.balanceFac <= 0) {
                return rightRotate(node);
            } else {
                return leftrightRotate(node);
            }
        } else if (node.balanceFac == 2) {
            if (node.right.balanceFac >= 0) {
                return leftRotate(node);
            } else {
                return rightleftRotate(node);
            }
        }
        return node;
    }

    //Rotating the tree to make it AVL tree
    protected Tree_Node leftRotate(Tree_Node node) {
        Tree_Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        update(node);
        balance(temp);
        return temp;
    }

    protected Tree_Node rightRotate(Tree_Node node) {
        Tree_Node temp = node.left;
        node.left = temp.left;
        temp.right = node;
        update(node);
        balance(temp);
        return temp;
    }

    protected Tree_Node leftrightRotate(Tree_Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    protected Tree_Node rightleftRotate(Tree_Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    /*
        public void addService_List(String type, String serviceName, String providerName, String description, String price){
            this.root = addService_List(this.root, type,serviceName,providerName,description,price);
        }
    */
    public Tree_Node addService_List(Tree_Node root, String type, String serviceName, String providerName, String description, String price) {
        if (root == null || root.service.name.compareTo(type) == 0) {
            root.addListNode(serviceName, providerName, description, price);
            return root;
        }
        else if (root.service.name.compareTo(type) < 0) {
            return addService_List(root.getRight(), type, serviceName, providerName, description, price);
        }
        return addService_List(root.getLeft(), type, serviceName, providerName, description, price);

    }

    // Display All Tree and All List in every tree node
    public void displayTree() {
        if (root == null) {
            System.out.println("There are no services available. Nothing to display");
        }
        displayTree(root);
    }

    public void displayTree(Tree_Node root) {
        if (root == null) {
            return;
        }
        displayTree(root.left);
        System.out.println("Service type: " + root.service.name);
        root.displayList();
        displayTree(root.right);
    }

    //Traverse to find a matching tree node and displaying the list within the tree node
    public void displayOne(String serviceType) {
        displayOne(this.root, serviceType);
    }

    public void displayOne(Tree_Node root, String serviceType) {
        if (root == null) {
            return;
        } else if (root.service.name.compareTo(serviceType) < 0) {
            displayOne(root.right, serviceType);
        } else if (root.service.name.compareTo(serviceType) > 0) {
            displayOne(root.left, serviceType);
        } else if (root.service.name.compareTo(serviceType) == 0) {
            root.displayList();
        }
    }



    //Remove all in a tree node
    public void removeAllList(String type){
        removeAllList(this.root,type);
    }

    public void removeAllList(Tree_Node root, String type){
        if(root == null)
            return;
        else if(root.service.name.compareTo(type) < 0){
            removeAllList(root.right, type);
        }
        else if(root.service.name.compareTo(type) > 0){
            removeAllList(root.left,type);
        }
        else if(root.service.name.compareTo(type) == 0){
            System.out.println("Service Type:" + root.service.name);
            root.removeAll_List();
        }
    }

    //Remove a linked list node
    public void removeListNode(String type, String serviceName, String providerName){
        removeListNode(this.root,type,serviceName,providerName);
    }

    public  void removeListNode(Tree_Node root, String type, String serviceName, String providerName){
        if(root == null){
            return;
        }
        if(root.service.name.compareTo(type) < 0){
            removeListNode(root.right,type,serviceName,providerName);
        }
        else if(root.service.name.compareTo(type) > 0){
            removeListNode(root.left,type,serviceName,providerName);
        }
        else if(root.service.name.compareTo(type) == 0 ){
            System.out.println("Type" + type );
            root.removeNode_List(serviceName,providerName);
            //root.deleteNode(serviceName,providerName);
        }

    }

    public void Retrieve(String type,String serviceName, String providerName, String description, String price){
        Retrieve(root,type,serviceName,providerName,description,price);
    }

    public void Retrieve(Tree_Node root,String type, String serviceName, String providerName, String description, String price){
        if(root == null){
            System.out.println("Nothing can display. No data was recorded");
            return;
        }
        if(root.service.name.compareTo(type) < 0)
            Retrieve(root.right,type,serviceName,providerName,description,price);
        else if(root.service.name.compareTo(type) > 0)
            Retrieve(root.left,type,serviceName,providerName,description,price);
        else if(root.service.name.compareTo(type) == 0)
            System.out.print("Type " + type + "\n");
            root.researchList(serviceName,providerName,description,price);

    }
}
