package edu.kirkwood.model;

public class Time {
    private long milliseconds;
    private final long MILLISECONDS_PER_SECOND = (long)1000;
    private final long MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * 60;
    private final long MILLISECONDS_PER_HOUR = MILLISECONDS_PER_MINUTE * 60;
    private final long MILLISECONDS_PER_DAY = MILLISECONDS_PER_HOUR * 24;
    private final long MILLISECONDS_PER_WEEK = MILLISECONDS_PER_DAY * 7;
    private final long MILLISECONDS_PER_YEAR = MILLISECONDS_PER_DAY * 365;

    /**
     * @return a string with a human-readable duration
     */
    @Override
    public String toString() {
        long current = this.milliseconds;
        String result = "";
        if (current >= MILLISECONDS_PER_YEAR) {
            long years = current / MILLISECONDS_PER_YEAR;
            current = current % MILLISECONDS_PER_YEAR;
            result += years + " year";
            if(years > 1){
                result += "s";
            }
            result += " ";
        }
        if (current >= MILLISECONDS_PER_WEEK) {
            long weeks = current / MILLISECONDS_PER_WEEK;
            current = current % MILLISECONDS_PER_WEEK;
            result += weeks + " week";
            if(weeks > 1){
                result += "s";
            }
            result += " ";
        }
        if (current >= MILLISECONDS_PER_DAY) {
            long days = current / MILLISECONDS_PER_DAY;
            current = current % MILLISECONDS_PER_DAY;
            result += days + " day";
            if(days > 1){
                result += "s";
            }
            result += " ";
        }
        if (current >= MILLISECONDS_PER_HOUR) {
            long hours = current / MILLISECONDS_PER_HOUR;
            current = current % MILLISECONDS_PER_HOUR;
            result += hours + " hour";
            if(hours > 1){
                result += "s";
            }
            result += " ";
        }
        if (current >= MILLISECONDS_PER_MINUTE) {
            long minutes = current / MILLISECONDS_PER_MINUTE;
            current = current % MILLISECONDS_PER_MINUTE;
            result += minutes + " minute";
            if(minutes > 1){
                result += "s";
            }
            result += " ";
        }
        if (current >= MILLISECONDS_PER_SECOND) {
            long seconds = current / MILLISECONDS_PER_SECOND;
            current = current % MILLISECONDS_PER_SECOND;
            result += seconds + " second";
            if(seconds > 1){
                result += "s";
            }
            result += " ";
        }
        if(current != 0) {
            result += current + " millisecond";
            if (current > 1) {
                result += "s";
            }
            result += " ";
        }
        result = result.trim();
        if(result.isEmpty()) {
            result = "Nothing";
        }
        return result;
    }

    /**
     * @param toSubtract a Time Object to subtract from this Time Object
     * @return this time after subtraction
     */
    public Time subtract(Time toSubtract)
    {

       this.milliseconds -= toSubtract.getMilliseconds();
        return this;
    }
    /**
     * @param toAdd a Time Object to add to this Time Object
     * @return this time after addition
     */
    public Time add(Time toAdd)
    {
        this.milliseconds += toAdd.getMilliseconds();
        return this;
    }

    /**
     * @param num an int to multiply with
     * @return this time after multiplication
     */
    public Time multiply(int num)
    {
        setMilliseconds(this.getMilliseconds() * num);
        return this;
    }

    /**
     * @param num an int to devide by
     * @return
     */
    public Time divide(int num)
    {
        setMilliseconds(this.getMilliseconds() / num);
        return this;
    }

    /**
     * @param toDevide a Time to devide by
     * @return a long result
     */
    public long divide(Time toDevide)
    {
        return this.milliseconds / toDevide.getMilliseconds();
    }


    /**
     * @param milliseconds the initial amount of milliseconds
     */
    public Time(long milliseconds) {
        setMilliseconds(milliseconds);
    }

    /**
     * @param num a number of units
     * @param unit the unit in question
     */
    public Time(long num, String unit) {
        long convert = unitToConversion(unit);
        num = num * convert;
        setMilliseconds(num);
    }

    /**
     * @param unit a string to match a convertion number to
     * @return the conversion number
     */
    private long unitToConversion(String unit){
        long num;
        if (unit.equalsIgnoreCase("year") || unit.equalsIgnoreCase("years") ) {
            num = MILLISECONDS_PER_YEAR;
        } else if (unit.equalsIgnoreCase("week")||unit.equalsIgnoreCase("weeks")) {
            num = MILLISECONDS_PER_WEEK;
        } else if (unit.equalsIgnoreCase("day") || unit.equalsIgnoreCase("days")) {
            num = MILLISECONDS_PER_DAY;
        } else if (unit.equalsIgnoreCase("hour") || unit.equalsIgnoreCase("hours")) {
            num = MILLISECONDS_PER_HOUR;
        } else if (unit.equalsIgnoreCase("minute") || unit.equalsIgnoreCase("minutes") || unit.equalsIgnoreCase("min") || unit.equalsIgnoreCase("mins")) {
            num = MILLISECONDS_PER_MINUTE;
        } else if (unit.equalsIgnoreCase("second") || unit.equalsIgnoreCase("seconds") || unit.equalsIgnoreCase("sec") || unit.equalsIgnoreCase("secs")) {
            num =  MILLISECONDS_PER_SECOND;
        } else if (unit.equalsIgnoreCase("millisecond") || unit.equalsIgnoreCase("milliseconds") || unit.equalsIgnoreCase("mil") || unit.equalsIgnoreCase("mils")) {
            num = 1;
        } else {
            throw new IllegalArgumentException("unit must be a unit of time");
        }
        return num;
    }

    /**
     * @return the amount of milliseconds
     */
    public long getMilliseconds()
    {
        return this.milliseconds;
    }

    private void setMilliseconds(long time){
        if(time <= 0){
            throw new IllegalArgumentException("milliseconds must be greater than Zero");
        }
        else{
            this.milliseconds = time;
        }
    }


}
