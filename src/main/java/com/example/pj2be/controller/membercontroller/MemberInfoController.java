package com.example.pj2be.controller.membercontroller;

import com.example.pj2be.domain.board.BoardDTO;
import com.example.pj2be.domain.member.MemberDTO;
import com.example.pj2be.domain.member.MemberLoginDTO;
import com.example.pj2be.service.memberservice.MemberInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/member/info")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/myBoardList")
    public Map<String, Object> myBoardList(@RequestParam("member_id") String member_id,
                                           @RequestParam("ob") String categoryOrdedBy,
                                           @RequestParam("ct") String categoryTopics,
                                           @RequestParam(value = "pg", defaultValue = "1") Integer page
    ){
        System.out.println("page = " + page);
        Map<String, Object> myBoardList = memberInfoService.getMyBoardList(member_id, categoryOrdedBy, categoryTopics, page);

        return myBoardList;
    }
    @PostMapping("/passwordCheck")
    public ResponseEntity editBeforePasswordCheck(@RequestBody @Valid MemberLoginDTO member,
                                        BindingResult bindingResult){
        String memberId = member.getMember_id();
        String password = member.getPassword();
        if(!bindingResult.hasErrors()){
            if(memberInfoService.validateMemberPassword(memberId,password)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}