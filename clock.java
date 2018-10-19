// A year is 365 days
// A day is 24 hours, each hour containing 60 minutes. This means there are 1440 minutes in a day.
// Time is displayed using a 24-hour clock
// The first year is 1, not 0
// The first day of the year is 1, not 0
// 2 moons orbit Brittania. Trammel has a 9-day phase cycle. Felucca has a 14-day phase cycle.

public class Main {

    public static void main(String args[]){
        Clock clock1 = new Clock(168260271);
        Clock clock2 = new Clock(27364375);
        Clock clock3= new Clock(67294091);
        clock1.displayInfo();
        clock2.displayInfo();
        clock3.displayInfo();
    }
}

public class Clock {

    int worldTime;
    int year;
    int day;
    int hour;
    int minute;
    int trammel;
    int felucca;

    public Clock (int wt) {
        worldTime = wt;
        year = calcYear();
        day = calcDay();
        hour = calcHour();
        minute = calcMinute();
        trammel = calcTrammel();
        felucca = calcFelucca();
    }

    //calculating the year
    public int calcYear() {
        return worldTime/(365*1440)+1;
    }

    //calculating day
    public int calcDay() {
        int minFromYears = (year-1)*1440*365;
        return 1 + (worldTime - minFromYears)/1440;
    }

    //calculating the hour (based on 24-hour clock)
    public int calcHour() {
        int minFromYears = (year-1)*1440*365;
        int minFromDays = (day-1)*1440;
        return (worldTime - minFromYears - minFromDays)/24;
    }

    //calculating minutes
    public int calcMinute() {
        int minFromYears = (year-1)*1440*365;
        int minFromDays = (day-1)*1440;
        int minFromHours= hour*60;
        return minFromYears- minFromDays- minFromHours;
    }

    //calculating phase day for Trammel (out of 9 day cycle)
    public int calcTrammelDay() {
        int totalDays = worldTime/1440;
        int modulus = totalDays%9 ;
        if (modulus == 0) {
            return 9;
        }
        return modulus;
    }

    //calculating phase day for Felucca (out of 14 day cycle)
    public int calcFeluccaDay() {
        int totalDays = worldTime/1440;
        int modulus = totalDays%14;
        if (modulus == 0) {
            return 14;
        }
        return modulus;
    }

    public void displayInfo() {
        System.out.printf("worldTime = %d", worldTime);
        System.out.printf("It is %02d:%d on day %d of year %d", minute, hour, day, year);
        System.out.printf("Trammel is in day %d of its 9 day phase.", trammel);
        System.out.printf("Felucca is in day %d of its 14 day phase.", felucca);
    }
}