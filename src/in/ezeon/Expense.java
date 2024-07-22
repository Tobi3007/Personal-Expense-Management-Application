package in.ezeon;
import java.io.Serializable;
import java.util.Date;
/**
 * This is a domain class represent Expense.
 * @author PC
 */
public class Expense implements Serializable{
    /**
     * A unique expense id, here its auto-generated as current milliseconds,
     * but in real time application it should be generated using some profession strategy or algorithms.
     */
    private Long expensedId= System.currentTimeMillis();
    /**
     * Represents a category of this expense.
     */
    private Long categoryId; //Khoa ngoai
    private Float amount;
    private Date date;
    private String remark;

    public Expense() {
    }

    public Expense(Long categoryId, Float amount, Date date, String remark) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
    }

    public Long getExpensedId() {
        return expensedId;
    }

    public void setExpensedId(Long expensedId) {
        this.expensedId = expensedId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
