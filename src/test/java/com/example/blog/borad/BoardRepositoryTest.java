package com.example.blog.borad;

import com.example.blog.board.Board;
import com.example.blog.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// @RequiredArgsConstuctor -> test에서는 롬복을 따로 추가하지 않아 사용할 수 없으므로 @Autowired를 사용한다.
@Import(BoardRepository.class)
@DataJpaTest // DB관련된 자원들을 메모리(IoC)에 올린다.
public class BoardRepositoryTest {

    @Autowired // -> 의존성 주입 : Spring
    private BoardRepository boardRepository;

    public void delete_test(){
        //given
        int id= 1;

        //when
        boardRepository.delete(id);

        //eye
        List<Board> boardList = boardRepository.findAll();
        System.out.println("size : "+ boardList.size());
    }

    public void save_test(){
        // given
        String title = "제목6";
        String content = "내용6";

        // when
        boardRepository.save(title, content);

        // eye
        Board board = boardRepository.findById(6);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

    } // rollback (@Transactional)

    @Test
    public void findAll_test(){
        // given - parameter를 넣는다. testcode에서는 junit이 제어해서

        // when - 실제로 테스트할 코드
        List<Board> boardList = boardRepository.findAll();
        System.out.println();

        // eye - 눈으로 보슈
        for(Board board : boardList){
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("=================");
        }

        // then - 상태 검증하기 - 초보자라 우선은 패스 : 나중에 배포할 때 쓰걸아^^


    }
}
