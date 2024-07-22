package in.ezeon;

/**
 * This class is an entry point of execution for PersonalExpenseManager Application (PEMApp).
 * @author PC
 */
public class StartApp {
    /**
    * This method is creating <code>PEMService</code> object and show app menu by calling showMenu() method
    * @param args
    */
    public static void main(String[] args) {
        // TODO code application logic here
        PEMService service = new PEMService();
        service.showMenu();
        
    }
    
}
