package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.StadiumDTO;
import com.matchgetit.backend.entity.StadiumEntity;
import com.matchgetit.backend.service.StadiumService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/matchGetIt/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @GetMapping()
    public String stadiums(Model model, @PageableDefault(page = 0, size = 10, sort = "stdId", direction = Sort.Direction.DESC) Pageable pageable,
                           @RequestParam(required = false) String search) {

        Page<StadiumEntity> stadiums = null;

        if (search == null) {
            stadiums =stadiumService.findAllStadiums(pageable);
        } else {
            stadiums = stadiumService.stadiumSearchList(search, pageable);
        }

        int nowPage = stadiums.getPageable().getPageNumber() + 1; /*pageable에서 넘어온 현재페이지를 가지고올수있다*//*0부터시작하니까 +1*/
        int startPage = Math.max(nowPage - 4, 1); /*매개변수로 들어온 두 값을 비교해서 큰값을 반환 (1일때, -3이되면 안되니까)*/
        int endPage = Math.min(nowPage + 5, stadiums.getTotalPages()); /*마지막 페이지를 초과하면안되니까 이것도 min으로 처리*/

        model.addAttribute("stadiums" , stadiums);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

       // model.addAttribute("stadiums",stadiumService.findAllStadiums(pageable));
        return "admin/pages/stadium/stadiumMgmt";
    }

//    @GetMapping()
//    public String stadiums(Model model){
//        List<StadiumDTO> stadiumDTOS = stadiumService.findAllStadiums();
//        model.addAttribute("stadiums",stadiumDTOS);
//        return "stadium/stadiumMgmt";
//    }

    @GetMapping ("/{stdName}")
    public String stadiumInfo(@PathVariable("stdName") String stdName, Model model){
        Optional<StadiumDTO> result = stadiumService.StadiumOne(stdName);
        StadiumDTO stadiumDTO = result.get();
        model.addAttribute("list",stadiumDTO);
        log.info("stadium={}",stadiumDTO);
        return "admin/pages/stadium/StadiumInfo";
    }

    @GetMapping("/add")
    public String addStadiumForm(Model model){
        model.addAttribute("stadium", new StadiumDTO());

        return "admin/pages/stadium/AddStadium";
    }

    @PostMapping("/add")
    public String addStadium(@ModelAttribute("stadium") StadiumDTO stadiumDTO) {
        stadiumService.addStadium(stadiumDTO);
        return "redirect:/matchGetIt/stadium";
    }

    @Transactional
    @GetMapping ("/{stdName}/delete")
    public String deleteStadium(@PathVariable("stdName") String stdName){
        stadiumService.deleteStadium(stdName);
        System.out.println(stdName+"2");
        return "redirect:/matchGetIt/stadium";
    }

    @GetMapping("/{stdName}/edit")
    public String editStadiumForm(@PathVariable("stdName") String stdName, Model model){
        Optional<StadiumDTO> result = stadiumService.StadiumOne(stdName);
        StadiumDTO stadiumDTO = result.get();
        model.addAttribute("stadium",stadiumDTO);
        log.info("stadium={}",stadiumDTO);
        return "admin/pages/stadium/EditStadium";
    }

    @Transactional
    @PostMapping("/{stdName}/edit")
    public String editStadium(@PathVariable("stdName") String stdName, @ModelAttribute StadiumDTO stadiumDTO){
        stadiumService.updateStadium(stdName,stadiumDTO);
        return "redirect:/matchGetIt/stadium";
    }
}
