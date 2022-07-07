package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 상속받고 있으면 구현체를 자동으로 생성해 자동으로 스프링 빈에 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JPQL: select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
