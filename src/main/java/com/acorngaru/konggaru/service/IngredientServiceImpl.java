package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.IngredientMapper;
import com.acorngaru.konggaru.model.Ingredient;
import com.acorngaru.konggaru.model.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("ingredientService")
@Transactional(rollbackFor = Exception.class)
public class IngredientServiceImpl implements IngredientService {
    private final IngredientMapper ingredientMapper;

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientMapper.allIngredient();
    }

    @Override
    public Page<Ingredient> findIngredients(int pageNo, int rows, String searchTerm) throws Exception {
        Page<Ingredient> ingredientPage = new Page<>();

        ingredientPage.process(
                rows,
                pageNo,
                ingredientMapper.countIngredientByName(searchTerm),
                ingredientMapper.searchIngredient(
                        (pageNo - 1) * rows + 1,
                        pageNo * rows,
                        searchTerm
                )
        );

        return ingredientPage;
    }

    @Override
    public void update(Ingredient ingredient) {
        ingredientMapper.updateIngredient(ingredient);
    }

    @Override
    public void create(Ingredient ingredient) {
        ingredientMapper.create(ingredient);
    }

    @Override
    public void deleteIngredientById(int id) {
        ingredientMapper.ingredientDel(id);
    }

    @Override
    public void deleteIngredientsById(List<Integer> ids) {
        ingredientMapper.ingredientDelAll(ids);
    }

    @Override
    public List<Ingredient> allIngredient() {return ingredientMapper.allIngredient();}
}
