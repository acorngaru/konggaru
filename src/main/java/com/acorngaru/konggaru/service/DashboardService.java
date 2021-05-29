package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.DashboardMapper;
import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DashboardService implements DashboardMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;

    protected static final String NAMESPACE = "com.acorngaru.konggaru.mapper.DashboardMapper";
    @Override
    public List<Income> allIncome() {
        List<Income> incomes =sqlSession.selectList(NAMESPACE+".AllIncome");
        return incomes;
    }
    @Override
    public int thisMonth() {
        int thisMonthIncome = sqlSession.selectOne(NAMESPACE+".ThisMonth");
        return thisMonthIncome;
    }
    @Override
    public int updateMonthlyIncome(Income income) {
        int updateDone = sqlSession.update(NAMESPACE+".updateMonthlyIncome",income);
        return updateDone;
    }
    @Override
    public List<Rank> getRank() {
        List<Rank> r = sqlSession.selectList(NAMESPACE+".RankIncome");
        return r;
    }
}
