
/**
 * Write a description of class Weerstation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Omrekening
{
    /**
     * Luchtdruk 
     */
    public double luchtdruk(short mval)
    {
        double barometer = (mval / 1000.0); 
        double luchtdrukLang = (barometer * 33.863886666667);
        double luchtdruk = Math.round(luchtdrukLang*10.0)/10.0;
        return luchtdruk;
    }

    /**
     * Binnentemperatuur
     */
    public double tempBinnen(short mval)
    {
        double tempFahrenheit = (mval / 10.0);
        double tempCelcius = (tempFahrenheit - 32) / 1.8;
        tempCelcius = Math.round(tempCelcius * 10.0)/10.0;
        return tempCelcius;
    }

    /**
     * Luchtvochtigheid binnen
     */
    public String humBinnen(short mval)
    {
        String luchtvochtBinnen = mval + " %" ;
        return luchtvochtBinnen;
    }

    /**
     * Buitentemperatuur
     */
    public double tempBuiten(short mval)
    {
        double tempFahrenheit = (mval / 10.0);
        double tempCelcius = (tempFahrenheit - 32) / 1.8;
        tempCelcius = Math.round(tempCelcius * 10.0)/10.0;
        return tempCelcius;
    }

    /**
     * Luchtvochtigheid buiten
     */
    public String humBuiten(short mval)
    {
        String luchtvochtBuiten = mval + " %" ;
        return luchtvochtBuiten;
    }

    /**
     * Windsnelheid
     */
    public double windSnelheid(short mval)
    {
        double windV = mval;
        double windKmH = (windV * 1.6);
        double windMps = (windKmH / 3.6);
        windMps = Math.round(windMps * 100.0) / 100.0;
        return windMps;
    }

    /**
     * Windrichting
     */
    public String windRichting(short mval)
    {
        double graden = (mval % 360.0);
        if (graden > 337.5 || graden < 22.5 && graden >0){
            return "N" ;
        }else if (graden > 22.5 && graden < 67.5){
            return "NO" ;
        }else if (graden > 67.5 && graden < 112.5){
            return "O" ;
        }else if (graden > 112.5 && graden < 157.5){
            return "ZO" ;
        }else if (graden > 157.5 && graden < 202.5){
            return "Z" ;
        }else if (graden > 202.5 && graden < 247.5){
            return "ZW" ;
        }else if (graden > 247.5 && graden < 292.5){
            return "W" ;
        }else if (graden > 292.5 && graden < 337.5){
            return "NW" ;
        }else{
            return "Er staat geen wind";
        } 
    }

    /**
     * Regenval
     */
    public double regenval(short mval)
    {
        double inchPerUur = mval/100.0;
        double mmPerUur = inchPerUur * 25.4;
        mmPerUur = Math.round(mmPerUur * 100.0) / 100.0;
        return mmPerUur;
    }

    /**
     * UV level
     */
    public double uvLevel(short mval)
    {
        double uvIndex = mval / 10.0;
        return uvIndex; 
    } 

    /**
     * Straling van de zon
     */
    public double zonStraling(short mval)
    {
        return mval;
    }

    /**
     * Batterijlevel
     */
    public double battLevel(short mval)
    {
        double battLevel = ((mval * 300.0) / 512.0) /100.0;
        battLevel = Math.round(battLevel * 100.0) / 100.0;
        return battLevel;
    }

    /**
     * Zonsopgang
     */
    public String sunrise(short mval)
    {
        int aantalUren = mval / 100;
        int uren = aantalUren * 100;
        int aantalMinuten = mval - uren;
        if(aantalUren<10 && aantalMinuten<10){
            return "0" + aantalUren + ":0" + aantalMinuten;
        }
        else if(aantalUren<10){
            return "0" + aantalUren + ":" + aantalMinuten;
        }
        else if(aantalMinuten<10){
            return aantalUren + ":0" + aantalMinuten;
        } 
        else{
            return aantalUren + ":" + aantalMinuten;
        }
    }

    /**
     * Zonsondergang
     */
    public String sunset(short mval)
    {
        int aantalUren = mval / 100;
        int uren = aantalUren * 100;
        int aantalMinuten = mval - uren;
        if(aantalUren<10 && aantalMinuten<10){
            return "0" + aantalUren + ":0" + aantalMinuten;
        }
        else if(aantalUren<10){
            return "0" + aantalUren + ":" + aantalMinuten;
        }
        else if(aantalMinuten<10){
            return aantalUren + ":0" + aantalMinuten;
        } 
        else{
            return aantalUren + ":" + aantalMinuten;
        }
    }

    /**
     * Wind Chill
     */
    public double windChill(double tempCel, double windMps)
    {
        double tempCelcius = tempCel;
        double V = windMps;
        double windchill = 13.12 + ((0.6215 * tempCelcius) - (13.96 * Math.pow(V, 0.16))) + (0.4867 * (tempCelcius * Math.pow(V,0.16))); 
        windchill = Math.round(windchill * 10.0)/10.0;
        return windchill;
    }

    /**
     * Dewpoint
     */
    public double dewPoint(double tempCel)
    {
        double tempCelcius = tempCel;
        double a = 6.089613;
        double m = 7.33502;
        double tn = 230.3921;
        double teller = m * tempCelcius;
        double noemer = tempCelcius + tn;
        double macht = teller / noemer;
        double dampDruk = a * Math.pow(10, macht);
        double numerator = 243.12* Math.log(dampDruk) - 440.1;
        double denominator = 19.43 - Math.log(dampDruk);
        double dewPoint = numerator / denominator;
        dewPoint = Math.round(dewPoint * 10.0) / 10.0;
        return dewPoint;
    }

}
