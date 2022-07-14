package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.AddOfferDTO;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.mapper.OfferMapper;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public OfferService(OfferMapper offerMapper,
                        OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        Offer newOffer = offerMapper.offerDtoToOfferEntity(addOfferDTO);

        User seller = this.userRepository
                .findByEmail(userDetails.getUsername()).orElseThrow();

        Model model = this.modelRepository
                .findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

}
