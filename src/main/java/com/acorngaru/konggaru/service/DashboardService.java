package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.dao.DashboardDao;
import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardService {
    @Autowired
    DashboardDao dashboardDao;

    public List<Income> allIncome() {
        List<Income> incomes = dashboardDao.allIncome();
        return incomes;
    }

    public int thisMonth() {
        int thisMonthIncome = dashboardDao.thisMonth();
        return thisMonthIncome;
    }

    public int updateMonthlyIncome(Income income) {
        int updateDone = dashboardDao.updateMonthlyIncome(income);
        return updateDone;
    }

    public List<Rank> getRank() {
        List<Rank> r = dashboardDao.getRank();
        return r;
    }
}
