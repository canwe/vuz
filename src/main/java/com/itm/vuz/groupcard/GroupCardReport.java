/*
 *
 * $Id$
 * Created on 17.06.2006 11:48:30
 * Last modification $Date$
 */

package com.itm.vuz.groupcard;

import com.itm.vuz.common.reports.Reportable;
import com.itm.vuz.common.domain.Discipline;
import com.itm.vuz.common.domain.Personal;
import com.itm.vuz.groupcard.models.GroupCardColumnHeaders;
import com.salmonllc.util.MessageLog;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.JRAlignment;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;

import java.awt.*;

/**
 * group card report
 *
 * @author Vitaly Shmelev
 * @version $Revision$ $Date$
 */
public class GroupCardReport implements Reportable {

    private GroupCard card;
    private GroupCard card1;

    public static final String REPORT_DISCIPLINE_MARK = "Student.Student.Mark.";
    public static final String SECOND_SEMESTR_FLAG = ".SECOND_SEMESTR";

    public static final int firstColumnWidth = 200;

    public GroupCardReport(GroupCard card, GroupCard card1) {
        this.card = card;
        this.card1 = card1;
    }

    // report
    public JasperDesign designReport() {
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = new JasperDesign();
            jasperDesign.setOrientation(JRReport.ORIENTATION_LANDSCAPE);
            jasperDesign.setPrintOrder(JRReport.PRINT_ORDER_VERTICAL);
            jasperDesign.setName("GroupCard");
            //jasperDesign.setPageWidth(800);
            //jasperDesign.setPageHeight(842);
            //jasperDesign.setColumnWidth(515);
            //jasperDesign.setColumnSpacing(1);
            //jasperDesign.setLeftMargin(40);
            //jasperDesign.setRightMargin(40);
            //jasperDesign.setTopMargin(50);
            //jasperDesign.setBottomMargin(50);

            //Fonts
            JRDesignStyle normalStyle = new JRDesignStyle();
            normalStyle.setName("Arial_Normal");
            normalStyle.setDefault(true);
            normalStyle.setFontName("Arial");
            normalStyle.setFontSize(10);
            normalStyle.setPdfFontName("Helvetica");
            normalStyle.setPdfEncoding("Cp1252");
            normalStyle.setPdfEmbedded(false);
            jasperDesign.addStyle(normalStyle);

            JRDesignStyle boldStyle = new JRDesignStyle();
            boldStyle.setName("Arial_Bold");
            boldStyle.setFontName("Arial");
            boldStyle.setFontSize(10);
            boldStyle.setBold(true);
            boldStyle.setPdfFontName("Helvetica-Bold");
            boldStyle.setPdfEncoding("Cp1252");
            boldStyle.setPdfEmbedded(false);
            jasperDesign.addStyle(boldStyle);

            JRDesignStyle italicStyle = new JRDesignStyle();
            italicStyle.setName("Arial_Italic");
            italicStyle.setFontName("Arial");
            italicStyle.setFontSize(10);
            italicStyle.setItalic(true);
            italicStyle.setPdfFontName("Helvetica-Oblique");
            italicStyle.setPdfEncoding("Cp1252");
            italicStyle.setPdfEmbedded(false);
            jasperDesign.addStyle(italicStyle);

            JRDesignStyle smallStyle = new JRDesignStyle();
            smallStyle.setName("Arial_Small");
            smallStyle.setFontName("Arial");
            smallStyle.setFontSize(8);
            smallStyle.setPdfFontName("Helvetica-Oblique");
            smallStyle.setPdfEncoding("Cp1252");
            smallStyle.setPdfEmbedded(false);
            jasperDesign.addStyle(smallStyle);

            //Parameters
            JRDesignParameter parameter = new JRDesignParameter();
            parameter.setName("ReportTitle");
            parameter.setValueClass(String.class);
            jasperDesign.addParameter(parameter);

            //Fields
            JRDesignField field = new JRDesignField();
            field.setName("Student.Student.Name");
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Student.FamilyName");
            field.setValueClass(String.class);
            jasperDesign.addField(field);


            field = new JRDesignField();
            field.setName("Student.Notes.FreeAttendance");
            //field.setValueClass(java.lang.Short.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.FreeAttendance" + SECOND_SEMESTR_FLAG);
            //field.setValueClass(java.lang.Short.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Rebuke");
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Rebuke" + SECOND_SEMESTR_FLAG);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.LastExamDate");
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.LastExamDate" + SECOND_SEMESTR_FLAG);
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.SessionProlongationDate");
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.SessionProlongationDate" + SECOND_SEMESTR_FLAG);
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.SessionClosingDate");
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.SessionClosingDate" + SECOND_SEMESTR_FLAG);
            //field.setValueClass(java.util.Date.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Studentgrant");
            //field.setValueClass(java.lang.String.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Studentgrant" + SECOND_SEMESTR_FLAG);
            //field.setValueClass(java.lang.String.class);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Practice");
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            field = new JRDesignField();
            field.setName("Student.Notes.Practice" + SECOND_SEMESTR_FLAG);
            field.setValueClass(String.class);
            jasperDesign.addField(field);

