package Utils;

import java.io.BufferedReader;

public class Get_Para_Data {

    public StringBuffer getParaData(BufferedReader bufferedReader){
        StringBuffer sb = new StringBuffer();
        String line = null;
        try{
            BufferedReader br = bufferedReader;
            while((line = br.readLine()) != null)
                sb.append(line);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sb;
    }

}
