package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService s;

    @GetMapping
    public String showHome() {
        return "dashboard/dashboard";
    }

    @ResponseBody
    @GetMapping("/chart")
    public List<Income> chart(
    )   {
        List<Income> incomes = s.allIncome();
        log.info(String.valueOf(incomes));

        return incomes;
    }
    @ResponseBody
    @GetMapping("/product_rank")
    public HashMap<String, String> productRank(HttpServletResponse res) throws IOException{
        HashMap<String,String> data = s.getRank();

        return data;

    }
    @ResponseBody
    @GetMapping ("/update_income")
    public HashMap<String, String> updateMonthlyIncome(){
        int monthly = s.thisMonth();

        HashMap<String,String> data = new HashMap<>();
        String name ="incomes";
        data.put(name,Integer.toString(monthly));

        Calendar cal = Calendar.getInstance();
        String month = Integer.toString(cal.get(Calendar.MONTH)+1);

        if (month.length() ==1 )
            month="0"+month;

        Income income = new Income(month,monthly);
        s.updateMonthlyIncome(income);

        return data;
    }

}
