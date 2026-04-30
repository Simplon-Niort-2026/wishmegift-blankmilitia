package co.simplon.wishmegift.controller;


import co.simplon.wishmegift.entity.BookGift;
import co.simplon.wishmegift.service.BookGiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookgifts")
public class BookGiftController {

    private final BookGiftService bookGiftService;

    public BookGiftController(BookGiftService bookGiftService) {
        this.bookGiftService = bookGiftService;

    }

    @PostMapping
    public ResponseEntity<BookGift> addBookGift(@RequestBody BookGift bookGift) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookGiftService.reserve(bookGift));
    }

}
