package br.com.postech.techchallenge.orderapi.models;

import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ComboTest {

    @Test
    void addComboShouldThrowExceptionWhenCategoryIsDifference(){
        Product product = new Product();
        product.setProductCategory(ProductCategory.LANCHE);
        Addon addon = new Addon();
        addon.setProductCategory(ProductCategory.ACOMPANHAMENTO);
        Combo combo = new Combo(product);

        assertThrows(IllegalArgumentException.class, ()-> combo.addAddon(addon));
        assertFalse(combo.getAddons().contains(addon));
    }

}