package org.example.exam.repository;
import org.example.exam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByUserId(String userId);
}
