# Personal Expense Management Application

## Overview
The Personal Expense Management Application is a Java-based program designed to help users manage their monthly expenses efficiently. It provides functionality to calculate and display the total expenses for each month and offers various date-related utilities for handling and formatting date information.

## Features
- Calculate and display monthly expense totals.
- Convert between string-formatted dates and `Date` objects.
- Extract year and month information from a `Date` object.
- Retrieve the name of a month given its number.

## Installation
1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/personal-expense-management.git
   ```
2. **Navigate to the Project Directory**
   ```bash
   cd personal-expense-management
   ```
3. **Build the Project**
   Ensure you have a Java Development Kit (JDK) installed. Then, build the project using your preferred build tool (e.g., Maven, Gradle, or simply compiling the `.java` files manually).

## Usage
### Running the Application
To run the application, execute the main class. You can do this from your IDE or using the command line:

```bash
java -cp target/personal-expense-management-1.0.jar in.ezeon.MainClass
```

### Key Classes and Methods
#### `ReportService`
The `ReportService` class provides methods to calculate expense reports. The primary method used is `calculateMonthlyTotal`.

#### `DateUtil`
The `DateUtil` class offers various static methods for handling date operations:
- `stringToDate(String dateAsString)`: Converts a string-formatted date to a `Date` object.
- `dateToString(Date date)`: Converts a `Date` object to a string in `dd/MM/yyyy` format.
- `getYearAndMonth(Date date)`: Returns the year and month from a given `Date` in `yyyy, MM` format.
- `getYear(Date date)`: Returns the year for a given `Date`.
- `getMonthName(Integer monthNo)`: Returns the month name for a given month number.

### Example
Below is an example of how to use the `onMonthlyExpenseList` method to display monthly expenses:

```java
public class MainClass {
    private static ReportService reportService = new ReportService();

    public static void main(String[] args) {
        onMonthlyExpenseList();
    }

    /**
     * This method is called from the menu to prepare the monthly expense total.
     * It uses <code>ReportService</code> to calculate the report.
     * The returned result is printed by this method.
     */
    private static void onMonthlyExpenseList() {
        System.out.println("Monthly Expense Total...");
        Map<String, Float> resultMap = reportService.calculateMonthlyTotal();
        Set<String> keys = resultMap.keySet();
        for (String yearMonth : keys) {
            String[] arr = yearMonth.split(",");
            String year = arr[0];
            Integer monthNo = Integer.valueOf(arr[1]);
            String monthName = DateUtil.getMonthName(monthNo);
            System.out.println(year + ", " + monthName + " : " + resultMap.get(yearMonth));
        }
    }
}
```

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any inquiries or issues, please open an issue in the GitHub repository or contact the project maintainer at [your-email@example.com].
