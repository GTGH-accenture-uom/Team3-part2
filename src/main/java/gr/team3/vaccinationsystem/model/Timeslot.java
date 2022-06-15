package gr.team3.vaccinationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class Timeslot implements Serializable {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minutes;
    private int startMinute;
    private int endMinute;
    private Integer ID;
    @JsonIgnore
    Doctor doctor;
    /* isFree is true when the timeslot is free/available and false when it has been booked*/
    private boolean isFree;


    public Timeslot(int day, int month, int year, int hour, int minutes, int startMinute, int endMinute, Doctor doctor) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.doctor = doctor;
        isFree = true;

    }


    public Timeslot(int day, int month, int year, int hour, int minutes, int startMinute, int endMinute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        isFree = true;
    }


    public Timeslot() {
    }


    public  Integer getDay() {return day;}
    public  Integer getMonth() {return month;}
    public  Integer getYear() {return year;}
    public int getHour() {return hour;}
    public int getMinutes() {return minutes;}
    public int getStartMinute() {return startMinute;}
    public int getEndMinute() {return endMinute;}
    public Doctor getDoctor() {return doctor;}
    public void setDay(int day) {this.day = day;}
    public void setMonth(int month) {this.month = month;}
    public void setYear(int year) {this.year = year;}
    public void setHour(int hour) {this.hour = hour;}
    public void setMinutes(int minutes) {this.minutes = minutes;}
    public void setStartMinute(int startMinute) {this.startMinute = startMinute;}
    public void setEndMinute(int endMinute) {this.endMinute = endMinute;}
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }


    @Override
    public String toString() {
        return "Timeslot{" +
                "ID=" + ID +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minutes=" + minutes +
                ", startMinute=" + startMinute +
                ", endMinute=" + endMinute +
                ", doctor=" + doctor +
                '}';
    }


    //Gets the local date
    public LocalDate getLocalDate() {
        return LocalDate.of(year,month,day);

    }


    public LocalDateTime getLocalDateWithTime() {
        return LocalDateTime.of(LocalDate.of(year,month,day), LocalTime.of(hour,minutes,0));
    }
}
