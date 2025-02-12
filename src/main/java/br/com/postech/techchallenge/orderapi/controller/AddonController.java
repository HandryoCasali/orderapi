package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.addon.AddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.orderapi.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.AddonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/addon")
public class AddonController {

    private final AddonService addonService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateAddonDto dto) {
        addonService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddonDto>> listAllByProductCategory(@RequestParam String categoryName) {
        var response = addonService.findAllByProductCategory(categoryName);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddonDto> update(@PathVariable Long id, @RequestBody @Valid UpdateAddonDto dto) throws EntityNotFoundException {
        addonService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        addonService.delete(id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}
