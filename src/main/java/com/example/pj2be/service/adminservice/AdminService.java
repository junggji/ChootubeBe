package com.example.pj2be.service.adminservice;

import com.example.pj2be.domain.admin.SuspensionDTO;
import com.example.pj2be.mapper.adminmapper.AdminMapper;
import com.example.pj2be.mapper.membermapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final MemberMapper memberMapper;

    public Map<String, Object> getBoardData() {
        Map<String, Object> map = new HashMap<>();

        map.put("boardDataList", adminMapper.getBoardData());

        System.out.println("어드민 서비스 보드데이터");

        return map;
    }

    public Map<String, Object> getUserData() {
        Map<String, Object> map = new HashMap<>();

        map.put("userWriteRankDataList", adminMapper.getUserWriteRankData());
        map.put("userLikeRankDataList", adminMapper.getUserLikeRankData());
        map.put("userCommentRankDataList", adminMapper.getUserCommentRankData());

        System.out.println("어드민 서비스 유저데이터");

        return map;
    }

    // 정지회원 관리 페이지
    public Map<String, Object> getSuspensionList() {
        Map<String, Object> map = new HashMap<>();

        map.put("suspensionList", adminMapper.selectSuspensioningMember());
        map.put("releaseList", adminMapper.selectReleaseMember());

        return map;

    }

    // 정지된 회원 정지해제
    public void updateSuspension(SuspensionDTO dto) {

        System.out.println(dto);

        // 정지해제된 멤버의 role을 아이언 회원으로
        memberMapper.updateByReleaseId(dto.getMember_id());
        System.out.println("eeeeeeeeee");

        // 정지해제 Suspension 테이블에서 삭제
        adminMapper.deleteSuspension(dto.getId());

    }

    public SuspensionDTO getSuspensionMember(String memberId) {
        return adminMapper.selectSuspensionMember(memberId);
    }
}
