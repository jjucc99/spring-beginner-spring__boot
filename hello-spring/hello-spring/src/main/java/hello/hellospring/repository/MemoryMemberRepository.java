package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        //리턴 값으로 Optional<>이 반환이 된다. 람다식을 사용하여 반복문을 간편하게 사용했다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //colletion Framwork의 생성자에 colllcetion을 채워 넣어주면 파라미터를 토대로 colletion이 만들어 진다.
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }
}
