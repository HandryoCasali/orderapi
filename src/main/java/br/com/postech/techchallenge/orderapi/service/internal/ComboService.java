package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.models.Combo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ComboService {

    private final ProductService productService;

    private final AddonService addonService;

    public Combo create(CreateComboDto comboDto) throws EntityNotFoundException {

        var product = productService.findById(comboDto.getProductId());

        Combo combo = new Combo(product);

        for (Long id : comboDto.getAddonsId()) {
            combo.addAddon(addonService.findById(id));
        }

        return combo;
    }
}
