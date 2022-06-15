package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // null 대신 Optional로 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByNames(String name);
    List<Member> findAll();

}
