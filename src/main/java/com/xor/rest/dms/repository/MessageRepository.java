package com.xor.rest.dms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xor.rest.dms.model.AbstractMessage;

@Repository
public interface MessageRepository extends JpaRepository<AbstractMessage, Long>{

}