            // marks

            for (int i = 0; i < card.getDisciplines().size(); i++) {
                String disciplineId = "" + ((Discipline) card.getDisciplines().get(i)).getDisciplineId();
                field = new JRDesignField();
                field.setName(REPORT_DISCIPLINE_MARK + disciplineId);
                field.setValueClass(String.class);
                jasperDesign.addField(field);
            }

            for (int i = 0; i < card1.getDisciplines().size(); i++) {
                String disciplineId = "" + ((Discipline) card1.getDisciplines().get(i)).getDisciplineId();
                field = new JRDesignField();
                field.setName(REPORT_DISCIPLINE_MARK + disciplineId + SECOND_SEMESTR_FLAG);
                field.setValueClass(String.class);
                jasperDesign.addField(field);
            }


            JRDesignBand band = new JRDesignBand();

            band.setHeight(2);

            JRDesignRectangle rectangle = new JRDesignRectangle();

            JRDesignLine line = new JRDesignLine();
            JRDesignStaticText staticText = new JRDesignStaticText();
            JRDesignTextField textField = new JRDesignTextField();
            JRDesignExpression expression = new JRDesignExpression();

            //jasperDesign.setPageHeader(band);

            final int headerHeight = 140;
            final int headerElementWidth = 26;

            band = new JRDesignBand();

            band.setHeight(headerHeight + 40);
            rectangle = new JRDesignRectangle();
            rectangle.setX(0);
            rectangle.setY(0);
            rectangle.setWidth(790);
            rectangle.setHeight(headerHeight);
            band.addElement(rectangle);

            staticText = new JRDesignStaticText();
            staticText.setX(0);
            staticText.setY(0);
            staticText.setWidth(firstColumnWidth);
            staticText.setHeight(20);
            staticText.setMode(JRElement.MODE_OPAQUE);
            staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
            staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
            staticText.setStyle(normalStyle);
            Personal personal = card.getGroup().getPersonal();
            if(personal!=null){
                staticText.setText(
                        personal.getName() + " " + personal.getPatronymic() + " " +
                        personal.getFamilyName());
            }
            staticText.setBorder((byte) 5);
            band.addElement(staticText);

            staticText = new JRDesignStaticText();
            staticText.setX(0);
            staticText.setY(20);
            staticText.setWidth(firstColumnWidth);
            staticText.setHeight(headerHeight);
            staticText.setMode(JRElement.MODE_OPAQUE);
            staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
            staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
            staticText.setStyle(boldStyle);
            staticText.setText(card.getGroup().getNumber());
            staticText.setBorder((byte) 5);
            band.addElement(staticText);

