
//Minh Ma
//Assignment4 - linked list within an AVL tree - JAVA
//CS202
//Tree node implementation

package Assignment4;

public class Tree_Node {
    protected Service service;
    protected int balanceFac;
    int height;

    protected List_Node head;
    protected Tree_Node left;
    protected Tree_Node right;

    public Tree_Node(String type, String serviceName, String providerName, String price, String description) {
        if (type.equals("Delivery")) {
            service = new Delivery(type);
        } else if (type.equals("Online Tutoring")) {
            service = new Online_Tutor(type);
        } else if (type.equals("Cooking")) {
            service = new Cooking(type);
        }
        head = new List_Node(serviceName, providerName, price, description);
        this.left = null;
        this.right = null;
        this.balanceFac = 0;
        this.height = 0;
    }

    public Tree_Node getLeft() {
        return left;
    }


    public Tree_Node getRight() {
        return right;
    }

    //Add a node into an existing type's list
    public void addListNode(String serviceName, String providerName, String description, String price) {
        addListNode(this.head, serviceName, providerName, description, price);
    }

    public void addListNode(List_Node head, String serviceName, String providerName, String description, String price) {
        if (head == null) {
            List_Node temp = new List_Node(serviceName, providerName, description, price);
            this.head = temp;
        }
        if (head.next == null) {
            List_Node temp = new List_Node(serviceName, providerName, description, price);
            head.next = temp;
        } else
            addListNode(head.next, serviceName, providerName, description, price);
    }

    //Display all list within a type
    public void displayList() {
        if (this.head == null) {
            System.out.println("List does not exist");
        } else {
            displayList(head);
        }
    }

    protected void displayList(List_Node head) {
        if (head == null) {
            return;
        } else {
            head.displayElement();
            displayList(head.next);
        }
    }

    //remove all node in the list
    public void removeAll_List() {
        head = null;
    }

    public void removeNode_List(String serviceName, String providerName) {
        this.head = removeNode_List(head, serviceName, providerName);
    }

    protected List_Node removeNode_List(List_Node head, String serviceName, String providerName) {
        if (head == null) {
            System.out.println("The item does not exist.");
            return null;
        }
        if (serviceName.equals(head.serviceName) && providerName.equals(head.providerName) && head.next != null) {
            head = head.next;
            return head;
        } else if (serviceName.equals(head.serviceName) && providerName.equals(head.providerName) && head.next == null) {
            List_Node temp = head.next;
            head.next = temp.next;
            return head;
        } else if (!serviceName.equals(head.next.serviceName)) {
            head.next = removeNode_List(head.next, serviceName, providerName);
        }
        return head;

    }

    public void researchList(String serviceName, String providerName, String description, String price) {
        researchList(head, serviceName, providerName, description, price);
    }

    public void researchList(List_Node head, String SserviceName, String SproviderName, String Sdescription, String Sprice) {
        if (head == null) {
            return;
        }
        if (head.serviceName.compareTo(SserviceName) == 0 && head.providerName.compareTo(SproviderName) == 0
                && head.description.compareTo(Sdescription) == 0 && head.price.compareTo(Sprice) == 0) {
            head.displayElement();

        } else {
            researchList(head.next, SserviceName, SproviderName, Sdescription, Sprice);
        }

    }
}
