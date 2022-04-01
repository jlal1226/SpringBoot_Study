package hello.hellospring;

import hello.hellospring.aop.TimeTraceAOP;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 직접 스프링 빈 등록 하기
@Configuration
public class SpringConfig {
/*    private DataSource dataSource;

    // 스프링이 dataSource를 만들어서 주입해줌
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

/*  // JPA
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    // spring data JPA
    private final MemberRepository memberRepository;
    // 스프링 빈에 등록된 실제 구현체가 주입됌
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        // spring bean에 등록되어 있는 memberRepository를 넣어줌
        // return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

/*    @Bean
    public MemberRepository memberRepository() {
        // 구현체를 return
        // return new JdbcMemberRepository(dataSource);

        return new JpaMemberRepository(em);
    }*/

/*    @Bean
    public TimeTraceAOP timeTraceAOP() {
        return new TimeTraceAOP();
    }*/
}
