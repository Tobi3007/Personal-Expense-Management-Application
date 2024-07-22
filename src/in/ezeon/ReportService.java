package in.ezeon;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
/**
 * The class contains various methods to calculate PEMApp report.
 * @author PC
 */
public class ReportService {
    /**
     * Declare a references of singleton repository.
     */
    private Repository repo = Repository.getRepository();
    /**
     * The method calculates monthly-total.
     * It's preparing data in proper order.
     * @return result in Map.
     */ 
    public Map<String,Float> calculateMonthlyTotal(){
        Map<String,Float> m = new TreeMap();
        for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            String yearMonth = DateUtil.getYearAndMonth(expDate);
            if(m.containsKey(yearMonth)){
                //when expense is already present for a month
                Float total = m.get(yearMonth);
                total = total + exp.getAmount();
                m.put(yearMonth, total); //new total; replace all total
            }else{
                //this month is not yet added, so add here
                m.put(yearMonth, exp.getAmount());
            }
        }
        return m; //return final result as Map
    }
    /**
     * The method calculates yearly-total.
     * It's preparing data in proper order.
     * @return result in Map.
     */ 
    public Map<Integer,Float> calculateYearlyTotal(){
        Map<Integer,Float> m = new TreeMap();
        for(Expense exp : repo.expList){
            Date expDate = exp.getDate();
            Integer year = DateUtil.getYear(expDate);
            if(m.containsKey(year)){
                //when expense is already present for a year
                Float total = m.get(year);
                total = total + exp.getAmount();
                m.put(year, total); //new total; replace all total
            }else{
                //this year is not yet added, so add here
                m.put(year, exp.getAmount());
            }
        }
        return m; //return final result as Map
    }
    /**
     * The method calculates categorized-total.
     * It's preparing data in proper order.
     * @return result in Map.
     */ 
    public Map<String,Float> calculateCategorizedTotal(){
        Map<String,Float> m = new TreeMap();
        for(Expense exp : repo.expList){
            Long categoryId = exp.getCategoryId();
            String catName = this.getCategoryNameById(categoryId);            
            if(m.containsKey(catName)){
                //when expense is already present for a category
                Float total = m.get(catName);
                total = total + exp.getAmount();
                m.put(catName, total); //new total; replace all total
            }else{
                //this category is not yet added, so add here
                m.put(catName, exp.getAmount());
            }
        }
        return m; //return final result as Map
    }
    /**
     * The method returns category name for given categoryId.
     * @param categoryId
     * @return null when wrong categoryId is supplied.
     */ 
    public String getCategoryNameById(Long categoryId){
        for(Category c : repo.catList){
            if(c.getCategoryId().equals(categoryId)){
                return c.getName();
            }
        }
        return null;//no such category id present
    }
}
