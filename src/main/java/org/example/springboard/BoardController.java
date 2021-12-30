package org.example.springboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", service.selBoardList());
    }

    @GetMapping("/detail")
    public void detail(Model model, BoardEntity entity) {
        service.updBoardHitsUp(entity);
        model.addAttribute("data", service.selBoard(entity));
    }

    @GetMapping("/write")
    public void write() {}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity, RedirectAttributes reAttr) {
        int result = service.insBoard(entity);
        if(result == 0){
            reAttr.addFlashAttribute("msg", "글 등록에 실패하였습니다.");
            reAttr.addFlashAttribute("data", entity);
            return "redirect:/board/write";
        }
        //addAttribute=쿼리스트링 생성, addFlashAttribute=request에 담아서 전송. : RedirectAttributes
        return "redirect:/board/list";
    }

    @GetMapping("/del")
    public String delProc(BoardEntity entity, RedirectAttributes reAttr) {
        int result = service.delBoard(entity);
        if(result==0){
            reAttr.addFlashAttribute("msg", "글 삭제에 실패하였습니다.");
            reAttr.addAttribute("iboard", entity.getIboard());
            return "redirect:/board/detail";
        }
        return "redirect:/board/list";
    }

    @GetMapping("/mod")
    public void mod(Model model, BoardEntity entity) {
        model.addAttribute("data", service.selBoard(entity));
    }

    @PostMapping("/mod")
    public String modProc(BoardEntity entity) {
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }
}

