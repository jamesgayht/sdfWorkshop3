package workshop3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

//task1 adding options to specify directory with shopping cart 
public class Repository {

    //create a private object 
    private File repository; 

    //create a public object for new repo 
    public Repository (String repo) {
        this.repository = new File(repo); 
    }   

    //used to compile all shopping carts for users 
    public List<String> getShoppingCarts() {
        List<String> carts = new LinkedList<>(); 
        //for loop to add .db to all the file names
        for(String n: repository.list()) {
            carts.add(n.replace(".cart", ""));
        }
        return carts; 
    }

    //create new object for saving 
    public void save(Cart cart) {
        String cartName = cart.getUsername() + ".cart"; 
        String saveLocation = repository.getPath() + File.separator + cartName; 
        File saveFile = new File(saveLocation); 
        OutputStream os = null; 
        try{ 
            if(!saveFile.exists()) {
                Path path = Paths.get(repository.getPath()); 
                Files.createDirectories(path); 
                saveFile.createNewFile(); 
            }

            os = new FileOutputStream(saveLocation); 
            cart.save(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //create new object for loading 
    public Cart load (String username) {
        String cartName = username + ".cart"; 
        Cart cart = new Cart(username); 
        for (File cartFile: repository.listFiles()) {
            if(cartFile.getName().equals(cartName)) {
                try{
                    InputStream is = new FileInputStream(cartFile);
                    cart.load(is); 
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return cart;
        
    }

    
}
