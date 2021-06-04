package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.DashboardMapper;
import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DashboardService  {

    @Autowired
    DashboardMapper mapper;

    public DashboardService(DashboardMapper mapper){
        this.mapper=mapper;
    }

    protected static final String NAMESPACE = "com.acorngaru.konggaru.mapper.DashboardMapper";
    public List<Income> allIncome() {

        List<Income> incomes =mapper.allIncome();
        return incomes;
    }
    public int thisMonth() {
        int thisMonthIncome = mapper.thisMonth();
        return thisMonthIncome;
    }
    public int updateMonthlyIncome(Income income) {
        int updateDone = mapper.updateMonthlyIncome(income);
        return updateDone;
    }
    public HashMap<String, String> getRank() {
        List<Rank> r = mapper.getRank();
        HashMap<String, String> data = new HashMap<>();
        for (Rank rank : r)
            data.put(rank.getName(),rank.getImage_url());

        return data;
    }
}
