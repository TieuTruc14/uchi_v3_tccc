
import com.vn.osp.common.util.SystemProperties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * Created by tieut on 9/14/2017.
 */
public class test {
    public static void main(String[] args) {
        Timestamp date=new Timestamp(new Date().getTime());
        Date newdate=new Date(date.getTime());
        Calendar ca=Calendar.getInstance();
        ca.setTimeInMillis(date.getTime());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(year);
        Properties prop = new Properties();
        InputStream input = null;
        try{
            ClassLoader loader = SystemProperties.class.getClassLoader();
            prop.load(loader.getResourceAsStream("system.properties"));
            System.out.println("Gia tri : "+prop.getProperty("email"));
        }catch (Exception e){

        }
    }
}
