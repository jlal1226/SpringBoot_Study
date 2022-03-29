package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 구현체를 만들어서 spring bean에 알아서 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JPQL : select m from Member m where m.name = ?
    // 규칙이 있음
    @Override
    Optional<Member> findByName(String name); // 인터페이스 구현으로 개발이 끝남
}
