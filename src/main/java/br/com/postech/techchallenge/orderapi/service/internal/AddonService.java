package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IAddonMapper;
import br.com.postech.techchallenge.orderapi.models.Addon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddonService {

    private final IAddonJpaRepository addonRepository;
    private final IAddonMapper mapper;

    public void create(CreateAddonDto createAddon) {

        Addon addon = mapper.toAddon(createAddon);

        addonRepository.save(addon);
    }

    public List<AddonDto> findAllByProductCategory(String productCategoryName) {

        var addonsList = addonRepository.findAllByIsActiveTrueAndProductCategory(ProductCategory.fromDisplayName(productCategoryName));

        return mapper.toAddonListDto(addonsList);
    }

    public Addon findById(Long id) throws EntityNotFoundException {

        return addonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The addon was not found."));
    }

    public void update(Long id, UpdateAddonDto dto) throws EntityNotFoundException {

        Addon addon = findById(id);

        addon.update(dto.getName(), dto.getPrice(), dto.getDiscountPercent());

        addonRepository.save(addon);
    }

    public void delete(Long id) throws EntityNotFoundException {

        if (!addonRepository.existsById(id))
            throw new EntityNotFoundException("The addon was not found.");

        addonRepository.deleteById(id);
    }
}
