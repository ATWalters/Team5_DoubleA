package cpsc4150.allaroundclemson;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    //Method that reads the json file so it can be parsed
    static String getJsonFromAssets(Context context, String filename){
        String jsonString;

        try{
            InputStream is = context.getAssets().open(filename);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}

