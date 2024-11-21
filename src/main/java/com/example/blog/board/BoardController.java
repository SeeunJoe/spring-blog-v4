package com.example.blog.board;

import com.example.blog._core.error.ex.Exception404;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {


        if(errors.hasErrors()) {
            String errMsg = errors.getFieldErrors().get(0).getField() + ": " + errors.getFieldErrors().get(0).getDefaultMessage();
            throw new Exception404(errMsg);
        }
        boardService.게시글수정하기(id, updateDTO);

        if(errors.hasErrors()) {
            if(errors.hasErrors()) {}
        }

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, Model model) {
        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
        model.addAttribute("model", updateFormDTO);
        return "update-form";
    }

    @PostMapping("/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
        //유효성검사
//        if(saveDTO.getTitle().isBlank()){
//            throw new RuntimeException("title에 공백 혹은 nulll이 들어갈 수 없습니다.");
//        }

/*        if(errors.hasErrors()) {
            String errMsg = errors.getFieldErrors().get(0).getField() + ": " + errors.getFieldErrors().get(0).getDefaultMessage();
            throw new Exception404(errMsg);
        }*/

        boardService.게시글쓰기(saveDTO);
        return "redirect:/";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail";
    }

    @GetMapping("/")
    public String list(Model model) { // DS(request객체를 model이라는 객체로 랩핑해서 전달해준다)
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);
        return "list";
    }
}