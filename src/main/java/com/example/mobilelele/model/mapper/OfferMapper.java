package com.example.mobilelele.model.mapper;

import com.example.mobilelele.model.dto.AddOfferDTO;
import com.example.mobilelele.model.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer offerDtoToOfferEntity(AddOfferDTO addOfferDTO);
}
