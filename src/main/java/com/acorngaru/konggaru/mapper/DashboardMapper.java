package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface DashboardMapper {

    List<Income> allIncome();

    int thisMonth();

    int updateMonthlyIncome(Income income);

    List<Rank> getRank();
}