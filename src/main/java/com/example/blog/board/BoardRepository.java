package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // JPA는 EntityManager로 DB에 접근한다 (자바에서 DBConnection)
    private final EntityManager em;

    public void delete(int id){
        Query q = em.createNativeQuery("delete from board_tb where id=?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete 때 사용함
    }

    // 게시글 쓰기
    public void save(Board board){
        em.persist(board);
    }

    public List<Board> findAll(){
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList();
    }

    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 (물음표 순서, 물음표에 바인딩될 변수값)
        return (Board) q.getSingleResult();
    }
}