            staticText = new JRDesignStaticText();
            staticText.setX(0);
            staticText.setY(headerHeight+20);
            staticText.setWidth(firstColumnWidth);
            staticText.setHeight(20);
            staticText.setMode(JRElement.MODE_OPAQUE);
            staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
            staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
            staticText.setStyle(normalStyle);
            staticText.setText("Общее колличество часов");
            staticText.setBorder((byte) 5);
            band.addElement(staticText);

            int i = 0;

            int testCount = 0;
            int examCount = 0;

            for (i = 0; i < card.getDisciplines().size(); i++) {
                if(((Discipline) card.getDisciplines().get(i)).getCheckingForm() == 0){
                    testCount++;
                }else{
                    examCount++;
                }
            }

            if(testCount!=0){
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth);
                staticText.setY(0);
                staticText.setWidth(headerElementWidth * testCount);
                staticText.setHeight(20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setBorder((byte) 5);
                staticText.setText(testCount>1 ? "Зачет" : "Зач");
                band.addElement(staticText);
            }

            if(examCount!=0){
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * testCount);
                staticText.setY(0);
                staticText.setWidth(headerElementWidth * examCount);
                staticText.setHeight(20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(examCount>1 ? "Экзамен" : "Экз");
                staticText.setBorder((byte) 5);
                band.addElement(staticText);
            }

            for (i = 0; i < card.getDisciplines().size(); i++) {
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * i);
                staticText.setY(20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(headerHeight);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setRotation(new Byte((byte) 2));
                //staticText.setForecolor(Color.white);
                //staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(((Discipline) card.getDisciplines().get(i)).getBaseDiscipline().getName());
                MessageLog.writeDebugMessage(((Discipline) card.getDisciplines().get(i)).getBaseDiscipline().getName() + ": checkingForm = [" +
                        ((Discipline) card.getDisciplines().get(i)).getCheckingForm() + "]", this);
                staticText.setBorder((byte) 5);
                //staticText.setRotation();
                band.addElement(staticText);

                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * i);
                staticText.setY(headerHeight+20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(20);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                staticText.setStyle(normalStyle);
                staticText.setText("" + String.valueOf(((Discipline) card.getDisciplines().get(i)).getIndependenthours() + ((Discipline) card.getDisciplines().get(i)).getAudiencehours()));
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

            }

