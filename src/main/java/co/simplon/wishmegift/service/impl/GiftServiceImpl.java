package co.simplon.wishmegift.service.impl;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.Wishlist;
import co.simplon.wishmegift.repository.GiftRepository;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Override
    public Gift updateGift(Gift gift) {
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
        return giftRepository.save(gift);
    }

}
