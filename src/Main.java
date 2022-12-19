import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        YearlyReport year = new YearlyReport("resources/y.2021.csv");
        MonthlyReport month1 = new MonthlyReport("resources/m.202101.csv");
        MonthlyReport month2= new MonthlyReport("resources/m.202102.csv");
        MonthlyReport month3= new MonthlyReport("resources/m.202103.csv");
        List<MonthlyReport> allMonth = new ArrayList<>();
        Cheking check = new Cheking();

        while(true){
            printMenu();
            //
            int command = scanner.nextInt();
            if(command == 1){
                allMonth.add(month1);
                allMonth.add(month2);
                allMonth.add(month3);
            }
            else if(command == 2){
                year = new YearlyReport("resources/y.2021.csv");
            }
            else if(command == 3){
                check.checkAllMonth(year,allMonth);
            }
            else if(command == 4){
                System.out.println("Выберете месяц, за который вывести отчет: январь; февраль; март;");
                String scMonth = scanner.next();//месяц, который введет пользователь

                 if(scMonth.equals("январь")){
                    System.out.println("Отчет за " + scMonth + ":");
                    month1.printResults();
                }
                else if(scMonth.equals("февраль")){
                    System.out.println("Отчет за " + scMonth + ":");
                    month2.printResults();
                }
                else if(scMonth.equals("март")){
                    System.out.println("Отчет за " + scMonth + ":");
                    month3.printResults();
                }
                else System.out.println("Отчета по такому месяцу нет");
            }
            else if(command == 5){
                System.out.println("Выберете год, за который вывести отчет: 2021;");
                int scYear = scanner.nextInt();//год, который введет пользователь
                if(scYear == 2021){
                    System.out.println("отчет за " + scYear + "год:");
                    year.printResult();
                }
            }
            else if(command == 0){
                System.out.println("Выход");
                break;
            }
            else {
                System.out.println("Извините, такой команды нет.");
            }
        }
    }
    public static void printMenu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход из программы");
    }
}

