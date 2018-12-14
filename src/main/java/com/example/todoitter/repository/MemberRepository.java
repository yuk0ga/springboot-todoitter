package com.example.todoitter.repository;

import com.example.todoitter.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {

    Member findOneByUsername(String username);

}
