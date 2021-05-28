package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;

import java.util.List;

public interface DashboardMapper {

    public List<Income> allIncome();

    public int thisMonth();

    public int updateMonthlyIncome(Income income);

    public List<Rank> getRank() ;
}