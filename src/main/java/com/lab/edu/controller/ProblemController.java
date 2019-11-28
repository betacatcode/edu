package com.lab.edu.controller;

import com.lab.edu.model.Problem;
import com.lab.edu.model.Wrong;
import com.lab.edu.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruin
 * @date 2019/9/18-12:21
 */
@Controller
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping("/exercise/index")
    public String exercise(Model model){

        List<Problem> l=problemService.chooseTenProblems();

        List<Map<String,Object>> problems=new ArrayList<>();

        for(int i=0;i<l.size();i++){
            Problem p=l.get(i);
            Map<String,Object> m=new HashMap<>();
            m.put("pid",i+1);
            m.put("problem",p);
            problems.add(m);
        }
        model.addAttribute("problems",problems);
        return "/exercise/index";
    }

    @RequestMapping("/exercise/calScore")
    public String calScore(String ids,String ans,Model model,HttpSession session){
        problemService.calScore(ids,ans,model,session);
        return "/exercise/scorePage";
    }

    @RequestMapping("/manage/wrong")
    public String goWrongManage(Model model,HttpSession session){
        List<Wrong> wrongs=problemService.getAllWrongProblems(session);
        model.addAttribute("msgs",wrongs);
        return "/manage/wrong";
    }

    @RequestMapping("/manage/delWrongQueById")
    public @ResponseBody Integer delWrongQueById(Integer id){
        return problemService.delWrongProblemById(id);
    }

    @RequestMapping("/manage/delWrongQueByIds")
    public @ResponseBody Integer delWrongQueByIds(String ids){
        String []id=ids.split(",");
        for(String i:id){
            problemService.delWrongProblemById(Integer.valueOf(i));
        }
        return 1;
    }

}
