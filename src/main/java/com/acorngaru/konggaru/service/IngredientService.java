package com.acorngaru.konggaru.service;


import com.acorngaru.konggaru.mapper.IngredientMapper;
import com.acorngaru.konggaru.model.Ingredient;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public class IngredientService  implements IngredientMapper {
    @Autowired
    private SqlSessionTemplate sqlSession;

    protected static final String NAMESPACE = "com.acorngaru.konggaru.mapper.IngredientMapper";
    @Override
    public List<Ingredient> searchIngredient(HashMap<String, String> map) {
        List<Ingredient> searchlist = sqlSession.selectList(NAMESPACE + ".searchIngredient", map);
        return searchlist;

    }
    @Override
    public List<Ingredient> allIngredient() {
        return sqlSession.selectList(NAMESPACE +".ingredientAllList");
    }
    @Override
    public int countIngredientByName(String name) {
        int amount = sqlSession.selectOne(NAMESPACE +".countIngredientByName",name);
        return amount;
    }
    @Override
    public int ingredientDel(int n) {
        int amount = sqlSession.delete(NAMESPACE+".ingredientDel",n);
        return amount;
    }
    @Override
    public int updateIngredient(Ingredient ingredient) {
        int n = sqlSession.update(NAMESPACE+".updateIngredient",ingredient);
        return n;
    }

    @Override
    public int create(Ingredient ingredient) {
        int n = sqlSession.insert(NAMESPACE+".create",ingredient);
        return n;
    }
    @Override
    public int ingredientDelAll(List<String> arrayList) {
        int n = sqlSession.delete(NAMESPACE+".ingredientDelAll",arrayList);
        return n;
    }

}