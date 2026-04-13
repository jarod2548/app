package org.app.Account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDBO, UUID>
{
    Optional<UserDBO> findByUsername(String username);
}
