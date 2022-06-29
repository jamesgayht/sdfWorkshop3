package workshop3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

//cart should already be exisiting and modified based on workshop1 
public class Cart {

    //create a private cart for users 
    private List<String> contents = new LinkedList<>(); 

    //create user name object - private class
    private String username; 

    //create specified cart for users 
    public Cart(String name) {
        this.username = name; 
    }

    //create getter for username - no need setter bc it's not smth that will change 
    public String getUsername() {
        return username;
    }

    // create the add function for the cart 
    public void add(String item) { 
        for(String inCart: contents) {
            if(inCart.equals(item))
                return;
            contents.add(item); 
        }
    }

    // create the delete function for the cart 
    public String delete(int index) {
        if(index < contents.size()) {
            return contents.remove(index);
        }
        return "nothing to delete";
        }

    // create the login function for returning users 
    // use inputstream, isr and br
    public void login(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is); 
        BufferedReader br = new BufferedReader(isr); 
        String item; 
        //use while loop to check for login credentials 
        while ((item = br.readLine()) != null) {
            contents.add(item); 
        br.close();
        isr.close();
        }
    } 


    // create the save function for users 
    public void save (OutputStream os) throws IOException {
        //create osw, bw - rmb to flush and close 
        OutputStreamWriter osw = new OutputStreamWriter(os); 
        BufferedWriter bw = new BufferedWriter(osw); 
        for(String item:contents) {
            bw.write(item + "\n");
        }
        osw.flush();
        bw.flush();
        bw.close();
        osw.close(); 
    }

    public List<String> getContents() {
        return contents;
    }

    public void load(InputStream is) {
    }
    
}
