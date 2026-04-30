package co.simplon.wishmegift.service;


import co.simplon.wishmegift.entity.BookGift;

public interface BookGiftService {

    BookGift reserve(BookGift bookGift);

    boolean isAlreadyReserved(Long giftId);


}
