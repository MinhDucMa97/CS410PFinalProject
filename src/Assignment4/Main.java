//Minh Ma
//Assignment4 - linked list within an AVL tree - JAVA
//CS202
//Main file of assignment 4

package Assignment4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        char choice = ' ';
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();
        tree.openFile();
        tree.readFile();
        tree.closeFile();
        System.out.println();

        do{
            System.out.println();
            System.out.println("(A)dd a service in an existing service category.");
            System.out.println("(D)isplay all services.");
            System.out.println("(S)how all provider options in an existing service based on service name");
            System.out.println("(E)liminate all provider options in an existing service.");
            System.out.println("(W)ithdraw a provider option in existing service");
            System.out.println("(R)etrieve services.");
            System.out.println("(Q)uit the program.");
            System.out.print("Enter the task you would like to do with this program: ");
            choice = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();
            if(choice == 'Q'){  //Program will terminate after Q is stored in choice variable
                System.out.println("Thank you for using this program");
            }
            else if(choice == 'D'){ //Display everydata within a tree and a list
                tree.displayTree();
            }
            else if(choice == 'S'){ // Display data based on type;
                System.out.println("Enter the type of the service you would like to display: ");
                String serviceType = scanner.nextLine();
                System.out.println("This is the whole record of the service: ");

                tree.displayOne(serviceType);
            }
            else if(choice == 'E'){ //Eliminate all the information of a list of a type
              System.out.print("Enter the type of service you would like to remove all list: ");
              String removeType = scanner.nextLine();
              tree.removeAllList(removeType);
            }
            else if(choice == 'A'){ // Add a new service option within an existing type
                System.out.print("Enter the type of service: ");
                String tType = scanner.nextLine();

                System.out.print("Enter the service title: ");
                String tTitile = scanner.nextLine();

                System.out.print("Enter the your name as a provider of the service: ");
                String tProvider = scanner.nextLine();

                System.out.print("Enter the description of the service: ");
                String tDescription = scanner.nextLine();

                System.out.print("Enter the price of the service: ");
                String tPrice = scanner.nextLine();

                tree.addTree(tType,tTitile,tProvider,tDescription,tPrice);
            }
            else if(choice == 'W'){ //Remove a item of a list base on provider name and service name
                System.out.print("Enter the type of service you would like to delete a provider option and service name: ");
                String tType = scanner.nextLine();

                System.out.print("Enter the service name you would like to delete: ");
                String tserviceName = scanner.nextLine();

                System.out.print("Enter the provider name you would like to delete: ");
                String tproviderName = scanner.nextLine();

                tree.removeListNode(tType,tserviceName,tproviderName);
            }
            else if(choice == 'R'){
                System.out.print("Enter the type of service you would like to retrieve: ");
                String tType = scanner.nextLine();

                System.out.println("Enter all the information below to retrieve.");
                System.out.print("Enter the service title: ");
                String tTitile = scanner.nextLine();

                System.out.print("Enter the name as a provider of the service: ");
                String tProvider = scanner.nextLine();

                System.out.print("Enter the description of the service: ");
                String tDescription = scanner.nextLine();

                System.out.print("Enter the price of the service: ");
                String tPrice = scanner.nextLine();

                tree.Retrieve(tType,tTitile,tProvider,tDescription,tPrice);
            }


        }while(choice != 'Q');
    }
}
