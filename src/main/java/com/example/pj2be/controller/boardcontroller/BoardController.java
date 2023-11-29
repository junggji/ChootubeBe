package com.example.pj2be.controller.boardcontroller;

import com.example.pj2be.domain.board.BoardDTO;
import com.example.pj2be.service.boardservice.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

     // 게시글 작성
    @PostMapping("add")
    public void add(BoardDTO board,
                    @RequestParam(value = "uploadFiles[]", required = false) MultipartFile[] files) throws Exception {

        boardService.save(board, files);
    }

    // 게시글 목록
    // 페이징 수정했음, 검색카테고리 수정
    @GetMapping("list")
    public Map<String, Object> list(
            @RequestParam(value = "p", defaultValue = "1") Integer page,
            @RequestParam(value = "c", defaultValue = "all") String category,
            @RequestParam(value = "k", defaultValue = "") String keyword) {

        return boardService.list(page,keyword,category);
    }

    // 게시글 보기
    @GetMapping("id/{id}")
    public BoardDTO view(@PathVariable Integer id) {
        return boardService.get(id);
    }

    // 게시글 수정
    @PutMapping("edit")
    public void edit(@RequestBody BoardDTO board) {
        boardService.update(board);
    }

    // 게시글 삭제 (Update 형식)
    @PutMapping("remove/{id}")
    public void remove(@PathVariable Integer id) {
        boardService.remove(id);
    }

}
