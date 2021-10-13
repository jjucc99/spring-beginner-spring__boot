package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    //  객체를 생성할 때 구현체를 넣어줘야 하는 관계 => dependence injection ===  DI
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원 가입
    public long join(Member member){
        //회원들의 이름들이 중복되면 저장하지 않는다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //메소드 안의 메소드.

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*
    전체 화원 조희
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId ){
        return memberRepository.findById(memberId);
    }
}
