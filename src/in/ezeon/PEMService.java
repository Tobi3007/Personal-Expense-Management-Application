package in.ezeon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The class contains most of the operations related to PEMApp.
 * <p>
 * This class prepare a menu and various methods are present to handle the user actions.
 * The class make use of <code>Repository</code> to store the data. 
 * Also using <code>ReportService</code> to generate different require reports.
 * @author PC
 */
public class PEMService {
    /**
     * Declare a reference of repository by calling a static method which return a singleton repository object.
     */
    Repository repo = Repository.getRepository();
    /**
    * Declare a reference of ReportService to call different method to calculate reports.
    */
    ReportService reportService = new ReportService();
    /**
     * Declare a scanner object to take standard input from keyboard. 
     */
    private Scanner in = new Scanner(System.in);
    /**
     * This variable store the value of menu-choice.
     */
    private int choice;
    
    /**
     * Call this constructor to create PEMService object with default details.
     */   
    public PEMService(){
          restoreRepository();
    }
    
    /**
     * This method prepares a PEMApp menu using switch-case and infinite loop, also ask for user choice.
     */
    public void showMenu(){
        while(true){
            printMenu();
            switch(choice){
                case 1:
                    onAddCategory();
                    pressAnyKeyToCOntinue();
                    break;
                case 2:
                    onCategoryList();
                    pressAnyKeyToCOntinue();
                    break;
                case 3:
                    onExpenseEntry();
                    pressAnyKeyToCOntinue();
                    break;
                case 4:
                    onExpenseList();
                    pressAnyKeyToCOntinue();
                    break;
                case 5:
                    onMonthlyExpenseList();
                    pressAnyKeyToCOntinue();
                    break;
                case 6:
                    onYearlyExpenseList();
                    pressAnyKeyToCOntinue();
                    break;
                case 7:
                    onCategorizedExpenseList();
                    pressAnyKeyToCOntinue();
                    break;
                case 0:
                    onExit();
                    break;
            }
        }
    }
    /**
     * This method prints a menu(CUI/CLI Menu)
     */
    public void printMenu(){
        System.out.println("-------------PEM Menu-------------");
        System.out.println("1. Add Category");
        System.out.println("2. Category List");
        System.out.println("3. Expense Entry");
        System.out.println("4. Expense List");
        System.out.println("5. Monthly Expense List");
        System.out.println("6. Yearly Expense List");
        System.out.println("7. Categorized Expense List");
        System.out.println("0. Exit");
        System.out.println("--------------------------");
        System.out.print("Enter your choice: ");
        choice=in.nextInt();
    }
    /**
     * This method is taking expense category name as input to add new category in the system.
     */
    public void onAddCategory(){
        in.nextLine(); //new line char is read here which is already present in stream and its not in use for now
        System.out.print("Enter Category name: ");
        String catName=in.nextLine();
        Category cat=new Category(catName);
        repo.catList.add(cat);
        System.out.println("Success: Category Added");
        //TODO
    }
    /**
     * Call this method to print existing category list
     */
    public void onCategoryList(){
        System.out.println("Category List");
        List<Category> clist = repo.catList;
        for(int i=0; i < clist.size(); i++){
            Category c= clist.get(i);
            System.out.println( (i+1)+". "+c.getName()+", "+c.getCategoryId() );
        }
    }
    /**
     * Call this method to enter expense detail. The entered detail will be added in the repository.
     */
    public void onExpenseEntry(){
        System.out.println("Enter Details for Expense Entry...");
        onCategoryList();
        System.out.print("Choose Category: ");
        int catChoice = in.nextInt();
        Category selectedCat = repo.catList.get(catChoice-1);
        
        System.out.print("Enter Amount: ");
        float amount = in.nextFloat();
        
        System.out.print("Enter Remark: ");
        in.nextLine();
        String remark = in.nextLine();
        
        //TODO Date can be taken form user input
        System.out.print("Enter Date(DD/MM/YYYY): ");
        String dateAsString = in.nextLine();
        Date date = DateUtil.stringToDate(dateAsString);
        
        //Add Expense detail in Expense object
        Expense exp=new Expense();
        exp.setCategoryId(selectedCat.getCategoryId());
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);
        
