package br.com.alcoolesaude.aes;

import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by thiago on 19/07/16.
 */
public class Evaluation {

    private Date dateCreated;
    private Date syncDate;
    private Date birth;
    private String gender;
    private Boolean pregnant;
    private Boolean drink;
    private Integer audit1;
    private Integer audit2;
    private Integer audit3;
    private Integer audit4;
    private Integer audit5;
    private Integer audit6;
    private Integer audit7;
    private Integer audit8;
    private Integer audit9;
    private Integer audit10;
    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;
    private String email;


    


    public Evaluation(){
        dateCreated = new Date();
    }

    public void setBirth(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        birth = cal.getTime();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getDrink() {
        return drink;
    }

    public void setDrink(Boolean drink) {
        this.drink = drink;
    }

    public Integer getAudit1() {
        return audit1;
    }

    public void setAudit1(Integer audit1) {
        this.audit1 = audit1;
    }

    public Integer getAudit2() {
        return audit2;
    }

    public void setAudit2(Integer audit2) {
        this.audit2 = audit2;
    }

    public Integer getAudit3() {
        return audit3;
    }

    public void setAudit3(Integer audit3) {
        this.audit3 = audit3;
    }

    public Integer getMonday() {
        return monday;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public Integer getAudit4() {
        return audit4;
    }

    public void setAudit4(Integer audit4) {
        this.audit4 = audit4;
    }

    public Integer getAudit5() {
        return audit5;
    }

    public void setAudit5(Integer audit5) {
        this.audit5 = audit5;
    }

    public Integer getAudit6() {
        return audit6;
    }

    public void setAudit6(Integer audit6) {
        this.audit6 = audit6;
    }

    public Integer getAudit7() {
        return audit7;
    }

    public void setAudit7(Integer audit7) {
        this.audit7 = audit7;
    }

    public Integer getAudit8() {
        return audit8;
    }

    public void setAudit8(Integer audit8) {
        this.audit8 = audit8;
    }

    public Integer getAudit9() {
        return audit9;
    }

    public void setAudit9(Integer audit9) {
        this.audit9 = audit9;
    }

    public Integer getAudit10() {
        return audit10;
    }

    public void setAudit10(Integer audit10) {
        this.audit10 = audit10;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isFemale() {
        return this.gender == "F";}

    public boolean isMale() {
        return this.gender == "M";}

    public int getAudit3Sum() {
        return this.audit1 + this.audit2 + this.audit3; }

    public boolean audit3LimitExceeded() {
        return getAudit3Sum() > 6;}


    public boolean dayLimitExceeded() {
        int limit =  isFemale() ? 1 : 2;
        if (sunday != null && sunday > limit){
            return true;}
        if (monday != null && monday > limit) {
            return true;}
        if (tuesday != null && tuesday > limit) {
            return true;}
        if (wednesday != null && wednesday > limit) {
            return true;}
        if (thursday != null && thursday > limit) {
            return true;}
        if (friday != null && friday > limit) {
            return true;}
        if (saturday != null && saturday > limit) {
            return true;}
        else{
            return false;}
    }

    public boolean weekLimitExceeded(){
        int limit =  isFemale() ? 5 : 10;
        return getWeekTotal() > limit;
    }

    public int getWeekTotal() {
        return (sunday != null ? sunday : 0) + (monday != null ? monday : 0) + (tuesday != null ? tuesday : 0) +
                (wednesday != null ? wednesday : 0) + (thursday != null ? thursday : 0) + (friday != null ? friday : 0) + (saturday != null ? saturday : 0);
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birth);

        if (today.get(Calendar.MONTH) < birthCal.get(Calendar.MONTH)) {
            return (today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR) - 1);

        } else if (today.get(Calendar.MONTH) == birthCal.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH)) {
            return (today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR) - 1);

        } else {
            return today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        }

    }

    public boolean isUnderage() {
        return getAge() < 18;

    }


    public int getAuditFullSum() {
        return audit1 + audit2 + audit3 + audit4 + audit5 + audit6 + audit7 + audit8 + audit9 + audit10;
    }


}
