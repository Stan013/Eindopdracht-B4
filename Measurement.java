
/**
 * Write a description of class Wtest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Measurement

{
    private double luchtdruk;
    private double binnentemperatuur;
    private String luchtvochtigheidBinnen;
    private double buitentemperatuur;
    private String luchtvochtigheidBuiten;
    private double windSnelheid;
    private String windRichting;
    private double regenclicks;
    private double uvlevel;
    private double zonnestraling;
    private double batteryLevel;
    private String sunrise;
    private String sunset;
    private double windChill;
    private double dewPoint;

    public Measurement(RawMeasurement rm, Omrekening ws)
    {
        RawMeasurement rawMeas = rm;
        Omrekening wS = ws;
        luchtdruk = wS.luchtdruk(rawMeas.getBarometer());
        binnentemperatuur = wS.tempBinnen(rawMeas.getInsideTemp());
        luchtvochtigheidBinnen = wS.humBinnen(rawMeas.getInsideHum());
        buitentemperatuur= wS.tempBuiten(rawMeas.getOutsideTemp());
        luchtvochtigheidBuiten = wS.humBuiten(rawMeas.getOutsideHum());
        windSnelheid = wS.windSnelheid(rawMeas.getWindSpeed());
        windRichting = wS.windRichting(rawMeas.getWindDir());
        regenclicks = wS.regenval(rawMeas.getRainRate());
        uvlevel = wS.uvLevel(rawMeas.getUVLevel());
        zonnestraling = wS.zonStraling(rawMeas.getSolarRad());
        batteryLevel = wS.battLevel(rawMeas.getBattLevel());
        sunrise = wS.sunrise(rawMeas.getSunrise());
        sunset = wS.sunset(rawMeas.getSunset());
        dewPoint = wS.dewPoint(buitentemperatuur);
        windChill = wS.windChill(buitentemperatuur, windSnelheid);
    }

    public String weerWeergeven()
    {
        String s = "Omgerekende meting:" 
        +"\nLuchtdruk = \t\t\t" +luchtdruk+ " hPa"
        +"\nBinnentemperatuur = \t\t" +binnentemperatuur+ " graden Celcius"
        +"\nLuchtvochtigheid binnen = \t" +luchtvochtigheidBinnen
        +"\nBuitentemperatuur = \t\t" +buitentemperatuur+ " graden Celcius"
        +"\nLuchtvochtigheid buiten = \t" +luchtvochtigheidBuiten
        +"\nWindsnelheid = \t\t\t" +windSnelheid+ " m/s"
        +"\nWindrichting = \t\t\t" +windRichting
        +"\nHet regent \t\t\t" +regenclicks+ " mm/u"
        +"\nUV index = \t\t\t" +uvlevel
        +"\nZonnestraling = \t\t" +zonnestraling+ " Watt/m2"
        +"\nBatterijspanning = \t\t" +batteryLevel+ " V"
        +"\nZonsopkomst om \t\t\t" +sunrise
        +"\nZonsondergang om \t\t" +sunset
        +"\nGevoelstemperatuur = \t\t" + windChill + " graden Celcius"
        +"\nDewpoint = \t\t\t" + dewPoint + " graden Celcius"
        +"\n ";
        return s;
    }
}
