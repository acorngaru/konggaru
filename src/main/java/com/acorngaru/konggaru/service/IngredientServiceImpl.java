package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.IngredientMapper;
import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("ingredientService")
@Transactional(rollbackFor = Exception.class)
public class IngredientServiceImpl implements IngredientService {
    private final IngredientMapper ingredientMapper;

    @Override
    public Page<Ingredient> findIngredients(int pageNo, int rows, String searchTerm) throws Exception {
        Page<Ingredient> ingredientPage = new Page<>();

        ingredientPage.process(
                rows,
                pageNo,
                ingredientMapper.countIngredients(searchTerm),
                ingredientMapper.findIngredients(pageNo, rows, searchTerm)
        );

        return ingredientPage;
    }
}
