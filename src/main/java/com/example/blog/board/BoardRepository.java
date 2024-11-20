package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // JPA는 EntityManager로 DB에 접근한다 (자바에서 DBConnection)
    private final EntityManager em;

    //조회만 하면 업데이트가 알아서 되기 때문에 업데이트 필요없다.
/*    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title=?, content=? where id=?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate();
    }*/

    public void delete(int id){
/*        Query q = em.createNativeQuery("delete from board_tb where id=?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete 때 사용함*/
        em.createQuery("delete from Board b where id = :id") // ?대신 :사용한다.
                .setParameter("id", id)
                .executeUpdate();
    }

    public void save(Board board){
        // 비영속
        em.persist(board);
        // 동기화 완료(영속화됨)
        //return board;
    }

    public List<Board> findAll() {
/*        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList();*/
//        em.createQuery("select b from Board - 클래스 b");
        return em.createQuery("select b from Board b order by b.id desc ",Board.class)
                .getResultList();
    }

/*    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 (물음표 순서, 물음표에 바인딩될 변수값)
        return (Board) q.getSingleResult();
        }
 */ public Optional<Board> findById(int id) {
     return Optional.ofNullable(em.find(Board.class, id));
    }
}