package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.BrandDTO;
import com.example.mobilelele.model.dto.ModelDTO;
import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDTO> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .collect(Collectors.toList());
    }

    private BrandDTO mapBrand(Brand brand) {
        List<ModelDTO> models = brand
                .getModels()
                .stream()
                .map(this::mapModel)
                .toList();

        return new BrandDTO()
                .setModels(models)
                .setName(brand.getName());
    }

    private ModelDTO mapModel(Model model) {
        return new ModelDTO()
                .setId(model.getId())
                .setName(model.getName());
    }
}
