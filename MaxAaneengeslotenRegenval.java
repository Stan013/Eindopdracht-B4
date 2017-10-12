
/**
 * Klasse laat de maximale aaneengesloten regenval binnen een bepaalde periode weer
 * @param d1    Begindag meting "YYYY-MM-DD" (00:00:00)
 * @param d2    Einddag meting "YYYY-MM-DD" (23:59:59)
 *
 * @author Marck de Bont
 * @version 1.0
 */

import java.util.*;
import java.time.*;

public class MaxAaneengeslotenRegenval
{
    public WeatherStation ws;
    
    public MaxAaneengeslotenRegenval()
    {
        ws = new WeatherStation();
    }
    
    public void maxRegenvalAaneengesloten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double maxRegenval = 0;
        double huidigHoogste = 0;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short regenval = (arrayListAll.get(i)).getRainRate();
            double regenvalDouble = (double) regenval;
            if(regenval > 10000)
            {
                //Ireële waarde
            }
            else if(regenval == 0)
            {
                if(huidigHoogste > maxRegenval)
                {
                    maxRegenval = huidigHoogste;
                    
                }else{}
                huidigHoogste = 0;
            }
            else
            {
                
                huidigHoogste += regenvalDouble;
            }
        }
        if(huidigHoogste > maxRegenval)
        {
            maxRegenval = huidigHoogste;
        }else{}
        maxRegenval = maxRegenval / 60.0;
        maxRegenval = maxRegenval * 25.4;
        maxRegenval = Math.round(maxRegenval * 10)/10.0;
        System.out.println("Maximale aaneengesloten regen die gevallen is: " +maxRegenval+" mm." );
    }
}
