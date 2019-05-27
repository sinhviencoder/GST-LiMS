/**
 * 
 */
package com.lims.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.User;

/**
 * @author Nguyen Van Trinh
 * @Date 19/9/2017
 *
 */
public interface UserRepository extends DataTablesRepository<User, Long> {

	User findByUsername(String username);


}
