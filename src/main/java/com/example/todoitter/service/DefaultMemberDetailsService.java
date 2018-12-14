package com.example.todoitter.service;

import com.example.todoitter.entity.Member;
import com.example.todoitter.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultMemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findOneByUsername(username);
        if(member == null){
            throw new UsernameNotFoundException("ないぜ");
        }
        return member;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Member createMember(Member member, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.setPassword(encodedPassword);
        return memberRepository.save(member);
    }
}
