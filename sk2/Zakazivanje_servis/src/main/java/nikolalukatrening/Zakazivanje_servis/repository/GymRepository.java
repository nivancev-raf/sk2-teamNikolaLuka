package nikolalukatrening.Zakazivanje_servis.repository;

import nikolalukatrening.Zakazivanje_servis.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym,Long> {
}
