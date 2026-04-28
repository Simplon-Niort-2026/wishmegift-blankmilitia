package co.simplon.wishmegift.service;


import co.simplon.wishmegift.entity.BookGift;

import java.util.List;

public interface BookGiftService {

    BookGift reserve(BookGift bookGift);

    boolean isAlreadyReserved(Long giftId);




}
