package za.ac.cput.calendar;

/**Day.java
 * Project 3 Assignment
 * @author Anicka Schouw 217284183
 * October 2021
 */
public class Day {

    int day;
    int month;
    int year;

    public Day(){

    }

    public Day(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public boolean isDay(Day date){
        return day == date.getDay() && month == date.getMonth() && year == date.getYear();
    }
}
