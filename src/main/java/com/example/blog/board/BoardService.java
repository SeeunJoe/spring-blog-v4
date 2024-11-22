package com.example.blog.board;

import com.example.blog._core.error.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;


    public List<BoardResponse.DTO> 게시글목록보기() {
        return boardRepository.findAll().stream()
                .map(BoardResponse.DTO::new)
                .toList();
    }

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow( () -> new Exception404("해당 id의 게시글이 없습니다 : "+id));
        return new BoardResponse.DetailDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    } // commit or rollback 이 됨.

    @Transactional
    public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
        // 1. 게시글 조회, 2. 게시글 수정 권한, 3. 게시글 수정, 4. 수정된 엔티티 응답
        //boardRepository.update(id, updateDTO.getTitle(), updateDTO.getContent());
        Board board = boardRepository.findById(id)
                .orElseThrow( () -> new Exception404("해당 id의 게시글이 없습니다 : "+id)); // 1. 조회
        board.update(updateDTO.getTitle(), updateDTO.getContent());
        // 영속화된 객체상태 변경 - update + commit => 더티체킹
    }
}