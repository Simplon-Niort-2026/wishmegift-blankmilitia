package co.simplon.wishmegift.repository;


import co.simplon.wishmegift.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Long> {

    List<Share> findByUserId(Long userId);

    List<Share> findByWishlistId(Long wishlistId);

    boolean existsByWishlistIdAndUserId(Long wishlistId, Long userId);

}
