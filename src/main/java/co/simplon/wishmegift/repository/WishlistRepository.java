package co.simplon.wishmegift.repository;


import co.simplon.wishmegift.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Query("SELECT w FROM Wishlist w JOIN FETCH w.user WHERE w.id = :id")
    Optional<Wishlist> findByIdWithUser(@Param("id") Long id);
    

}
