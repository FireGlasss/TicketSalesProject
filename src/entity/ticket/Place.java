package entity.ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Place {
    private String country;
    private String city;
    private String airportCode;

    private LocalDateTime timeDate;

    public Place(String country, String city, String airportCode, LocalDateTime timeDate) {
        this.country = country;
        this.city = city;
        this.airportCode = airportCode;
        this.timeDate = timeDate;
    }

//    public void changeTimeDate(LocalDateTime newLocalDateTime) {
//        timeDate = newLocalDateTime;
//    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public LocalDateTime getDate () {
        return timeDate;
    }

//    public void setTimeDate(LocalDateTime timeDate) {
//        this.timeDate = timeDate;
//    }
//    public LocalDateTime getTimeDate() {
//        return timeDate;
//    }
//
//    public LocalDate getDate() {
//        return timeDate.toLocalDate();
//    }
//    public LocalTime getTime () {
//        return timeDate.toLocalTime();
//    }
}


