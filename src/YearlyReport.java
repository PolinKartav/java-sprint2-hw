
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.IOException;
public class YearlyReport {

    HashMap<String, Integer>  ProfitOfEachMonth = new HashMap<>();
    int middleProfitOfMonth = 0;
    int middleExpenseOfMonth = 0;
    List<MonthlyReport> monthOfYear = new ArrayList<>();


    YearlyReport()
    {

    }
//
    YearlyReport(String path)
    {
        ProfitOfEachMonth = findProfitOfEachMonth(path);
        middleExpenseOfMonth = findMidleEx(path);
        middleProfitOfMonth = findMidlePr(path);
        statOfEachMonth(path);
    }
    List<String> readFileContents(String path){
        try {
            return Files.readAllLines(Path.of(path));

        } catch (IOException e){
            System.out.println("Невозможно прочитать файл с годовом отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();

        }
    }

    List<String[]> changeToString (String path) {

        List<String> list = readFileContents(path);
        List<String[]> listOfMas = new ArrayList<>();

        for(String st: list){
            listOfMas.add(st.split(","));
        }
        return  listOfMas;
    }

//сооздание списка трат и доходов по месяцам
    void statOfEachMonth(String path) {
        List<String[]> list = changeToString(path);
        for(int i = 1; i <= 3; i++){
            MonthlyReport month = new MonthlyReport();
            for(String[] st: list){
                if(st[0].equals("0" + i)){
                    if(st[2].equals("false")){
                        month.sumOfMonthPrf = Integer.parseInt(st[1]);
                    }
                    else if(st[2].equals("true")){
                        month.sumOfMonthExp = Integer.parseInt(st[1]);
                    }
                }
            }
            monthOfYear.add(month);
        }
    }
    HashMap<String, Integer>  findProfitOfEachMonth(String path){
        HashMap<String, Integer> monthAndProfit = new HashMap<>();
        String nameMonth ="";
        int expense = 0;//трата = true
        int incom = 0;//доход = false
        int profit = 0;//прибыль
        List<String[]> list = changeToString(path);
        for(int i = 1; i <= 3; i++){
            for(String[] st: list){
                if(st[0].equals("0" + i)){

                    if(st[2].equals("false")){
                        incom = Integer.parseInt(st[1]);
                    }
                    else if(st[2].equals("true")){
                        expense = Integer.parseInt(st[1]);
                    }nameMonth = st[0];
                }
            }
            profit = incom - expense;
            monthAndProfit.put(nameMonth, profit);
        }
        return monthAndProfit;
    }
    //находим средние доходы и расходы
    int findMidlePr(String path){
        int midPr = 0;//средний доход
        int count = 0;
        List<String[]> list = changeToString(path);

            for (String[] st : list) {
                if (st[2].equals("false")) {
                    midPr = midPr + Integer.parseInt(st[1]);
                    count++;
                }
            }
            midPr = midPr / count;

        return midPr;

    }

    //печать отчета за год
    int findMidleEx(String path){
        int midEx = 0;
        int count = 0;
        List<String[]> list = changeToString(path);
            for (String[] st : list) {
                if (st[2].equals("true")) {
                midEx = midEx + Integer.parseInt(st[1]);
                count ++;
                }
            }
            midEx = midEx / count;
        return midEx;
    }

    void printResult(){
        for(String key: ProfitOfEachMonth.keySet()){
            System.out.println(key + ": " + ProfitOfEachMonth.get(key));
        }
        System.out.println("Средний расход за все месяцы: " + middleExpenseOfMonth);
        System.out.println("Средний доход за все месяцы:" + middleProfitOfMonth);
    }
}
