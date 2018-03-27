package transporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import transporter.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findById(Long id);

	User findByFirstName(String currentUserName);
	User findOneByLogin(String login);
	User findByEmail(String email);
}
