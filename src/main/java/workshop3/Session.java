package workshop3;

import java.io.Console;
import java.util.List;

public class Session {

    //list all the commands and set them as public static final class 
    public static final String LIST = "list"; 
    public static final String CARTS = "carts"; 
    public static final String ADD = "add"; 
    public static final String DEL = "del"; 
    public static final String LOAD = "load"; 
    public static final String USERS = "users"; 
    public static final String SAVE = "save"; 
    public static final String END = "end"; 
    public static final String LOGIN = "login"; 

    //repo and cart should be private
    private Repository repository; 
    private Cart currCart; 

    public Session (Repository repo) {
        this.repository = repo; 
    }

    public void start() {
        Console cons = System.console(); 
        boolean stop = false; 
        currCart = new Cart("anon"); 

        while(!stop) {
            String input = cons.readLine("> "); 
            //split the user input 
            String[] terms = input.split(" "); 
            //use switch case for commands 
            switch (terms[0]) {

                // LIST
                case LIST:
                // get the user's cart items 
                System.out.printf("Items in %s's shopping cart:\n", currCart.getUsername()); 
                printList(currCart.getContents()); 
                break;
                
                //CARTS
                case CARTS: 
                System.out.println("List of shopping carts");
                break; 
                
                //ADD 
                case ADD: 
                // set up a count for before
                int before = currCart.getContents().size(); 
                // set up for loop to add stuff 
                for (int i=0; i < terms.length; i++) 
                    currCart.add(terms[i]);
                // set up a count for after - before 
                int addedCount = currCart.getContents().size() - before; 
                // add a print statement to inform on no. of items added 
                System.out.printf("Added %d item(s) to %s's cart.\n ", addedCount, currCart.getUsername());
                break; 

                //DEL
                // set up a delindex
                case DEL:
                int delIndex = Integer.parseInt(terms[1]); 
                //create item to be deleted to print for the user also to remove from cart
                String item = currCart.delete(delIndex - 1); 
                // add a print statement to inform on items removed 
                System.out.printf("Removed %s from %s's cart. \n", item, currCart.getUsername());
                break; 

                //LOAD
                case LOAD: 
                currCart = repository.load(currCart.getUsername()); 
                System.out.printf("Loaded %s's cart. There are %d item(s)\n", 
                                currCart.getUsername(), currCart.getContents().size());
                break; 

                // USERS
                case USERS: 
                List<String> allCarts = repository.getShoppingCarts(); 
                this.printList(allCarts);
                break; 

                //SAVE
                case SAVE:
                repository.save(currCart);
                System.out.println("Saved. ");
                break; 

                //END 
                case END: 
                stop = true;
                break; 

                //LOGIN 
                case LOGIN: 
                //1 because the terms[0] is login - 1 will be the username 
                currCart = new Cart(terms[1]); 
                System.out.printf("Logged in to %s's cart. \n", currCart.getUsername());
                break; 

                default:
                System.err.printf("Unknown input: %s \n", terms[0]);
            }
        }
        System.out.println("Thank you for shopping with us! ");
    }

    private void printList(List<String> list) {
        if(list.size() <= 0) {
            System.out.println("No record found! ");
            return;
        }
        for (int i=0; i<list.size(); i++) { 
            System.out.printf("%d. %s\n", (i+1), list.get(i));
        }
    }
}
