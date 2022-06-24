package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//컴포넌트 스캔 방식 (Controller, Service, Repository 모두 동일방식)
@Controller
public class MemberController {
    private final MemberService memberService; // 스프링 컨테이너에 등록해 공유하여 사용

    @Autowired // 의존관계 주입(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
