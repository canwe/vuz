package com.itm.vuz.examlist.models;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Vitaly Shmelev
 * Date: 12.08.2006
 * Time: 16:53:57
 */
public class ExamMarksHelper {

    List examMarks;
    List examMarkSigns;
    List testMarks;

    public ExamMarksHelper(){
        examMarks = new ArrayList();
        examMarks.add("");
        examMarks.add("2");
        examMarks.add("3");
        examMarks.add("4");
        examMarks.add("5");

        examMarkSigns = new ArrayList();
        examMarkSigns.add("неявка");
        examMarkSigns.add("неудовлетворительно");
        examMarkSigns.add("удовлетворительно");
        examMarkSigns.add("хорошо");
        examMarkSigns.add("отлично");


        testMarks = new ArrayList();
        testMarks.add("неявка");
        testMarks.add("зачтено");
        testMarks.add("незачтено");

    }

    public List getExamKeys(){
        return examMarks;
    }

    public List getTestKeys(){
        return testMarks;
    }

    public String getExamValue(int ind){
        return (String)examMarkSigns.get(ind);
    }
}
