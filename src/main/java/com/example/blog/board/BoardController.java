package com.example.blog.board;

import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api")
    public Resp<?> list() { // DS(request객체를 model이라는 객체로 랩핑해서 전달해준다)
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        return Resp.ok(boardList);
    }

    @PostMapping("/api/board")    //@RequestBody를 붙여야 json으로 받아올 수 있다.
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null);
    }

    @PutMapping("/api/board/{id}") // 수정은 put!!!!!!!!!!!!! -> 동사 지우기
    public Resp<?> update(@PathVariable("id") Integer id, @Valid @RequestBody BoardRequest.UpdateDTO updateDTO, Errors errors) {
        boardService.게시글수정하기(id, updateDTO);
        return Resp.ok(null);
    }

    @DeleteMapping("/api/board/{id}") // post와 put만 @RequestBody받을 수 있다
    public Resp<?> delete(@PathVariable("id") Integer id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }

    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }

}