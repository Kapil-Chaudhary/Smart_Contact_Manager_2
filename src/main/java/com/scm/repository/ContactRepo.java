package com.scm.repository;

import com.scm.entities.Contact;
import com.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // ----------------------     Custom Finder method ---------------------------

    // find the contacts by user
    List<Contact> findByUser(User user);


    // ----------------- Custom query methods ------------------------

    // --- JPQL / JPA ----
    @Query("Select c from Contact c where c.user.userId = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

}
