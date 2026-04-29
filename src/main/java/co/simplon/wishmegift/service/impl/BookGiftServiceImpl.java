package co.simplon.wishmegift.service.impl;


import co.simplon.wishmegift.entity.BookGift;
import co.simplon.wishmegift.repository.BookGiftRepository;
import co.simplon.wishmegift.service.BookGiftService;
import org.springframework.stereotype.Service;

@Service
public class BookGiftServiceImpl implements BookGiftService {

    private final BookGiftRepository bookGiftRepository;

    public BookGiftServiceImpl(BookGiftRepository bookGiftRepository) {
        this.bookGiftRepository = bookGiftRepository;
    }

    @Override
    public BookGift reserve(BookGift bookGift) {
        if (bookGiftRepository.existsByGiftId(bookGift.getGift().getId() )) {
            throw new RuntimeException("Ce cadeaux et déja réserver");
        }
        return bookGiftRepository.save(bookGift);
    }

    @Override
    public boolean isAlreadyReserved(Long giftId) {

        return bookGiftRepository.existsByGiftId(giftId);
    }
}
