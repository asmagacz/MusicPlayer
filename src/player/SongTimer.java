package player;

import java.util.concurrent.TimeUnit;

public class SongTimer {

    public SongTimer() {
    }

    public String showTimer() {
            long displayMinutes = 0;
            long starttime = System.currentTimeMillis();
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long timepassed = System.currentTimeMillis() - starttime;
                    long secondspassed = timepassed / 1000;
                    if (secondspassed == 60) {
                        secondspassed = 0;
                        starttime = System.currentTimeMillis();
                    }
                    if ((secondspassed % 60) == 0)
                        displayMinutes++;

                    System.out.println(displayMinutes + ":" + secondspassed);
                    return displayMinutes + ":" + secondspassed;
                }
    }

}
