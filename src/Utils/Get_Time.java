package Utils;

import java.text.SimpleDateFormat;

public class Get_Time {

    public String getCurrentTime(){
        //获取当前时间
        long l = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(l);
        System.out.println("当前时间："+sdf.format(l));
        return sdf.format(l);
    }

}
