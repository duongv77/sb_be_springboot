package duong.dev.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Admin
 * @version 1.0
 */
public final class DateUtil {
	
	/**
	 * lay dau ngay
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * lay cuoi ngay
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}
	
	public static int calculateAge(Date birthDate, Date currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
        	
        	java.time.LocalDate ldBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	java.time.LocalDate lbCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        	
        	if(ldBirthDate != null && lbCurrentDate != null) {
        		return java.time.Period.between(ldBirthDate, lbCurrentDate).getYears();
        	} else {
        		return 0;
        	}
        } else {
            return 0;
        }
    }
	
	public static Date convertDatetimeToDate(Date date) {
		if (date == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE),
                0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
		cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
	}


    public static boolean compare(Date date1, Date date2, String format) throws Exception {
        if (date1 == null || date2 == null) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (dateFormat.format(date1).equals(dateFormat.format(date2))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean equalOnlyField(Date date1, Date date2, int type) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c1.get(type) == c2.get(type);

    }

    /**
     * add second
     *
     * @param nowDate Date
     * @param period  integer
     * @return Date
     */
    public static Date addSecond(Date nowDate, int period) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(nowDate);
    	calendar.add(Calendar.SECOND, period);
        return calendar.getTime();
    }

    /**
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long minusDate(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMyyString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("MM/yyyy");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyNoSlashString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");
            return date.format(value);
        }
        return "";
    }
    
    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMddHHMMssNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("ddMMyyyyHHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    public static String date2yyyyMMddStringWithSlash(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            return date.format(value);
        }
        return "";
    }
    
    
    public static String convertToString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            return date.format(value);
        }
        return "";
    }
    

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMddHHString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHH");
            return date.format(value);
        }
        return "";
    }

    public static Date string2Date(String value) {
        return DateUtil.string2DateByPattern(value, "dd/MM/yyyy");
    }
    
   

    public static Date string2DateByPattern(String value, String pattern) {
        if (value!=null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            dateTime.setLenient(false);
            try {
                return dateTime.parse(value);
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }

    private DateUtil() {
    }

    /**
     * @param value String
     * @return Date
     */
    public static Date string2Date(String value, String format) {
        try {
            SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(format);
            return dbUpdateDateTime.parse(value);
        } catch (ParseException ex) {
        }

        return new Date();
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2String(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateNoSlash = new SimpleDateFormat("yyyyMMdd");
            return dateNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateH2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateNoSlash = new SimpleDateFormat("yyyyMMddHH");
            return dateNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2StringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat(
                    "yyyyMMddHHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2String(Date value) {
        if (value != null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss");
            return dateTime.format(value);
        }
        return "";
    }
    
    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2StringyyyyMMdd(Date value) {
        if (value != null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "yyyy/MM/dd");
            return dateTime.format(value);
        }
        return "";
    }
    
    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2StringddMMyyyy(Date value) {
        if (value != null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "ddMMyyyy");
            return dateTime.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dbUpdateDateTime2String(Date value) {
        if (value != null) {
            SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            return dbUpdateDateTime.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return Timestamp
     */
    public static Timestamp date2Timestamp(Date value) {
        if (value != null) {
            return new Timestamp(value.getTime());
        }
        return null;
    }

    /**
     * @return Date
     */
    public static Date sysDate() {
        return new Date();
    }

    /**
     * return now if input date is null, otherwise return date
     *
     * @param date
     * @return
     */
    public static Date dateToNow(Date date) {
        return date == null ? new Date() : date;
    }

    /**
     * @return Date
     */
    public static Date sysdateYmd() {
        return nextdate(0);
    }

    /**
     * @param day integer
     * @return Date
     */
    public static Date nextdate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + day, 0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    /**
     * @param date Date
     * @param day  integer
     * @return Date
     */
    public static Date nextdate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + day, 0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    /**
     * get the next n month.
     *
     * @param date  Date
     * @param month number of next month
     * @return Date
     */
    public static Date nextMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + month,
                calendar.get(Calendar.DATE),
                0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }

    public static Date nextMonthDateTime(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * get the previos n month
     *
     * @param date  Date
     * @param month integer
     * @return Date
     */
    public static Date getPreMonthDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) - month,
                calendar.get(Calendar.DATE),
                0, // hour
                0, // min
                0); // sec
        /**
         * clear millisecond field
         */
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();

    }

    /**
     * @return String
     */
    public static String sysdateString() {
        SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dbUpdateDateTime.format(new Date());
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDate() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return date;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateTime() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateTime;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateTimeMinute() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return dateTime;
    }

    /**
     * [timestampToStringFF function.].<br>
     * [Detail description of method.]
     *
     * @param date Timestamp
     * @return String
     */
    public static String timestampToStringFF(Timestamp date) {
        if (date != null) {
            SimpleDateFormat dbDateTimeString = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            return dbDateTimeString.format(date);
        }
        return "";
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDbUpdateDateTime() {
        SimpleDateFormat dbUpdateDateTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dbUpdateDateTime;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getYYYYMM() {
        SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");
        return yyyymm;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getMMdd() {
        SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
        return mmdd;
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyString(Date value) {
        if (value != null) {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
            return ddMMyyyy.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String getFirstDateOfMonth(Date value) {
        if (value != null) {
            SimpleDateFormat MMyyyy = new SimpleDateFormat("MM/yyyy");
            return "01/" + MMyyyy.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMyyyyString(Date value) {
        if (value != null) {
            SimpleDateFormat yyyyMM = new SimpleDateFormat("MM/yyyy");
            return yyyyMM.format(value);
        }
        return "";
    }

    /**
     * date to yyMMddString
     *
     * @param value Date
     * @return String
     */
    public static String date2yyMMddString(Date value) {
        if (value != null) {
            SimpleDateFormat yyMMdd = new SimpleDateFormat("yy/MM/dd");
            return yyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyMMddStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
            return yyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMM");
            return yyyymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyyyMMddStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yyyymm = new SimpleDateFormat("yyyyMMdd");
            return yyyymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2yyMMStringNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat yymm = new SimpleDateFormat("yyMM");
            return yymm.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMddString(Date value) {
        if (value != null) {
            SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
            return mmdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String second2String(Date value) {
        if (value != null) {
            return SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM).format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return []String
     */
    public static String[] getSplitDate(Date value) {
        if (value != null) {
            DecimalFormat df = new DecimalFormat("00");
            String[] dateTime = dateTime2String(value).split(" ");
            String[] date = new String[6];
            String[] tmpDate = dateTime[0].split("/");
            date[0] = df.format(Integer.parseInt(tmpDate[0]));
            date[1] = df.format(Integer.parseInt(tmpDate[1]));
            date[2] = df.format(Integer.parseInt(tmpDate[2]));
            tmpDate = dateTime[1].split(":");
            date[3] = df.format(Integer.parseInt(tmpDate[0]));
            date[4] = df.format(Integer.parseInt(tmpDate[1]));
            date[5] = df.format(Integer.parseInt(tmpDate[2]));
            return date;
        }
        return new String[6];
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2MMddTime(Date value) {
        if (value != null) {
            SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd HH:mm:ss");
            return mmdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2YYYYMMddTime(Date value) {
        if (value != null) {
            SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return yyyyMMdd.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2HHMMssNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("HHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyHHMMssNoSlash(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("ddMMyyyyHHmmss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }


    /**
     * @param value Date
     * @return String
     */
    public static String dateyyyyMMddHHmmSS(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String dateyyyyMMdd(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("yyyy-MM-dd");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * @return
     */
    public static Timestamp nowDateMilli() {
        return new Timestamp(sysDate().getTime());
    }

    /**
     * @param date Date
     * @return integer
     */
    public static int getYY(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR)) % 100;
    }

    /**
     * @param nowDate Date
     * @return integer
     */
    public static int getMonth(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @param nowDate Date
     * @return integer
     */
    public static int getDay(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //==========================================================================

    /**
     * addMilli.<br>
     *
     * @param nowDate Date
     * @param period  integer
     * @return Timestamp
     */
    //==========================================================================
    public static Timestamp addMilli(Timestamp nowDate, int period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MILLISECOND, period);

        Timestamp stopTerm = date2Timestamp(calendar.getTime());

        return stopTerm;
    }

    /**
     * add minute
     *
     * @param nowDate Date
     * @param period  integer
     * @return Date
     */
    public static Date addMinute(Date nowDate, int period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(Calendar.MINUTE, period);

        return calendar.getTime();
    }

    public static Date getddMMyyyy(Date date) throws Exception {
        Date result = date;
        if (result != null) {
            String strDate = DateUtil.date2ddMMyyyyString(date);
            result = DateUtil.string2Date(strDate);
        }
        return result;
    }

    public static Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static Date convertStringToTime(String date, String pattern) throws ParseException {
        if (date == null || "".equals(date.trim())) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);

    }

//    public static DateTime convertStringToDateTime(String date, String pattern) throws ParseException {
//        if (date == null || "".equals(date.trim())) {
//            return null;
//        }
//
//        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
//        return formatter.parseDateTime(date);
//    }

//    public static void main(String[] args) throws Exception {
//    	String strDate="2021-11";
//    	Date date = convertStringToTime (strDate, "yyyy-MM");
//		System.out.println(date.toString());
//		
//		
//    }

    public static String dateToStringWithPattern(Date date, String pattern) {
    	if (date == null) {
    		return null;
    	}
    	
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        }
    }

    public static Long date2LongUpdateDateTime(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            String updateDateTime = date.format(value);
            return Long.parseLong(updateDateTime);
        }
        return null;
    }

    /**
     * kiem tra tuoi khach hang o nam trong khoang min - max hay ko
     *
     * @param minOld
     * @param maxOld
     * @param customerBirthDay
     * @param sysDate
     * @return
     * @throws Exception
     * @author quangkm
     */
    public static boolean checkCustomerAge(int minOld, int maxOld, Date customerBirthDay, Date sysDate) throws Exception {
        try {
            Calendar birthDateCalendar = Calendar.getInstance();
            birthDateCalendar.setTime(customerBirthDay);
            Calendar currDateCalendar = Calendar.getInstance();
            currDateCalendar.setTime(sysDate);

            int yearOfBirthDate = birthDateCalendar.get(Calendar.YEAR);

            int yearOfCurrDate = currDateCalendar.get(Calendar.YEAR);

            if (minOld <= yearOfCurrDate - yearOfBirthDate && maxOld >= yearOfCurrDate - yearOfBirthDate) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Ham so sanh hai ngay voi nhau
     *
     * @param date1
     * @param date2
     * @return
     * @author anhbq8
     */
//    public static int compareDateToDate(Date date1, Date date2) {
//        DateTime date1DT = new DateTime(date1);
//        DateTime date2DT = new DateTime(date2);
//        return DateTimeComparator.getDateOnlyInstance().compare(date1DT, date2DT);
//    }

    /**
     * convert date sang string dinh dang nam-thang-ngay-gio-phut-giay
     *
     * @param value
     * @return
     * @author quangkm
     */
    public static String date2yyyyMMddHHmmSsString(Date value) {
        if (value != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            return date.format(value);
        }
        return "";
    }

    /**
     * @param value Date
     * @return String
     */
    public static String date2ddMMyyyyHHMMss(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    public static Date addMonth(Date date, int... numMonth) {
        for (int i : numMonth) {
            date = addMonth(date, i);
        }
        return date;
    }

    public static Date getLastDayOfMonth(Date date) {
        LocalDate today = new LocalDate(date);
        return today.dayOfMonth().withMaximumValue().toDate();
    }

    public static Date getFirstDayOfMonth(Date date) {
        LocalDate today = new LocalDate(date);
        return today.dayOfMonth().withMinimumValue().toDate();
    }

    public static XMLGregorianCalendar convertToXmlDate(Date date) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return date2;
    }

//    public static int compareDateToDateTime(Date date1, Date date2) {
//        DateTime date1DT = new DateTime(date1);
//        DateTime date2DT = new DateTime(date2);
//        return DateTimeComparator.getInstance().compare(date1DT, date2DT);
//    }
//
//    public static Date stripTimeFromDate(Date date) {
//        return new LocalDateTime(date).toLocalDate().toDate();
//    }

    /**
     * @param strDate1
     * @param strDate2
     * @param pattern
     * @return
     * @date 06-04-2016 14:52:51
     * @author TuyenLT18
     * @description
     */
    public static boolean greaterOrEqualsddMMyyyy(String strDate1, String strDate2, int type) {
        String pattern = "dd/MM/yyyy";
        if (strDate1 == null || strDate1 == "" || strDate2 == null | strDate2 == "") {
            return false;
        }
        Date date1 = string2DateByPattern(strDate1, pattern);
        Date date2 = string2DateByPattern(strDate2, pattern);
        
        if (date1 == null || date2 == null) {
        	return false;
        }
        
        return greaterOrEqualsddMMyyyy(date1, date2, type);
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @date 06-04-2016 14:52:59
     * @author TuyenLT18
     * @description So sanh chinh xac 2 ngay
     */
    public static boolean greaterOrEqualsddMMyyyy(Date date1, Date date2, int type) {
        return date1.compareTo(date2) > 0 || equalsByTypeddMMyyyy(date1, date2, type);
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @date 06-04-2016 14:52:59
     * @author TuyenLT18
     * @description So sanh chinh xac
     */
    public static boolean equalsByTypeddMMyyyy(Date date1, Date date2, int type) {
        return equalOnlyField(date1, date2, type) && equalOnlyField(date1, date2, Calendar.YEAR);
    }

//    public static Date trunc(Date date) {
//        return new DateTime(date).withTimeAtStartOfDay().toDate();
//    }
//
//    public static DateTime convertStringTimeToDate(String dateTime) {
//        int i = 0;
//        while (i < XmlStream.getFormats().length) {
//            try {
//                return DateTimeFormat.forPattern(XmlStream.getFormats()[i]).parseDateTime(dateTime);
//            } catch (Exception e) {
//                i++;
//            }
//        }
//        return null;
//    }
    
    
    /**
     * @param value Date
     * @return String
     */
    public static String dateTime2StringyyyyMM(Date value) {
        if (value != null) {
            SimpleDateFormat dateTime = new SimpleDateFormat(
                    "yyyy/MM");
            return dateTime.format(value);
        }
        return "";
    }
    
    public static Long getYearOfDate(Date date) {
    	java.time.LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	int year  = localDate.getYear();
    	return new Long(year);
    }

    
}
