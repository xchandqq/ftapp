/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchan.ftapp.repo;

import com.xchan.ftapp.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Christian
 */
@Repository
public interface UserRepo extends JpaRepository<UserInformation, Long>{
    
}
