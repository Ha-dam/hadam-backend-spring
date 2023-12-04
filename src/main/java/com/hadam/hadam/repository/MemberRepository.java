package com.hadam.hadam.repository;

import com.hadam.hadam.domain.Member;
import com.hadam.hadam.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByIdentifier(String identifier);

}
