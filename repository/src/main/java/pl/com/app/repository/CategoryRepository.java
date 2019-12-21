package pl.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.app.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
