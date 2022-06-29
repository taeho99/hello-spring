package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//컴포넌트 스캔 방식 (Controller, Service, Repository 모두 동일방식)
@Controller
public class MemberController {
    private final MemberService memberService; // 스프링 컨테이너에 등록해 공유하여 사용

    @Autowired // 의존관계 주입(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 데이터 바인딩 방식에는 2가지가 있다.
    // 1. @RequestParam 으로 바인딩
    // 2. 객체를 바로 바인딩하는 방식(아래와 같음)
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
