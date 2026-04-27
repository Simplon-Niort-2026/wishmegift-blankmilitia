package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;

    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    @Override
    public Gift getGiftById(Long id) {
        return giftRepository.findById(id).orElse(null);
    }

    @Override
    public Gift save(Gift gift) {
        return giftRepository.save(gift);
    }

}
