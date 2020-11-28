package com.tae.tae.controller;

import com.tae.tae.dto.member.Member;
import com.tae.tae.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id) {
        Member member = null;

        try{
            member = memberService.findOne(id);
        } catch (NoSuchElementException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(member, HttpStatus.OK);
    }

    @GetMapping(value = "/members_page")
    public ResponseEntity list(@PageableDefault(size = 12, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {


        return new ResponseEntity(HttpStatus.OK);
    }
}
