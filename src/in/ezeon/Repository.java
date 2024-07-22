package in.ezeon;
import java.util.ArrayList;
import java.util.List;
/**
 * The class is used as Database/Repository and its a singleton.
 * @author PC
 */
public class Repository {
    /**
     * The list holds all expense added by user.
     */
    public List<Expense> expList = new ArrayList();
    /**
     * The list holds all expense-categories added by user.
     */
    public List<Category> catList = new ArrayList();
    /**
     * A singleton reference of repository.
     */
    private static Repository repository;
    /**
     * Private constructor to restrict object creation from outside.
     */
    private Repository(){
    }
    /**
     * The method provides a singleton object of repository.
     * @return 
     */
    public static Repository getRepository(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    
}
