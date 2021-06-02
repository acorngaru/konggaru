package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface DashboardMapper {

    public List<Income> allIncome();

    public int thisMonth();

    public int updateMonthlyIncome(Income income);

    public List<Rank> getRank() ;
}