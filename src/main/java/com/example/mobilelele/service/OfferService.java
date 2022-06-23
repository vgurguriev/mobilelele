package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.AddOfferDTO;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.Offer;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.mapper.OfferMapper;
import com.example.mobilelele.repository.ModelRepository;
import com.example.mobilelele.repository.OfferRepository;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.user.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferMapper offerMapper;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;

    public OfferService(OfferMapper offerMapper, OfferRepository offerRepository, UserRepository userRepository, ModelRepository modelRepository, CurrentUser currentUser) {
        this.offerMapper = offerMapper;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer newOffer = offerMapper.offerDtoToOfferEntity(addOfferDTO);

        User seller = this.userRepository
                .findByEmail(currentUser.getEmail()).orElseThrow();

        Model model = this.modelRepository
                .findById(addOfferDTO.getModelId()).orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

}
