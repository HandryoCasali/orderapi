package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IAddonMapper;
import br.com.postech.techchallenge.orderapi.mapper.IProductCategoryMapper;
import br.com.postech.techchallenge.orderapi.models.Addon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddonServiceTest {

    @InjectMocks
    private AddonService addonService;
    @Mock
    private IAddonJpaRepository repository;
    @Spy
    private IAddonMapper mapper = Mappers.getMapper(IAddonMapper.class);
    @Spy
    private IProductCategoryMapper productCategoryMapper = IProductCategoryMapper.INSTANCE;

    @BeforeEach
     void setup(){
        ReflectionTestUtils.setField(mapper, "iProductCategoryMapper", productCategoryMapper);
    }

    @Test
    void create() {
        CreateAddonDto dto = new CreateAddonDto("Bacon", new BigDecimal(5), ProductCategory.LANCHE, 0.0);
        addonService.create(dto);
        verify(repository, times(1)).save(any(Addon.class));
    }

    @Test
    void findAllByProductCategory() {
        Addon addon = createAddon();
        addon.setId(1L);
        List<Addon> addonEntities = List.of(addon);

        when(repository.findAllByIsActiveTrueAndProductCategory(ProductCategory.LANCHE)).thenReturn(addonEntities);

        var result = addonService.findAllByProductCategory("Lanche");

        assertAll(
                ()-> assertEquals(1, result.size()),
                ()-> assertEquals(1L, result.get(0).getId()),
                ()-> assertEquals("Bacon", result.get(0).getName()),
                ()-> assertEquals(5d, result.get(0).getPrice().doubleValue()),
                ()-> assertEquals(ProductCategory.LANCHE.getDisplayName(), result.get(0).getProductCategory()),
                ()-> assertEquals(0.0, result.get(0).getDiscountPercent())
        );
    }


    @Test
    void findById() throws EntityNotFoundException {
        Addon addonEntity = createAddon();
        when(repository.findById(1L)).thenReturn(Optional.of(addonEntity));

        var result = addonService.findById(1L);

        assertEquals("Bacon", result.getName());
    }

    @Test
    void findByIdWhenThrowException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> addonService.findById(1L));
    }

    @Test
    void update() throws EntityNotFoundException {
        UpdateAddonDto dto = new UpdateAddonDto("Bacon", new BigDecimal(5), ProductCategory.LANCHE, 0.0 );
        Addon addonEntity = createAddon();

        when(repository.findById(1L)).thenReturn(Optional.of(addonEntity));

        addonService.update(1L, dto);

        verify(repository, times(1)).save(any(Addon.class));

    }

    @Test
    void updateWhenThrowException() {
        UpdateAddonDto dto = new UpdateAddonDto("Bacon", new BigDecimal(5), ProductCategory.LANCHE, 0.0 );

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> addonService.update(1L, dto));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void delete() throws EntityNotFoundException {
        when(repository.existsById(1L)).thenReturn(true);
        addonService.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteWhenIdNotExists(){
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, ()-> addonService.delete(1L));
        verify(repository, times(0)).deleteById(1L);
    }

    private static Addon createAddon() {
        return new Addon("Bacon", new BigDecimal(5), 0.0, ProductCategory.LANCHE);
    }
}