            int j = 0;
            for (j = 0; j < GroupCardColumnHeaders.headers.length; j++) {
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j));
                staticText.setY(0);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(headerHeight+20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setRotation(new Byte((byte) 2));
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(GroupCardColumnHeaders.headers[j]);
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j));
                staticText.setY(headerHeight+20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(20);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                staticText.setStyle(normalStyle);
                staticText.setText("-");
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

            }
            int k = 0;

            testCount = 0;
            examCount = 0;

            for (k = 0; k < card1.getDisciplines().size(); k++) {
                if(((Discipline) card1.getDisciplines().get(k)).getCheckingForm() == 0){
                    testCount++;
                }else{
                    examCount++;
                }
            }
            if(testCount!=0){
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j));
                staticText.setY(0);
                staticText.setWidth(headerElementWidth * testCount);
                staticText.setHeight(20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setBorder((byte) 5);
                staticText.setText(testCount>1 ? "Зачет" : "Зач");
                band.addElement(staticText);
            }
            if(examCount!=0){
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j) + headerElementWidth * testCount);
                staticText.setY(0);
                staticText.setWidth(headerElementWidth * examCount);
                staticText.setHeight(20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(examCount>1 ? "Экзамен" : "Экз");
                staticText.setBorder((byte) 5);
                band.addElement(staticText);
            }


            for (k = 0; k < card1.getDisciplines().size(); k++) {
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j + k));
                staticText.setY(20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(headerHeight);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setRotation(new Byte((byte) 2));
                //staticText.setForecolor(Color.white);
                //staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(((Discipline) card1.getDisciplines().get(k)).getBaseDiscipline().getName());

                MessageLog.writeDebugMessage(((Discipline) card1.getDisciplines().get(k)).getBaseDiscipline().getName() + ": checkingForm = [" +
                        ((Discipline) card1.getDisciplines().get(k)).getCheckingForm() + "]", this);

                staticText.setBorder((byte) 5);
                band.addElement(staticText);

                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j + k));
                staticText.setY(headerHeight+20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(20);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                staticText.setStyle(normalStyle);
                staticText.setText("" + String.valueOf(((Discipline) card1.getDisciplines().get(k)).getIndependenthours() + ((Discipline) card1.getDisciplines().get(k)).getAudiencehours()));
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

            }
            int n = 0;
            for (n = 0; n < GroupCardColumnHeaders.headers.length; n++) {
                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j + k + n));
                staticText.setY(0);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(headerHeight+20);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setRotation(new Byte((byte) 2));
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setStyle(normalStyle);
                staticText.setText(GroupCardColumnHeaders.headers[n]);
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

                staticText = new JRDesignStaticText();
                staticText.setX(firstColumnWidth + headerElementWidth * (i + j + k + n));
                staticText.setY(headerHeight + 20);
                staticText.setWidth(headerElementWidth);
                staticText.setHeight(20);
                staticText.setMode(JRElement.MODE_OPAQUE);
                staticText.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                staticText.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                staticText.setStyle(normalStyle);
                staticText.setText("-");
                staticText.setBorder((byte) 5);
                band.addElement(staticText);

            }
            //Column header
            //band = new JRDesignBand();
            jasperDesign.setColumnHeader(band);



            //Details
            band = new JRDesignBand();
            band.setHeight(20);
            textField = new JRDesignTextField();
            textField.setX(0);
            textField.setY(0);
            textField.setWidth(firstColumnWidth);
            textField.setHeight(20);
            textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_LEFT);
            textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
            textField.setMode(JRElement.MODE_OPAQUE);
            textField.setBorder((byte) 5);
            textField.setStyle(boldStyle);
            expression = new JRDesignExpression();
            expression.setValueClass(String.class);

            expression.setText("$F{Student.Student.FamilyName} + \" \" + $F{Student.Student.Name}");

            textField.setExpression(expression);
            band.addElement(textField);

            i = 0;

            for (i = 0; i < card.getDisciplines().size(); i++) {
                textField = new JRDesignTextField();
                textField.setStretchWithOverflow(true);
                textField.setX(firstColumnWidth + headerElementWidth * i);
                textField.setY(0);
                textField.setWidth(headerElementWidth);
                textField.setHeight(20);
                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
                textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                textField.setMode(JRElement.MODE_OPAQUE);
                textField.setStyle(normalStyle);
                textField.setBorder((byte) 5);
                expression = new JRDesignExpression();
                expression.setValueClass(String.class);
                String disciplineId = "" + ((Discipline) card.getDisciplines().get(i)).getDisciplineId();
                expression.setText("(($F{" + REPORT_DISCIPLINE_MARK + disciplineId + "}) == null) ? \"\" : $F{" + REPORT_DISCIPLINE_MARK + disciplineId + "}");

                textField.setExpression(expression);
                band.addElement(textField);
            }


            for (j = 0; j < GroupCardColumnHeaders.headers.length; j++) {
                textField = new JRDesignTextField();
                textField.setStretchWithOverflow(true);
                textField.setX(firstColumnWidth + headerElementWidth * (i + j));
                textField.setY(0);
                textField.setWidth(headerElementWidth);
                textField.setHeight(20);
                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
                textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                textField.setMode(JRElement.MODE_OPAQUE);
                textField.setBorder((byte) 5);
                textField.setStyle(smallStyle);
                expression = new JRDesignExpression();
                expression.setValueClass(GroupCardColumnHeaders.classes[j]);
                if (GroupCardColumnHeaders.classes[j].getName().indexOf("Date") != -1) {
                    expression.setValueClass(String.class);
                    //expression.setText("(($F{" + GroupCardColumnHeaders.values[j] + "}) == null) ? \"\" : " + "(new SimpleDateFormat(\"MM/dd/yyyy\")).format($F{" + GroupCardColumnHeaders.values[j] + "})");
                } else {
                    if (GroupCardColumnHeaders.classes[j].getName().indexOf("String") != -1) {
                        expression.setText("(($F{" + GroupCardColumnHeaders.values[j] + "}) == null) ? \"\" : " + "$F{" + GroupCardColumnHeaders.values[j] + "}");
                    } else {
                        expression.setText("$F{" + GroupCardColumnHeaders.values[j] + "}");
                    }

                }
                textField.setExpression(expression);
                band.addElement(textField);
            }

            for (k = 0; k < card1.getDisciplines().size(); k++) {
                textField = new JRDesignTextField();
                textField.setStretchWithOverflow(true);
                textField.setX(firstColumnWidth + headerElementWidth * (i + j + k));
                textField.setY(0);
                textField.setWidth(headerElementWidth);
                textField.setHeight(20);
                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
                textField.setBorder((byte) 5);
                textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                textField.setMode(JRElement.MODE_OPAQUE);
                textField.setStyle(normalStyle);
                expression = new JRDesignExpression();
                expression.setValueClass(String.class);
                String disciplineId = "" + ((Discipline) card1.getDisciplines().get(k)).getDisciplineId();
                expression.setText("(($F{" + REPORT_DISCIPLINE_MARK + disciplineId + SECOND_SEMESTR_FLAG + "}) == null) ? \"\" : $F{" + REPORT_DISCIPLINE_MARK + disciplineId + SECOND_SEMESTR_FLAG + "}");

                textField.setExpression(expression);
                band.addElement(textField);
            }

            for (n = 0; n < GroupCardColumnHeaders.headers.length; n++) {
                textField = new JRDesignTextField();
                textField.setStretchWithOverflow(true);
                textField.setX(firstColumnWidth + headerElementWidth * (i + j + k + n));
                textField.setY(0);
                textField.setWidth(headerElementWidth);
                textField.setHeight(20);
                textField.setPositionType(JRElement.POSITION_TYPE_FLOAT);
                textField.setHorizontalAlignment(JRAlignment.HORIZONTAL_ALIGN_CENTER);
                textField.setVerticalAlignment(JRAlignment.VERTICAL_ALIGN_MIDDLE);
                textField.setMode(JRElement.MODE_OPAQUE);
                textField.setBorder((byte) 5);
                textField.setStyle(smallStyle);
                expression = new JRDesignExpression();
                expression.setValueClass(GroupCardColumnHeaders.classes[n]);
                if (GroupCardColumnHeaders.classes[n].getName().indexOf("Date") != -1) {
                    expression.setValueClass(String.class);
                    //expression.setText("(($F{" + GroupCardColumnHeaders.values[n] + SECOND_SEMESTR_FLAG + "}) == null) ? \"\" : " + "(new SimpleDateFormat(\"MM/dd/yyyy\")).format($F{" + GroupCardColumnHeaders.values[n] + SECOND_SEMESTR_FLAG + "})");
                } else {
                    if (GroupCardColumnHeaders.classes[n].getName().indexOf("String") != -1) {
                        expression.setText("(($F{" + GroupCardColumnHeaders.values[n] + SECOND_SEMESTR_FLAG + "}) == null) ? \"\" : " + "$F{" + GroupCardColumnHeaders.values[n] + SECOND_SEMESTR_FLAG + "}");
                    } else {
                        expression.setText("$F{" + GroupCardColumnHeaders.values[n] + SECOND_SEMESTR_FLAG + "}");
                    }

                }
                textField.setExpression(expression);
                band.addElement(textField);
            }

            jasperDesign.setDetail(band);

            //Column footer
            band = new JRDesignBand();
            jasperDesign.setColumnFooter(band);
            //Page footer
            band = new JRDesignBand();
            jasperDesign.setPageFooter(band);

            //Summary
            band = new JRDesignBand();
            jasperDesign.setSummary(band);

            return jasperDesign;

        } catch (JRException e) {
            MessageLog.writeErrorMessage(e, this);
        }


        return null;
    }
}
