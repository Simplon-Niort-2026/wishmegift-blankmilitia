package co.simplon.wishmegift.controller;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.service.GiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public List<Gift> getAllGifts() {
        return giftService.getAllGifts();
    }

    @GetMapping("/{id}")
    public Gift getGiftById(@PathVariable Long id) {
        return giftService.getGiftById(id);
    }

    @PostMapping
    public ResponseEntity<Gift> addGiftToList(@RequestBody Gift gift) {
        return ResponseEntity.status(HttpStatus.CREATED).body(giftService.save(gift));
    }

    @PutMapping("/{id}")
    public Gift updateGift(@PathVariable Long id, @RequestBody Gift gift) {
        Gift existingGift = giftService.getGiftById(id);
        return giftService.updateGift(existingGift, gift);
    }

    @DeleteMapping("/{id}")
    public void deleteGiftById(@PathVariable Long id) {
        giftService.deleteGift(id);
    }
}