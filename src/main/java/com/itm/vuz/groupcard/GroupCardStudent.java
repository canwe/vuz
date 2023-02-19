/*
 * $Id$
 * Created on 17.05.2006 10:37:01
 * Last modification $Date$
 */

package com.itm.vuz.groupcard;

import com.itm.vuz.common.domain.Student;
import com.itm.vuz.common.domain.Note;
import com.itm.vuz.common.domain.Mark;
import com.salmonllc.sql.Remotable;
import com.salmonllc.sql.DataStoreException;
import com.salmonllc.sql.DataStoreProxy;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * TODO
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardStudent implements Remotable{

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    private String str;

    private Student student;

    //private String marks = new String();
    private HashMap marks = new HashMap();

    private Note notes;

    public GroupCardStudent() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    /*
    public void setMarks(String discipline, Byte value){
        marks = marks + "@" + discipline + "@"+ "=" + value;
    }

    public String getMarks(){
        return marks;
    }*/
    public Byte getMarks(Long disciplineId) {
        return (marks.get(disciplineId) == null)? null : ((Mark)marks.get(disciplineId)).getValue();
    }

    public void setMarks(Long disciplineId, Mark value) {
        marks.put(disciplineId, value);
    }

    public Mark getMarkObject(Long disciplineId) {
        return (Mark)marks.get(disciplineId);
    }

    public Note getNotes() {
        return notes;
    }

    public void setNotes(Note notes) {
        this.notes = notes;
    }

  public boolean request(String reqType, HttpServletRequest req, boolean sessionValid, String userID, String password, String criteria) throws DataStoreException {
   //this model is used for reports, allow read requests only, no updates
   if (reqType.equals(DataStoreProxy.OPERATION_UPDATE))
     return false;
   else
     return true;
 }



}
