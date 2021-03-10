package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.IngredientRepository;
import com.example.sprotte.datahandling.product.repository.ProductRepository;
import com.example.sprotte.dto.product.SaveIngredientDto;
import com.example.sprotte.entity.Ingredient;
import com.example.sprotte.entity.Product;
import com.example.sprotte.errorhandling.product.IngredientNotFoundException;
import com.example.sprotte.errorhandling.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        if(ingredients.size() == 0)
            throw new IngredientNotFoundException(ResponseMessageConstants.INGREDIENT_NOT_FOUND);

        return ingredients;
    }

    @Override
    public Ingredient saveIngredient(SaveIngredientDto dto) {
        if (dto == null)
            throw new RuntimeException(ResponseMessageConstants.INGREDIENT_IS_EMPTY);

        return ingredientRepository.save(mapSaveIngredientDtoToIngredient(dto));
    }

    @Override
    public Ingredient findIngredientById(Long ingredientId) {
        return findById(ingredientId);
    }

    @Override
    public Ingredient updateIngredient(SaveIngredientDto dto) {
        if (dto == null)
            throw new RuntimeException(ResponseMessageConstants.INGREDIENT_IS_EMPTY);

        return ingredientRepository.save(mapSaveIngredientDtoToIngredient(dto));
    }

    @Override
    public Ingredient updateIngredientQuantity(Long ingredientId, double quantity) {
        Ingredient ingredient = findById(ingredientId);

        ingredient.setQuantity(quantity);

        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredientUnitOfMeasure(Long ingredientId, String unitOfMeasure) {
        Ingredient ingredient = findById(ingredientId);

        ingredient.setUnitOfMeasure(unitOfMeasure);

        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateProductInIngredient(Long ingredientId, Long productId) {
        Ingredient ingredient = findById(ingredientId);
        Product product = findProductById(productId);

        ingredient.setProduct(product);

        return ingredientRepository.save(ingredient);
    }

    @Override
    public String deleteIngredientById(Long ingredientId) {
        Ingredient ingredient = findById(ingredientId);

        ingredientRepository.deleteById(ingredientId);

        return ResponseMessageConstants.INGREDIENT_SUCCESSFULLY_DELETE;
    }

    Product findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ProductNotFoundException(ResponseMessageConstants.PRODUCT_NOT_FOUND);

        return product;
    }

    Ingredient findById(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (ingredient == null)
            throw new IngredientNotFoundException(ResponseMessageConstants.INGREDIENT_NOT_FOUND);

        return ingredient;
    }

    public Ingredient mapSaveIngredientDtoToIngredient(SaveIngredientDto dto) {
        Ingredient ingredient = new Ingredient();

        Product product = findProductById(dto.getProductId());

        ingredient.setProduct(product);
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setUnitOfMeasure(dto.getUnitOfMeasure());

        return ingredient;
    }
}
