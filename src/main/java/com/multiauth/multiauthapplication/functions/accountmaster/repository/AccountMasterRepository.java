package com.multiauth.multiauthapplication.functions.accountmaster.repository;

import com.multiauth.multiauthapplication.model.AccountMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountMasterRepository extends JpaRepository<AccountMaster, Long> {

    @Query(value = "Select * FROM AccountMaster " +
            "WHERE email =:email AND password =:password", nativeQuery = true)
    Optional<AccountMaster> validateAccountMaster(@Param("email") String email, @Param("password") String password);


    @Query(value = "Select * FROM AccountMaster " +
            "WHERE email =:email", nativeQuery = true)
    Optional<AccountMaster> findAccountByEmail(@Param("email") String email);
}
