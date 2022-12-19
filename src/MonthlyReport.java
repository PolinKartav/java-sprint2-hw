import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MonthlyReport {

    String strmaxExpense = "";//самая большая трата
    String strmaxProfit = "";//самый прибыльный товар
    int maxProfit = 0;//сумма самого прибыльного товара
    int maxExpense = 0;//сумма максимальной траты

    int sumOfMonthExp = 0;
    int sumOfMonthPrf = 0;

    MonthlyReport()
    {

    }
    MonthlyReport(String path)
    {
        sumOfMonthExp = sumOfMonthExpense(path);
        sumOfMonthPrf = sumOfMonthProfit(path);
        findMaxProfit(path);
        findMaxExpense(path);
    }

        List<String> readFileContents (String path){
            try {
                List<String>list = Files.readAllLines(Path.of(path));
                return Files.readAllLines(Path.of(path));
            }
            catch (IOException e) {
                System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
                return Collections.emptyList();
            }
        }

        //метод для переделывания списка строк в список массива строк
        List<String[]> changeToString (String path) {

            List<String> list = readFileContents(path);
            List<String[]> listOfMas = new ArrayList<>();

            for(String st: list){
                listOfMas.add(st.split(","));
            }
           return  listOfMas;
        }

        //медот для расчета максимальной прибыли
        void findMaxProfit(String path){
            List<String[]> list = changeToString(path);
            maxProfit = 0;
            strmaxProfit = "";
            int max;

            for(String[] st: list){
                if (st[1].equals("FALSE")) {
                    max = Integer.parseInt(st[2])* Integer.parseInt(st[3]);
                    if(max>maxProfit){
                        maxProfit = max;
                        strmaxProfit = st[0];
                    }
                }
            }
        }
        //метод для выявления самой большой траты
        void findMaxExpense(String path){
            List<String[]> list = changeToString(path);
            int max;
            maxExpense = 0;
            strmaxExpense = "";

            for(String[] st: list){
                if (st[1].equals("TRUE")) {
                    max = Integer.parseInt(st[3]);
                    if(max > maxExpense){
                        maxExpense = max;
                        strmaxExpense = st[0];
                    }
                }
            }
        }

        int sumOfMonthProfit(String path){
            List<String[]> list = changeToString(path);
            int profit = 0;
            for (String[] str: list){
                if (str[1].equals("FALSE"))
                {
                    profit = profit + Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            return profit;
        }

        int sumOfMonthExpense(String path){
            List<String[]> list = changeToString(path);
            int expense = 0;
            for (String[] str: list){
                if (str[1].equals("TRUE"))
                {
                    expense = expense + Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            return expense;
        }

        void printResults(){
            System.out.println( strmaxProfit + " - самый прибыльный товар в этом месяце. Прибыль составила: " + maxProfit);
            System.out.println(strmaxExpense + " - самый неприбыльный товар. Сумма расходов составила: " + maxExpense);
        }
}
