package engine.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ASUS on 12/25/2014.
 */
public class ExecuteUtility {
    private ExecuteUtility () {
    }
    public static String executeCommand(List<String> command){
        StringBuffer output = new StringBuffer();
        Process p;
        Timer processTimer = new Timer();
        ProcessKiller pk;

        try {
            p = new ProcessBuilder(command).start();
            //kills the thread if it runs for more than 5s (infinite loop)
            pk = new ProcessKiller(p);
            processTimer.schedule(pk ,5000);

            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            if(pk.getHasKilled()) {
                output.insert(0, "ERROR: INFINITE LOOP \n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString().substring(0, output.length() - 1); //remove \n at the end;
    }

    private static class ProcessKiller extends TimerTask {
        Process p;
        boolean hasKilled = false;

        ProcessKiller(Process p){
            this.p = p;
        }

        @Override
        public void run(){
            p.destroy();
            hasKilled = true;
        }

        public boolean getHasKilled(){
            return hasKilled;
        }
    }

}
