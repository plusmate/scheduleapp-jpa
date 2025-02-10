package com.schedulegroup.scheduleapp.repository;

import com.schedulegroup.scheduleapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {
}
