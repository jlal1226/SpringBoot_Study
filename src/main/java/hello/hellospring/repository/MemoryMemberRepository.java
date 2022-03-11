package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서 동시성 문제 발생
    private static long sequence = 0L; // 실무에선 동시성 문제 고려해서 atom long 씀

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있으면..
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 값이 없으면 Optional에 null이 포함되서 반환
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member로 이루어진 list 반환
    }
    // 메모리를 지움
    public void clearStore() {
        store.clear();
    }
}
