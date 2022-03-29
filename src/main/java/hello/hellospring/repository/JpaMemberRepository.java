package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // 스프링부트가 자동으로 EntityManager 생성함
    private final EntityManager em;
    // 주입 받기만하면 됌
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // 알아서 Insert
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK 기반으로 찾는게 아니기 때문에, jpql을 작성해야됌됌
   @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체(Entity)를 대상으로 쿼리를 날림
        // m : 객체 자체를 select
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
