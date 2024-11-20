package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString
    public static class SaveDTO extends Object{
        private String title;
        private String content;

        public Board toEntity(){
            Board board = new Board(null,title,content,null);
            return board;
        }

//        @Override
//        public String toString() {
//            return "SaveDTO{" +
//                    "title='" + title + '\'' +
//                    ", content='" + content + '\'' +
//                    '}';
//        }
    }
}