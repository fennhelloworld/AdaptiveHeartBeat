/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package notificationserver;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

class Logger
{
    static String strLogFile;
    static DataOutputStream fpLog;

    static
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        strLogFile = sdf.format(date) + "_server.log";

        try {
            fpLog = new DataOutputStream(new FileOutputStream(strLogFile));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void Log(Object objToLog)
    {        
        //
        // Prepare the timestamp.
        //
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        String strToLog = formattedDate + " - " + objToLog.toString() + "\r\n";
        
        //
        // Log the time-stamped spew
        //
        System.out.print(strToLog);
        try {
            synchronized(fpLog) {
                fpLog.writeBytes(strToLog);
                fpLog.flush();
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
