package transporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import transporter.entity.Driver;
import transporter.entity.User;

public interface DriverRepository extends JpaRepository<Driver, Long>{

	Driver findOneByLogin(String login);
}
