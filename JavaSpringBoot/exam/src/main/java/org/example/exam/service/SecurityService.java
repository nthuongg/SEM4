//package org.example.exam.service;
//
//import ch.qos.logback.core.LayoutBase;
//import org.example.exam.entity.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SecurityService {
//    @Autowired
//    private MemberService memberService;
//
//    public boolean hasAdminRole() {
//        return hasRole("ADMIN");
//    }
//
//    public boolean hasSaleRole() {
//        return hasRole("SALE");
//    }
//
//    public boolean hasRole(String role) {
//        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//        Member member = memberService.findByUserId(userId).orElse(null);
//        return member != null && member.getRoles().stream().anyMatch(r -> role.equals(r.getRole()));
//    }
//}
