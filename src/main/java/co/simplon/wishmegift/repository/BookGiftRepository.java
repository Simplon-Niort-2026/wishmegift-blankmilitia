package co.simplon.wishmegift.repository;

import co.simplon.wishmegift.entity.BookGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookGiftRepository extends JpaRepository<BookGift, Long> {
    boolean existsByGiftId(Long giftId);

    List<BookGift> id(Long id);
}