        //Store expese object in repository
        repo.expList.add(exp);
        System.out.println("Success: Expense Added");
    }
    /**
     * The method prints all entered expenses.
     */
    private void onExpenseList(){
        System.out.println("Expense Listind");
        List<Expense> expList = repo.expList;
        for(int i=0; i<expList.size();i++){
            Expense exp = expList.get(i);
            String catName = reportService.getCategoryNameById(exp.getCategoryId());
            String dateString= DateUtil.dateToString(exp.getDate());
            System.out.println((i+1)+"."+catName+", "+exp.getAmount()+", "+exp.getRemark()+", "+dateString);
        }
    }
     /**
     * This method is called from menu to prepare monthly-expense-total. 
     * It's using <code>ReportService</code> to calculate report. 
     * The returned result is printed by this method. 
     * Means this method invokes a call to generate report then the result is print by this method.
     */
    private void onMonthlyExpenseList(){
        System.out.println("Monthly Expense Total...");
        Map<String,Float> resultMap = reportService.calculateMonthlyTotal();
        Set<String> keys = resultMap.keySet();
        for (String yearMonth : keys){
            System.out.println(yearMonth+" : "+resultMap.get(yearMonth));
        }
    }

    /**
     * This method is called from menu to prepare yearly-expense-total. 
     * It's using <code>ReportService</code> to calculate report. 
     * The returned result is printed by this method. 
     * Means this method invokes a call to generate report then the result is print by this method.
     */
    private void onYearlyExpenseList(){
        System.out.println("Yearly Expense Total...");
        Map<Integer,Float> resultMap = reportService.calculateYearlyTotal();
        Set<Integer> years = resultMap.keySet();
        Float total = 0.0F;
        for (Integer year : years){
            Float exp = resultMap.get(year);
            total = total + exp;
            System.out.println(year+" : "+exp);
        }
        System.out.println("------------------------");
        System.out.println("Total Expense(Dong) : " + total);
    }
    /**
     * This method is called from menu to prepare categorized-expense-total. 
     * It's using <code>ReportService</code> to calculate report. 
     * The returned result is printed by this method. 
     * Means this method invokes a call to generate report then the result is print by this method.
     */
    private void onCategorizedExpenseList(){
        System.out.println("Category wise Expense Listing...");
        Map<String,Float> resultMap = reportService.calculateCategorizedTotal();
        Set<String> categories = resultMap.keySet();
        Float netTotal = 0.0F;
        for (String categoryName : categories){
            Float catWiseTotal = resultMap.get(categoryName);
            netTotal = netTotal + catWiseTotal;
            System.out.println(categoryName+" : "+catWiseTotal);
        }
        System.out.println("------------------------------------");
        System.out.println("Net Total (Dong) : " + netTotal);
  
    }
    /**
     * This method stops a JVM, before that it stores the repository permanently. 
     * It's closing application. It's like a shutdown-hook.
     */
    private void onExit(){
        persistRepository();
        System.exit(0);
    }
    /**
     * This method is called to hold a output screen after processing the requested task 
     * and wait for any char input to continue the menu.
     */    
    public void pressAnyKeyToCOntinue(){
        try {
            System.out.println("Press any key to continue...");
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method sleep a thread for 10ms.
     */
    private void delay(){
        try{
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void persistRepository() {
        serializes("expenses.ser", repo.expList);
        serializes("categories.ser", repo.catList);
    }
    
    public void serializes(String file, Object obj){
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);//store expense list in file
            
            //use-finally block - TODO
            oos.close();
            fos.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    } 

    public Object deserialize(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject(); //deserialize
            return obj;
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("No existing data present.");
            return null;
        }
    }
    
    private void restoreRepository() {
        List<Expense> expList = (List<Expense>) deserialize("expenses.ser");
        List<Category> catList = (List<Category>) deserialize("categories.ser");
        if(expList != null){
            //set existing expense in repository
            repo.expList = expList;
        }
        if(catList != null){
            //set existing expense in repository
            repo.catList = catList;
        }
    }
}
