package workshop3;


public class App 
{
    private static String defaultDB = "db";
    public static void main( String[] args )
    {   
        System.out.println("Welcome to the shopping cart app! Please key in your name to login. ");
        //print out first argument db name use to create the directory 
        if(args.length>0) {
            if(args[0] != null) {
                // System.out.println(args[0]);
                App.defaultDB = args[0]; 
            }
        }
        System.out.println(defaultDB);
        Repository repo = new Repository(defaultDB); 
        Session session = new Session(repo); 
        session.start();
        }
}
