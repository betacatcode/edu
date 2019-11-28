package com.lab.edu.controller;

import com.lab.edu.mapper.IntroduceMapper;
import com.lab.edu.model.Culture;
import com.lab.edu.model.Introduce;
import com.lab.edu.model.Video;
import com.lab.edu.service.CultureService;
import com.lab.edu.service.IntroduceService;
import com.lab.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2019/8/20-19:50
 */
@Controller
public class EduController {

    @Autowired
    VideoService videoService;

    @Autowired
    IntroduceService introduceService;

    @Autowired
    CultureService cultureService;

    @RequestMapping("/education/introduce")
    public String goIntroduce(Model model){
        List<Introduce> introduces=introduceService.getAllIntroduce();
        model.addAttribute("introduces",introduces);
        return "/education/introduce";
    }

    @RequestMapping("/education/video")
    public String goVideo(Model model){
        List<Video> videos=videoService.getAllVideo();
        model.addAttribute("videos",videos);
        return "/education/video";
    }

    @RequestMapping("/education/culture")
    public String goCulture(Model model){
        List<Culture> cultures=cultureService.getAllCulture();
        List<Culture> cultures1=new ArrayList<>();
        List<Culture> cultures2=new ArrayList<>();
        List<Culture> cultures3=new ArrayList<>();

        for(int i=0;i<=1;i++)
            cultures1.add(cultures.get(i));
        for (int i=2;i<=4;i++)
            cultures2.add(cultures.get(i));
        for (int i=5;i<=6;i++)
            cultures3.add(cultures.get(i));

        model.addAttribute("cultures1",cultures1);
        model.addAttribute("cultures2",cultures2);
        model.addAttribute("cultures3",cultures3);
        return "/education/culture";
    }

    @RequestMapping("/education/singleCulture")
    public String goSingleCulture(Integer id,Model model){
        Culture culture=cultureService.getCultureById(id);

        model.addAttribute("culture",culture);
        return "/education/singlePage";
    }
}
