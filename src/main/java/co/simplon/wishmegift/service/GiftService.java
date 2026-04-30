package co.simplon.wishmegift.service;

import co.simplon.wishmegift.dto.GiftDTO;
import co.simplon.wishmegift.entity.Gift;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface GiftService {


    List<GiftDTO> getAll();

    public GiftDTO getGiftById(Long id);

    public GiftDTO save(Gift gift);

    public GiftDTO updateGift(Gift gift);

    public void deleteGift(Long id);
}
