import java.util.List;
public class Cheking {

    void checkAllMonth(YearlyReport year, List<MonthlyReport> allMonth)
    {
        for (int i = 0; i < 3; i++)
        {
            if (allMonth.get(i).sumOfMonthPrf != year.monthOfYear.get(i).sumOfMonthPrf)
            {
                System.out.println("В "+ i + " месяце допущена ошибка в доходах");
            }
            if (allMonth.get(i).sumOfMonthExp != year.monthOfYear.get(i).sumOfMonthExp)
            {
                System.out.println("В "+ i + " месяце допущена ошибка в тратах");
            }
        }
    }
}
