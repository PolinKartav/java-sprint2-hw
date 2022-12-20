import java.util.List;
public class Cheking {

    boolean checkAllMonth(YearlyReport year, List<MonthlyReport> allMonth) {
        boolean result = true;
        for (int i = 0; i < 3; i++)
        {
            if (allMonth.get(i).sumOfMonthPrf != year.monthOfYear.get(i).sumOfMonthPrf)
            {
                System.out.println("В "+ (i+1) + " месяце допущена ошибка в доходах");
                result = false;
            }
            if (allMonth.get(i).sumOfMonthExp != year.monthOfYear.get(i).sumOfMonthExp)
            {
                System.out.println("В "+ (i+1) + " месяце допущена ошибка в тратах");
                result = false;
            }
        }
        return result;
    }
}
