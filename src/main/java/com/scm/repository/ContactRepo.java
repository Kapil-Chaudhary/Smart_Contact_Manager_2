package com.scm.repository;

import com.scm.entities.Contact;
import com.scm.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // ----------------------     Custom Finder method ---------------------------

    // find the contacts by user
    Page<Contact> findByUser(User user, Pageable pageable);

    void delete(Contact contact);
    void deleteById(String id);

    // Example: Custom Query
    @Query("DELETE FROM Contact c WHERE c.id = :id")
    @Modifying
    @Transactional
    void deleteContactById(@Param("id") String id);


    // ----------------- Custom query methods ------------------------

    // --- JPQL / JPA ----
    @Query("Select c from Contact c where c.user.userId = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);


    // -- search contact methods
    Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword, Pageable pageable);
}
