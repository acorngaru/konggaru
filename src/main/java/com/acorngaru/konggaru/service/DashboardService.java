package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Income;
import com.acorngaru.konggaru.model.Rank;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public class DashboardService  {
    @Autowired
    private SqlSessionTemplate sqlSession;

    protected static final String NAMESPACE = "com.acorngaru.konggaru.mapper.DashboardMapper";
    @Transactional(readOnly = true)
    public List<Income> allIncome() {
        List<Income> incomes =sqlSession.selectList(NAMESPACE+".AllIncome");
        return incomes;
    }
    @Transactional(readOnly = true)
    public int thisMonth() {
        int thisMonthIncome = sqlSession.selectOne(NAMESPACE+".ThisMonth");
        return thisMonthIncome;
    }
    @Transactional
    public int updateMonthlyIncome(Income income) {
        int updateDone = sqlSession.update(NAMESPACE+".updateMonthlyIncome",income);
        return updateDone;
    }
    @Transactional
    public HashMap<String, String> getRank() {
        List<Rank> r = sqlSession.selectList(NAMESPACE+".RankIncome");
        HashMap<String, String> data = new HashMap<>();
        for (Rank rank : r)
            data.put(rank.getName(),rank.getImage_url());

        return data;
    }
}
