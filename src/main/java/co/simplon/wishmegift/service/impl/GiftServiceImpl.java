package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.mapper.GiftMapper;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;

    public GiftServiceImpl(GiftRepository giftRepository, GiftMapper giftMapper) {
        this.giftRepository = giftRepository;
        this.giftMapper = giftMapper;
    }

    @Override
    public List<GiftDTO> getAll() {
        return giftRepository.findAll().stream()
                .map(giftMapper::toDTO)
                .toList();
    }

    @Override
    public GiftDTO getGiftById(Long id) {
        Gift gift = giftRepository.findById(id).orElse(null);
        return giftMapper.toDTO(gift);
    }

    @Override
    public GiftDTO save(Gift gift) {
        return giftMapper.toDTO(giftRepository.save(gift));
    }

    @Override
    public GiftDTO updateGift(Gift gift) {
        if (gift == null) {
            return null;
        }

        gift.setName(gift.getName());
        gift.setDescription(gift.getDescription());
        gift.setLink(gift.getLink());
        gift.setWishLevel(gift.getWishLevel());
        gift.setPrice(gift.getPrice());
        gift.setBook(gift.getBook());
        gift.setWishlist(gift.getWishlist());
        gift.setUser(gift.getUser());
        return giftMapper.toDTO(giftRepository.save(gift));
    }

    @Override
    public void deleteGift(Long id){
       giftRepository.deleteById(id);
    }

}
