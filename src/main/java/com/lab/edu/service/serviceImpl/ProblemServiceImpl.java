package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.ProblemMapper;
import com.lab.edu.mapper.WrongMapper;
import com.lab.edu.model.Problem;
import com.lab.edu.model.Wrong;
import com.lab.edu.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author ruin
 * @date 2019/9/18-12:25
 */


@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    ProblemMapper problemMapper;

    @Autowired
    WrongMapper wrongMapper;

    @Override
    public List<Problem> getAllProblems() {
        return problemMapper.getAllProblems();
    }

    @Override
    public List<Wrong> getAllWrongProblems(HttpSession session) {
        Integer userId= (Integer) session.getAttribute("userId");
        return wrongMapper.getAllWrongQuestions(userId);
    }

    @Override
    public List<Problem> chooseTenProblems() {
        List<Integer> l=new ArrayList<Integer>();
        while (l.size()<10){
            int i= (int) (Math.random()*19+1);
            if(!l.contains(i)){
                l.add(i);
            }
        }
        List<Problem> problems=new ArrayList<>();
        for(int i=0;i<l.size();i++)
            problems.add(problemMapper.getProblemById(l.get(i)));

        return problems;
    }

    @Override
    public void calScore(String ids, String ans, Model model,HttpSession session) {

        int score=100;
        int wrongCount=0;
        String[] id=ids.split(",");
        String[] a=ans.split(",");
        List<Integer> wrongIds=new ArrayList<>();

        /*计算评分*/
        for(int i=0;i<10;i++){
            String right=problemMapper.getRightAnswerById(Integer.valueOf(id[i]));
            if(!right.equals(a[i])){
                wrongIds.add(Integer.valueOf(id[i]));
                score-=10;
            }
        }

        wrongCount=(100-score)/10;
        model.addAttribute("wrongCount",wrongCount);
        model.addAttribute("score",score);


        List<Map<String,Object>> problems=new ArrayList<>();
        Integer userId= (Integer) session.getAttribute("userId");

        for(int i=0;i<wrongIds.size();i++){
            Problem problem=problemMapper.getProblemById(wrongIds.get(i));
            Map<String,Object> m=new HashMap<>();
            /*获取正确答案*/
            String rightAns=problemMapper.getRightProblemContentById(wrongIds.get(i));
            m.put("pid",i+1);
            m.put("problem",problem);
            m.put("rightAns",rightAns);
            problems.add(m);

            /*保存错误题目*/
            wrongMapper.saveWrongQuestion(userId,problem.getProblem(),rightAns);
        }
        model.addAttribute("problems",problems);

    }

    @Override
    public Integer delWrongProblemById(Integer id) {
        return wrongMapper.deleteWrongQuestionsById(id);
    }
}
