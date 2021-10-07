package hello.hellospring.service;

import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void AfterEach(){
        memoryMemberRepository.clear();
    }


    @Test
    void 회원가입() {
        //given => 무엇이 주어지는 지
        Member member = new Member();
        member.setName("hello");

        //whew => 어떠한 상황을 가정하는 지
        long saveId = memberService.join(member);

        //then => 원하는 결과물
        //Opitonal 을 부수기 위해 get 메소드가 사용되었다.
        //.findOne 메소드는 리턴으로 Optional을 주고 그 안에  Member가 있다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void 중복_회원_가입(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when memberService.join이 중복된 상황
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}