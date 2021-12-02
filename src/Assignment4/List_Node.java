//Minh Ma
//Assignment4 - linked list within an AVL tree - JAVA
//CS202
//node of linked list implementation


package Assignment4;

public class List_Node {
    protected String serviceName;
    protected String providerName;
    protected String price;
    protected String description;
    protected List_Node next;


    public List_Node(String serviceName,String providerName, String description, String price) {
        this.serviceName = serviceName;
        this.providerName = providerName;
        this.price = price;
        this.description = description;
        this.next = null;
    }

    //Display all data within a node of a list
    public void displayElement(){
        System.out.print("Type of service: " + serviceName + ", Provider Name: " + providerName + ", Description: " + description + ", Price: " + price+ "\n");
    }
}




