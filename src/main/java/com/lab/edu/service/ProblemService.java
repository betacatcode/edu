package com.lab.edu.service;

import com.lab.edu.model.Problem;
import com.lab.edu.model.Wrong;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ruin
 * @date 2019/9/18-12:24
 */

public interface ProblemService {

    public List<Problem> getAllProblems();

    public List<Wrong> getAllWrongProblems(HttpSession session);

    public List<Problem> chooseTenProblems();

    public void calScore(String ids, String ans, Model model,HttpSession session);

    public Integer delWrongProblemById(Integer id);
}
