
/**
 * Write a description of class Statistieken here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import java.time.*;

public class Statistieken
{
    public WeatherStation ws;
    public Omrekening calculator;

    public Statistieken()
    {
        ws = new WeatherStation();
        calculator = new Omrekening();
    }

    
    
    //BUITENTEMPERATUUR
    /**
     *  Methode laat de hoogst gemeten buitentemperatuur tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeTempBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short tempBuiten = (arrayListAll.get(index)).getOutsideTemp();
            if(tempBuiten > 1200)
            {
                //Ireële waarde (ongeveer 50 graden celcius) 
            }
            else if(tempBuiten > hoogste)
            {
                hoogste = tempBuiten;
            }
            else{}
            index++;
        }

        double hoogsteTemperatuur = calculator.tempBuiten(hoogste);
        System.out.println("Hoogste temperatuur gemeten: \t" + hoogsteTemperatuur + " graden Celcius");
    }

    /**
     *  Methode geeft de gemiddelde buitentemperatuur van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde buitentemperatuur uit de ArrayList (double)
     */
    public double gemTemperatuurBuiten(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short tempBuiten = (arrayListAll.get(i)).getOutsideTemp();
            if(tempBuiten > 1200)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((tempBuiten - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.tempBuiten(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten buitentemperatuur tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusTemperatuurBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short tempBuiten = (arrayListAll.get(i)).getOutsideTemp();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short tempBuiten1 = (arrayListAll.get(j)).getOutsideTemp();
                if(tempBuiten > 1200 || tempBuiten1 > 1200)
                {
                    //Ireële waardes
                }
                else if(tempBuiten == tempBuiten1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getOutsideTemp();
            }
        }
        double maxValueOmgerekend = calculator.tempBuiten(maxValue);
        System.out.println("De vaakst voorkomende temperatuur is:");
        System.out.println(maxValueOmgerekend + " graden Celcius.");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten buitentemperatuur tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingTempBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemTempBui = gemTemperatuurBuiten(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short tempBuiten = (arrayListAll.get(i)).getOutsideTemp();
            double temperatuur = calculator.tempBuiten(tempBuiten);
            if(tempBuiten > 1200)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = temperatuur - gemTempBui;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde buitentemperatuur tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeBuitenTemperatuur(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short tempBuiten = arrayListAll.get(i).getOutsideTemp();
            if(tempBuiten > 1200)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((tempBuiten - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.tempBuiten(gemiddelde);
        average = Math.round(average*10)/10.0;
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de buitentemperatuur tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanBuitenTemperatuur(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListBuitenTemp = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short buitenTemp = (arrayListAll.get(i)).getOutsideTemp();
            arrayListBuitenTemp.add(buitenTemp);
        }
        Collections.sort(arrayListBuitenTemp);
        int middle = arrayListBuitenTemp.size()/2;
        if (arrayListBuitenTemp.size()%2 == 1) {
            System.out.println("De mediaan is : " + arrayListBuitenTemp.get(middle));
        } else {
            System.out.println("De mediaan is : " + (arrayListBuitenTemp.get(middle-1) + arrayListBuitenTemp.get(middle)) / 2.0);
        }
    }
    
    /**
     *  Methode geeft de laagste buitentemperatuur tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestBuitenTemperatuur(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getOutsideTemp();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short tempBuiten = (arrayListAll.get(i)).getOutsideTemp();
            if(tempBuiten < kleinste)
                kleinste = tempBuiten;
            else{}
        }
        
        System.out.println("Kleinste getal is : " + kleinste);
    }
    
    
    //LUCHTDRUK
    /**
     *  Methode laat de hoogst gemeten luchtdruk tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeLuchtdruk(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short luchtdruk = (arrayListAll.get(index)).getBarometer();
            if(luchtdruk > 31500 || luchtdruk < 28500)
            {
                //Ireële waarde (ongeveer 50 graden celcius) 
            }
            else if(luchtdruk > hoogste)
            {
                hoogste = luchtdruk;
            }
            else{}
            index++;
        }

        double hoogsteLuchtdruk = calculator.luchtdruk(hoogste);
        System.out.println("Hoogste luchtdruk gemeten: \t" + hoogsteLuchtdruk + " hPa");
    }

    /**
     *  Methode geeft de gemiddelde luchtdruk van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde luchtdruk uit de ArrayList (double)
     */
    public double gemLuchtdruk(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short luchtdruk = (arrayListAll.get(i)).getBarometer();
            if(luchtdruk > 31500 || luchtdruk < 28500)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((luchtdruk - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.luchtdruk(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten luchtdruk tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusLuchtdruk(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short luchtdruk = (arrayListAll.get(i)).getBarometer();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short luchtdruk1 = (arrayListAll.get(j)).getBarometer();
                if(luchtdruk > 31500 || luchtdruk1 > 31500 || luchtdruk < 28500 || luchtdruk1 < 28500)
                {
                    //Ireële waardes
                }
                else if(luchtdruk == luchtdruk1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getBarometer();
            }
        }
        double maxValueOmgerekend = calculator.luchtdruk(maxValue);
        System.out.println("De vaakst voorkomende luchtdruk is:");
        System.out.println(maxValueOmgerekend + " hPa.");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten luchtdruk tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingLuchtdruk(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemLuchtdruk = gemLuchtdruk(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short barometer = (arrayListAll.get(i)).getBarometer();
            if(barometer > 31500 || barometer < 28500)
            {
                //Ireële waarde
            }
            else
            {
                double luchtdruk = calculator.luchtdruk(barometer);
                double afwijking = luchtdruk - gemLuchtdruk;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: "+ standaardafwijking);
    }
   
    /**
     *  Methode geeft de gemiddelde luchtdruk tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeLuchtdruk(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short barometer = arrayListAll.get(i).getBarometer();
            if(barometer > 31500 || barometer < 28500)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((barometer - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.luchtdruk(gemiddelde);
        average = Math.round(average*10)/10.0;
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de luchtdruk tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanLuchtdruk(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListLuchtdruk = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short barometer = (arrayListAll.get(i)).getBarometer();
            arrayListLuchtdruk.add(barometer);
        }
        Collections.sort(arrayListLuchtdruk);
        int middle = arrayListLuchtdruk.size()/2;
        if (arrayListLuchtdruk.size()%2 == 1) {
            System.out.println("De mediaan is : " + arrayListLuchtdruk.get(middle));
        } else {
            System.out.println("De mediaan is : " + (arrayListLuchtdruk.get(middle-1) + arrayListLuchtdruk.get(middle)) / 2.0);
        }
    }
    
    /**
     *  Methode geeft de laagste luchtdruk tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestLuchtdruk(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getBarometer();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short barometer = (arrayListAll.get(i)).getBarometer();
            if(barometer < kleinste)
                kleinste = barometer;
            else{}
        }
        double kleinsteLuchtdruk = calculator.luchtdruk(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteLuchtdruk);
    }
    
    
    
    //BINNENTEMPERATUUR
    /**
     *  Methode laat de hoogst gemeten binnentemperatuur tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeTempBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short tempBinnen = (arrayListAll.get(index)).getInsideTemp();
            if(tempBinnen > 1200)
            {
                //Ireële waarde (ongeveer 50 graden celcius) 
            }
            else if(tempBinnen > hoogste)
            {
                hoogste = tempBinnen;
            }
            else{}
            index++;
        }

        double hoogsteTempBinnen= calculator.tempBinnen(hoogste);
        System.out.println("Hoogste binnentemperatuur gemeten: \t" + hoogsteTempBinnen + " graden Celcius");
    }

    /**
     *  Methode geeft de gemiddelde binnentemperatuur van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde binnentemperatuur uit de ArrayList (double)
     */
    public double gemTempBinnen(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short tempBinnen = (arrayListAll.get(i)).getInsideTemp();
            if(tempBinnen > 1200)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((tempBinnen - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.tempBinnen(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten binnentemperatuur tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusTempBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short tempBinnen = (arrayListAll.get(i)).getInsideTemp();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short tempBinnen1 = (arrayListAll.get(j)).getInsideTemp();
                if(tempBinnen > 1200 || tempBinnen1 > 1200)
                {
                    //Ireële waardes
                }
                else if(tempBinnen == tempBinnen1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getInsideTemp();
            }
        }
        double maxValueOmgerekend = calculator.tempBinnen(maxValue);
        System.out.println("De vaakst voorkomende binnentemperatuur is:");
        System.out.println(maxValueOmgerekend + " gradenCelcius.");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten binnentemperatuur tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingTempBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemTempBinnen = gemTempBinnen(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short tempBinnen = (arrayListAll.get(i)).getInsideTemp();
            if(tempBinnen > 1200)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = tempBinnen - gemTempBinnen;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: "+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde binnentemperatuur tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeBinnenTemperatuur(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short tempBinnen = arrayListAll.get(i).getInsideTemp();
            if(tempBinnen > 1200)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((tempBinnen - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.tempBinnen(gemiddelde);
        average = Math.round(average*10)/10.0;
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de binnentemperatuur tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanBinnenTemperatuur(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListBinnenTemp = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short binnenTemp = (arrayListAll.get(i)).getInsideTemp();
            arrayListBinnenTemp.add(binnenTemp);
        }
        Collections.sort(arrayListBinnenTemp);
        int middle = arrayListBinnenTemp.size()/2;
        if (arrayListBinnenTemp.size()%2 == 1) {
            System.out.println("De mediaan is : " + arrayListBinnenTemp.get(middle));
        } else {
            System.out.println("De mediaan is : " + (arrayListBinnenTemp.get(middle-1) + arrayListBinnenTemp.get(middle)) / 2.0);
        }
    }
    
    /**
     *  Methode geeft de laagste binnentemperatuur tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestBinnenTemperatuur(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getInsideTemp();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short tempBinnen = (arrayListAll.get(i)).getInsideTemp();
            if(tempBinnen < kleinste)
                kleinste = tempBinnen;
            else{}
        }
        double kleinsteTemp = calculator.tempBinnen(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteTemp);
    }
    
    
    
    //LUCHTVOCHTIGHEID BINNEN
    /**
     *  Methode laat de hoogst gemeten luchtvochtigheid binnen tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeHumBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short humBinnen = (arrayListAll.get(index)).getInsideHum();
            if(humBinnen > 80 || humBinnen < 20)
            {
                //Ireële waarde (binnenluchtvochtigheid zit meestal tussen 40%-60%)
                //              (Er is een marge ingebouwd.)
            }
            else if(humBinnen > hoogste)
            {
                hoogste = humBinnen;
            }
            else{}
            index++;
        }

        String hoogsteHumBinnen = calculator.humBinnen(hoogste);
        System.out.println("Hoogste luchtvochtigheid binnen gemeten: \t" + hoogsteHumBinnen);
    }

    /**
     *  Methode geeft de gemiddelde luchtvochtigheid binnen van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde luchtvochtigheid binnen uit de ArrayList (double)
     */
    public double gemHumBinnen(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short humBinnen = (arrayListAll.get(i)).getInsideHum();
            if(humBinnen > 80 || humBinnen < 20)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((humBinnen - huidigGem)/aantal);
                aantal++;
            }
        }
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten luchtvochtigheid binnen tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusHumBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short humBinnen = (arrayListAll.get(i)).getInsideHum();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short humBinnen1 = (arrayListAll.get(j)).getInsideHum();
                if(humBinnen < 20 || humBinnen > 80 || humBinnen1 < 20 || humBinnen1 > 80)
                {
                    //Ireële waardes
                }
                else if(humBinnen == humBinnen1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getInsideHum();
            }
        }
        String maxValueOmgerekend = calculator.humBinnen(maxValue);
        System.out.println("De vaakst voorkomende luchtvochtigheid binnen is:");
        System.out.println(maxValueOmgerekend);
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten luchtvochtigheid binnen tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingHumBinnen(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemHumBinnen = gemHumBinnen(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short humBinnen = (arrayListAll.get(i)).getInsideHum();
            if(humBinnen < 20 || humBinnen > 80)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = humBinnen - gemHumBinnen;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde luchtvochtigheid binnen tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeInsideHum(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short humBinnen = arrayListAll.get(i).getInsideHum();
            if(humBinnen > 80 || humBinnen < 20)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((humBinnen - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        String average = calculator.humBinnen(gemiddelde);
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de luchtvochtigheid binnen tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanBinnenHum(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListBinnenHum = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short insideHum = (arrayListAll.get(i)).getInsideHum();
            arrayListBinnenHum.add(insideHum);
        }
        Collections.sort(arrayListBinnenHum);
        int middle = arrayListBinnenHum.size()/2;
        if (arrayListBinnenHum.size()%2 == 1) {
            System.out.println("De mediaan is : " + arrayListBinnenHum.get(middle) + "%");
        } else {
            System.out.println("De mediaan is : " + (arrayListBinnenHum.get(middle-1) + arrayListBinnenHum.get(middle)) / 2.0 + "%");
        }
    }
    
    /**
     *  Methode geeft de laagste luchtvochtigheid binnen tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestBinnenHum(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getInsideHum();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short humBinnen = (arrayListAll.get(i)).getInsideHum();
            if(humBinnen < kleinste)
                kleinste = humBinnen;
            else{}
        }
        String kleinsteHum = calculator.humBinnen(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteHum);
    }
    
    
    
    //LUCHTVOCHTIGHEID BUITEN
    /**
     *  Methode laat de hoogst gemeten luchtvochtigheid buiten tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeHumBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short humBuiten = (arrayListAll.get(index)).getOutsideHum();
            if(humBuiten > 100 || humBuiten < 0)
            {
                //Ireële waarde 
            }
            else if(humBuiten > hoogste)
            {
                hoogste = humBuiten;
            }
            else{}
            index++;
        }

        String hoogsteHumBuiten = calculator.humBinnen(hoogste);
        System.out.println("Hoogste luchtvochtigheid buiten gemeten: \t" + hoogsteHumBuiten);
    }

    /**
     *  Methode geeft de gemiddelde luchtvochtigheid buiten van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde luchtvochtigheid buiten uit de ArrayList (double)
     */
    public double gemHumBuiten(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short humBuiten = (arrayListAll.get(i)).getOutsideHum();
            if(humBuiten > 100 || humBuiten < 0)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((humBuiten - huidigGem)/aantal);
                aantal++;
            }
        }
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten luchvochtigheid buiten tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusHumBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short humBuiten = (arrayListAll.get(i)).getOutsideHum();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short humBuiten1 = (arrayListAll.get(j)).getOutsideHum();
                if(humBuiten < 0 || humBuiten > 100 || humBuiten1 < 0 || humBuiten1 > 100)
                {
                    //Ireële waardes
                }
                else if(humBuiten == humBuiten1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getOutsideHum();
            }
        }
        String maxValueOmgerekend = calculator.humBuiten(maxValue);
        System.out.println("De vaakst voorkomende luchtvochtigheid buiten is:");
        System.out.println(maxValueOmgerekend);
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten luchtvochtigheid buiten tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingHumBuiten(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemHumBuiten = gemHumBuiten(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short humBuiten = (arrayListAll.get(i)).getOutsideHum();
            if(humBuiten < 0 || humBuiten > 100)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = humBuiten - gemHumBuiten;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde luchtvochtigheid buiten tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeOutsideHum(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short humBuiten = arrayListAll.get(i).getOutsideHum();
            if(humBuiten > 100 || humBuiten < 0)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((humBuiten - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        String average = calculator.humBuiten(gemiddelde);
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de luchtvochtigheid buiten tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanBuitenHum(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListBuitenHum = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short outsideHum = (arrayListAll.get(i)).getOutsideHum();
            arrayListBuitenHum.add(outsideHum);
        }
        Collections.sort(arrayListBuitenHum);
        int middle = arrayListBuitenHum.size()/2;
        if (arrayListBuitenHum.size()%2 == 1) {
            System.out.println("De mediaan is : " + arrayListBuitenHum.get(middle) + "%");
        } else {
            System.out.println("De mediaan is : " + (arrayListBuitenHum.get(middle-1) + arrayListBuitenHum.get(middle)) / 2.0 + "%");
        }
    }
    
    /**
     *  Methode geeft de laagste luchtvochtigheid buiten tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestBuitenHum(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getOutsideHum();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short humBuiten = (arrayListAll.get(i)).getOutsideHum();
            if(humBuiten < kleinste)
                kleinste = humBuiten;
            else{}
        }
        String kleinsteHum = calculator.humBuiten(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteHum);
    }
    
    
    
    //WINDSNELHEID
    /**
     *  Methode laat de hoogst gemeten windsnelheid tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteWaardeWindSnelheid(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short windSnelheid = (arrayListAll.get(index)).getWindSpeed();
            if(windSnelheid > 70)
            {
                //Ireële waarde (54 mph is de hoogste reële waarde ooit gemeten)
            }
            else if(windSnelheid > hoogste)
            {
                hoogste = windSnelheid;
            }
            else{}
            index++;
        }

        double hoogsteWindSnelheid = calculator.windSnelheid(hoogste);
        System.out.println("Hoogste windsnelheid gemeten: \t" + hoogsteWindSnelheid + " m/s");
    }

    /**
     *  Methode geeft de gemiddelde windsnelheid van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde windsnelheid uit de ArrayList (double)
     */
    public double gemWindSnelheid(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short windSnelheid = (arrayListAll.get(i)).getWindSpeed();
            if(windSnelheid > 70)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((windSnelheid - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.windSnelheid(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten windsnelheid tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusWindSnelheid(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short windSnelheid = (arrayListAll.get(i)).getWindSpeed();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short windSnelheid1 = (arrayListAll.get(j)).getWindSpeed();
                if(windSnelheid > 70 || windSnelheid1 > 70)
                {
                    //Ireële waardes
                }
                else if(windSnelheid == windSnelheid1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getWindSpeed();
            }
        }
        double maxValueOmgerekend = calculator.windSnelheid(maxValue);
        System.out.println("De vaakst voorkomende windsnelheid is:");
        System.out.println(maxValueOmgerekend + " m/s");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten windsnelheid tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingWindSnelheid(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemWindSnelheid = gemWindSnelheid(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short windSnelheidMpH = (arrayListAll.get(i)).getWindSpeed();
            double windSnelheidMps = calculator.windSnelheid(windSnelheidMpH);
            if(windSnelheidMpH > 70)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = windSnelheidMps - gemWindSnelheid;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde windsnelheid tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
     public void gemiddeldeWindsnelheid(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short windSnelheid = arrayListAll.get(i).getWindSpeed();
            if(windSnelheid > 70)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((windSnelheid - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.windSnelheid(gemiddelde);
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de windsnelheid tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanWindsnelheid(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListWindSnelheid = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short windSnelheid = (arrayListAll.get(i)).getWindSpeed();
            arrayListWindSnelheid.add(windSnelheid);
        }
        Collections.sort(arrayListWindSnelheid);
        int middle = arrayListWindSnelheid.size()/2;
        if (arrayListWindSnelheid.size()%2 == 1) {
            double windsnelheid = calculator.windSnelheid(arrayListWindSnelheid.get(middle));
            System.out.println("De mediaan is : " + windsnelheid + " m/s");
        } else {
            double avgMediaan = (arrayListWindSnelheid.get(middle-1) + arrayListWindSnelheid.get(middle)) / 2.0;
            short medwindSnelheid = (short)avgMediaan;
            double medWindSpeed = calculator.windSnelheid(medwindSnelheid);
            System.out.println("De mediaan is : " + medWindSpeed + " m/s");
        }
    }
    
    /**
     *  Methode geeft de laagste windsnelheid tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestWindSnelheid(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getWindSpeed();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short windSpeed = (arrayListAll.get(i)).getWindSpeed();
            if(windSpeed < kleinste)
                kleinste = windSpeed;
            else{}
        }
        double kleinsteWS = calculator.windSnelheid(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteWS + " m/s" );
    }
    
     
    
    //WINDRICHTING
    /**
     *  Methode laat de meest gemeten windrichting tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusWindRichting(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short windRichting = (arrayListAll.get(i)).getWindDir();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short windRichting1 = (arrayListAll.get(j)).getWindDir();
                if(windRichting < 0 || windRichting > 360 || windRichting1 > 360 || windRichting1 < 0)
                {
                    //Ireële waardes
                }
                else if(windRichting == windRichting1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getWindDir();
            }
        }
        String maxValueOmgerekend = calculator.windRichting(maxValue);
        System.out.println("De vaakst voorkomende windrichting is:");
        System.out.println(maxValueOmgerekend);
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    
    
    //REGENVAL
    /**
     *  Methode laat de hoogst gemeten regenval tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteRegenval(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short regenval = (arrayListAll.get(index)).getRainRate();
            if(regenval > 10000)
            {
                //Ireële waarde (10 meter per uur is niet mogelijk (hoogste 'normale' gemeten waarde is 7200))
            }
            else if(regenval > hoogste)
            {
                hoogste = regenval;
            }
            else{}
            index++;
        }

        double hoogsteRegenval = calculator.regenval(hoogste);
        System.out.println("Hoogste regenval gemeten: \t" + hoogsteRegenval + " mm/u");
    }

    /**
     *  Methode geeft de gemiddelde regenval van een ArrayList.
     *  (Hulpmethode voor de standaardafwijking)
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde regenval uit de ArrayList (double)
     */
    public double gemRegenval(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short regenval = (arrayListAll.get(i)).getRainRate();
            if(regenval > 10000)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((regenval - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.regenval(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten regenval tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusRegenval(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short regenval = (arrayListAll.get(i)).getRainRate();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short regenval1 = (arrayListAll.get(j)).getRainRate();
                if(regenval > 10000 || regenval1 > 10000)
                {
                    //Ireële waardes
                }
                else if(regenval == regenval1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getRainRate();
            }
        }
        double maxValueOmgerekend = calculator.regenval(maxValue);
        System.out.println("De vaakst voorkomende regenval is:");
        System.out.println(maxValueOmgerekend + " mm/u");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten regenval tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingRegenval(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemRegenval = gemRegenval(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short regenval = (arrayListAll.get(i)).getRainRate();
            if(regenval > 10000)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = regenval - gemRegenval;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde regenval tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
    public void gemiddeldeRegenval(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short regenval = arrayListAll.get(i).getRainRate();
            if(regenval > 10000)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((regenval - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.regenval(gemiddelde);
        System.out.println("Gemiddelde is : " + average +" mm/u");
    }

    /**
     *  Methode geeft de mediaan van de regenval tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanRegenval(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListRegenval = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short regenval = (arrayListAll.get(i)).getRainRate();
            arrayListRegenval.add(regenval);
        }
        Collections.sort(arrayListRegenval);
        int middle = arrayListRegenval.size()/2;
        if (arrayListRegenval.size()%2 == 1) {
            double regenVal = calculator.regenval(arrayListRegenval.get(middle));
            System.out.println("De mediaan is : " + regenVal + " mm/u");
        } else {
            double avgMediaan = (arrayListRegenval.get(middle-1) + arrayListRegenval.get(middle)) / 2.0;
            short medregenval = (short)avgMediaan;
            double medRegenval = calculator.regenval(medregenval);
            System.out.println("De mediaan is : " + medRegenval + " mm/u");
        }
    }
    
    /**
     *  Methode geeft de laagste regenval tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestRegenval(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getRainRate();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short regenval = (arrayListAll.get(i)).getRainRate();
            if(regenval < kleinste)
                kleinste = regenval;
            else{}
        }
        double kleinsteRegenval = calculator.regenval(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteRegenval + " mm/u" );
    }
    
    
    
    //SOLAR RADIATION
    /**
     *  Methode laat de hoogst gemeten zonnestraling tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteSolarRad(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short solarRad = (arrayListAll.get(index)).getSolarRad();
            if(solarRad > 2000)
            {
                //Ireële waarde (10 meter per uur is niet mogelijk (hoogste 'normale' gemeten waarde is 7200))
            }
            else if(solarRad > hoogste)
            {
                hoogste = solarRad;
            }
            else{}
            index++;
        }

        double hoogsteStraling = calculator.zonStraling(hoogste);
        System.out.println("Hoogste zonnestraling gemeten: \t" + hoogsteStraling + " Watt/m2");
    }

    /**
     *  Methode geeft de gemiddelde zonnestraling van een ArrayList.
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde zonnestraling uit de ArrayList (double)
     */
    public double gemStraling(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short solarRad = (arrayListAll.get(i)).getSolarRad();
            if(solarRad > 2000)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((solarRad - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.zonStraling(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten zonnestraling tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusStaling(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short solarRad = (arrayListAll.get(i)).getSolarRad();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short solarRad1 = (arrayListAll.get(j)).getSolarRad();
                if(solarRad > 2000 || solarRad > 2000)
                {
                    //Ireële waardes
                }
                else if(solarRad == solarRad1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getSolarRad();
            }
        }
        double maxValueOmgerekend = calculator.zonStraling(maxValue);
        System.out.println("De vaakst voorkomende zonnestraling is:");
        System.out.println(maxValueOmgerekend + " Watt/m2");
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten zonnestraling tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingStraling(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemStraling = gemStraling(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short solarRad = (arrayListAll.get(i)).getSolarRad();
            if(solarRad > 2000)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = solarRad - gemStraling;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde zonnestraling tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
    public void gemiddeldeStraling(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short straling = arrayListAll.get(i).getSolarRad();
            if(straling > 1500)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((straling - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.zonStraling(gemiddelde);
        System.out.println("Gemiddelde is : " + average +" Watt/m2");
    }

    /**
     *  Methode geeft de mediaan van de zonnestraling tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanStraling(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListStraling = new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short straling = (arrayListAll.get(i)).getSolarRad();
            if(straling > 1500)
            {
                //Ireële waarde
            }else{
                arrayListStraling.add(straling);
            }   
        }
            
        Collections.sort(arrayListStraling);
        int middle = arrayListStraling.size()/2;
        if (arrayListStraling.size()%2 == 1) {
            double straling = calculator.zonStraling(arrayListStraling.get(middle));
            System.out.println("De mediaan is : " + straling + " Watt/m2");
        } else {
            double avgMediaan = (arrayListStraling.get(middle-1) + arrayListStraling.get(middle)) / 2.0;
            short medstraling = (short)avgMediaan;
            double medStraling = calculator.zonStraling(medstraling);
            System.out.println("De mediaan is : " + medStraling + " Watt/m2");
        }
    }
    
    /**
     *  Methode geeft de laagste zonnestraling tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestStraling(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getSolarRad();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short straling = (arrayListAll.get(i)).getSolarRad();
            if(straling < kleinste)
                kleinste = straling;
            else{}
        }
        double kleinsteStraling = calculator.zonStraling(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteStraling + " Watt/m2" );
    }
    
    
    
    //UV LEVEL
    /**
     *  Methode laat de hoogst gemeten uv-index tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void hoogsteUVindex(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int index = 0;
        short hoogste = 0;
        while (index < arrayListAll.size())
        {
            short uvIndex = (arrayListAll.get(index)).getUVLevel();
            if(uvIndex >= 255)
            {
                //Ireële waarde 
            }
            else if(uvIndex > hoogste)
            {
                hoogste = uvIndex;
            }
            else{}
            index++;
        }

        double hoogsteUV = calculator.uvLevel(hoogste);
        System.out.println("Hoogste UV-index gemeten: \t" + hoogsteUV );
    }

    /**
     *  Methode geeft de gemiddelde UV-index van een ArrayList.
     *  
     *  @param arrayList    Een ArrayList met RawMeasurements
     *  
     *  @return             Gemiddelde UV-index uit de ArrayList (double)
     */
    public double gemUV(ArrayList<RawMeasurement> arrayList)
    {
        ArrayList<RawMeasurement> arrayListAll = arrayList;
        double huidigGem = 0;
        int aantal = 1;
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short uvIndex = (arrayListAll.get(i)).getUVLevel();
            if(uvIndex > 2000)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((uvIndex - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        huidigGem = calculator.uvLevel(gemiddelde);
        return huidigGem;       
    }
    
    /**
     *  Methode laat de meest gemeten UV-index tussen twee dagen zien (de modus).
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void modusUV(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int maxCount = 0;
        short maxValue = 0;
        for(int i = 0; i < arrayListAll.size() ; i++)
        {
            int count = 0;
            short uvLevel = (arrayListAll.get(i)).getUVLevel();
            for(int j = 0; j < arrayListAll.size(); j++)
            {
                short uvLevel1 = (arrayListAll.get(j)).getUVLevel();
                if(uvLevel == 255 || uvLevel1 == 255)
                {
                    //Ireële waardes
                }
                else if(uvLevel == uvLevel1)
                {
                    count++;
                }else{}
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = (arrayListAll.get(i)).getUVLevel();
            }
        }
        double maxValueOmgerekend = calculator.uvLevel(maxValue);
        System.out.println("De vaakst voorkomende UV index is:");
        System.out.println(maxValueOmgerekend);
        System.out.println("Deze is "+maxCount+ " keer geregistreerd.");
        System.out.println(" ");
    }
    
    /**
     *  Methode laat de standaardafwijking van de
     *  gemeten UV-index tussen twee dagen zien.
     *  
     *  @param d1   Startdag van de periode als "YYYY-MM-DD" (00:00:00)
     *  @param d2   d2 >= d1 Einddag van de periode als "YYYY-MM-DD" (23:59:59)
     */
    public void standaardAfwijkingUV(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        double gemUV = gemUV(arrayListAll);
        double somAfwijking = 0;
        double aantal = 0;
        for(int i = 0; i< arrayListAll.size(); i++)
        {
            short uvLevel = (arrayListAll.get(i)).getUVLevel();
            if(uvLevel == 255)
            {
                //Ireële waarde
            }
            else
            {
                double afwijking = uvLevel - gemUV;
                somAfwijking = somAfwijking + (afwijking*afwijking);
                aantal++;
            }
        }
        double standaardafwijking = Math.sqrt(somAfwijking / aantal);
        standaardafwijking = Math.round(standaardafwijking * 100) / 100.0;
        System.out.println("De standaardafwijking was: \t"+ standaardafwijking);
    }
    
    /**
     *  Methode geeft de gemiddelde UV-index tussen twee dagen
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59) d2 >= d1
     */
    public void gemiddeldeUVindex(String d1, String d2) 
     {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        int aantal = 0;
        double huidigGem = 0;

        for(int i=0; i< arrayListAll.size(); i++)
        {
            short uvIndex = arrayListAll.get(i).getUVLevel();
            if(uvIndex == 255)
            {
                //Ireële waarde
            }else{
                huidigGem = huidigGem + ((uvIndex - huidigGem)/aantal);
                aantal++;
            }
        }
        short gemiddelde = (short) huidigGem;
        double average = calculator.uvLevel(gemiddelde);
        System.out.println("Gemiddelde is : " + average);
    }

    /**
     *  Methode geeft de mediaan van de UV-index tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void mediaanUVindex(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        ArrayList<Short> arrayListUV= new ArrayList<Short>();
        for(int i = 0; i < arrayListAll.size(); i++)
        {
            short uvIndex = (arrayListAll.get(i)).getUVLevel();
            if(uvIndex == 255)
            {
                //Ireële waarde
            }else{
                arrayListUV.add(uvIndex);
            }   
        }
            
        Collections.sort(arrayListUV);
        int middle = arrayListUV.size()/2;
        if (arrayListUV.size()%2 == 1) {
            double uvINDEX = calculator.uvLevel(arrayListUV.get(middle));
            System.out.println("De mediaan is : " + uvINDEX);
        } else {
            double avgMediaan = (arrayListUV.get(middle-1) + arrayListUV.get(middle)) / 2.0;
            short meduv = (short)avgMediaan;
            double medUV = calculator.uvLevel(meduv);
            System.out.println("De mediaan is : " + medUV);
        }
    }
    
    /**
     *  Methode geeft de laagste UV-index tussen twee dagen
     *  
     *  @param d1   Begindag meting "YYYY-MM-DD" (00:00:00)
     *  @param d2   Einddag meting "YYYY-MM-DD" (23:59:59)
     */
    public void lowestUVindex(String d1, String d2)
    {
        ArrayList<RawMeasurement> arrayListAll = new ArrayList<RawMeasurement>();
        arrayListAll = ws.getAllMeasurementsBetween(d1, d2);
        short kleinste = (arrayListAll.get(0)).getUVLevel();
        for(int i=0; i< arrayListAll.size() ; i++)
        {
            short uvIndex = (arrayListAll.get(i)).getUVLevel();
            if(uvIndex < kleinste)
                kleinste = uvIndex;
            else{}
        }
        double kleinsteUV = calculator.uvLevel(kleinste);
        System.out.println("Kleinste getal is : " + kleinsteUV);
    }
    
    
}


















