/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.repo;

import com.xchan.ftapp.model.Account;
import com.xchan.ftapp.model.UserInformation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
    
    @Query(value = "SELECT MAX(id) FROM account WHERE owner_id = ?1", nativeQuery = true)
    public Long findMaxIdOfAccountByOwner(Long ownerId);
    
    @Query(value = "SELECT * FROM account WHERE name = ?1 AND owner_id = ?2 LIMIT 1", nativeQuery = true)
    public Account findByName(String name, Long ownerId);
    
    public List<Account> findByOwnerAndAssetOrderByDisplayOrderAsc(UserInformation owner, boolean asset);
    public Optional<Account> findByOwnerAndUserAccountId(UserInformation owner, long userAccountId);
    
}
