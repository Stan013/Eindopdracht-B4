
/**
 * Write a description of class AwsApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MeasurementTest
{
    public MeasurementTest()
    {
    }

    public void testDatabaseConnectionRaw()
    {
        WeatherStation ws = new WeatherStation();
        RawMeasurement rawMeas = ws.getMostRecentMeasurement();
        System.out.println(rawMeas.toString());
    }
    
    public void testDatabaseConnectionOmgerekend()
    {
        WeatherStation ws = new WeatherStation();
        RawMeasurement rawMeas = ws.getMostRecentMeasurement();
        Omrekening weerS = new Omrekening();
        Measurement test = new Measurement(rawMeas, weerS);
        System.out.println(test.weerWeergeven());
    }
    
}       

