package in.ezeon;
import java.io.Serializable;

/**
 * This is a domain class represents a Category.
 * @author PC
 */
public class Category implements Serializable{
    /**
     * It refers to a unique category Id. 
     * Here, it is simply generated using current time, 
     * but in real time application it should be generated using some profession strategy or algorithms.
     */
    private Long categoryId=System.currentTimeMillis();
    /**
     * Name of expense category.
     */
    private String name;

    public Category(String name) {
        this.name = name;
    }
    
     public Category(Long categoryId, String name) {
        this.categoryId=categoryId;
        this.name = name;
    }

    public Category() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
