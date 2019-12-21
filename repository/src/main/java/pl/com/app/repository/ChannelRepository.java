package pl.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.app.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
