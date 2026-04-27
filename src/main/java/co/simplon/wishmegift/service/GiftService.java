package co.simplon.wishmegift.service;

import co.simplon.wishmegift.entity.Gift;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface GiftService {

    public List<Gift> getAllGifts();

    public Gift getGiftById(Long id);

    public Gift save(Gift gift);
}
