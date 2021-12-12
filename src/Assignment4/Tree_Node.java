
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

    //This function will create a node that contains all the
    //essential elements for a tree node. When the function
    //is called, a new node will be created.
    public Tree_Node(String type,
                     String serviceName,
                     String providerName,
                     String price,
                     String description) {
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

    //Return a left branch of the tree
    public Tree_Node getLeft() {
        return left;
    }

    //Return a right branhc of the tree
    public Tree_Node getRight() {
        return right;
    }

    //Add a node into an existing type's list
    public void addListNode(String serviceName,
                            String providerName,
                            String description,
                            String price) {
        addListNode(this.head, serviceName, providerName, description, price);
    }

    public void addListNode(List_Node head,
                            String serviceName,
                            String providerName,
                            String description,
                            String price) {
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

    //remove all node in the list based on the name of the serivce and provider which will be provided from user's inputs
    public void removeAll_List() {
        head = null;
    }

    public void removeNode_List(String serviceName,
                                String providerName) {
        this.head = removeNode_List(head, serviceName, providerName);
    }

    protected List_Node removeNode_List(List_Node head,
                                        String serviceName,
                                        String providerName) {
        if (head == null) {
            System.out.println("The item does not exist.");
            return null;
        }
        if (serviceName.equals(head.serviceName) &&
                providerName.equals(head.providerName) &&
                head.next != null) {
            head = head.next;
            return head;
        } else if (serviceName.equals(head.serviceName) &&
                providerName.equals(head.providerName) &&
                head.next == null) {

            List_Node temp = head.next;
            head.next = temp.next;
            return head;
        } else if (!serviceName.equals(head.next.serviceName)) {
            head.next = removeNode_List(head.next, serviceName, providerName);
        }
        return head;

    }

    //The functionality of this function is to display the list of service that are compatible to
    //the input of the users in term of name of service and provider, descrpition
    // of the service, and the price for that serivce
    public void researchList(String serviceName,
                             String providerName,
                             String description,
                             String price) {
        researchList(head, serviceName, providerName, description, price);
    }

    public void researchList(List_Node head,
                             String DesireServiceName,
                             String DesireProviderName,
                             String DesireDescription,
                             String DesirePrice) {
        if (head == null) {
            return;
        }
        if (head.serviceName.compareTo(DesireServiceName) == 0 &&
                head.providerName.compareTo(DesireProviderName) == 0 &&
                head.description.compareTo(DesireDescription) == 0 &&
                head.price.compareTo(DesirePrice) == 0) {
            head.displayElement();

        } else {
            researchList(head.next,
                    DesireServiceName,
                    DesireProviderName,
                    DesireDescription,
                    DesirePrice);
        }

    }
}
