package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이터와 테스트를 함께 실행
@Transactional //테스트 후 테스트케이스 들을 rollback 시켜주기 때문에 DB에 데이터가 남지 않고 다음 테스트에 영향X
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given (상황이 주어져서)
        Member member = new Member();
        member.setName("spring");

        // when (실행했을 때)
        Long saveId = memberService.join(member);

        // then (결과가 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given (상황이 주어져서)
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when (실행했을 때)
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //                                     ↑ 이 예외가 발생하는 것을 기대함    ↑ 이 로직이 실행이 되었을 때,
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail(); // 예외가 발생해야 하는데 발생하지 않음.
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then (결과가 나와야 함)

    }

}