
/**
 * Write a description of class Eindopdracht here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
public class Eindopdracht
{
    public static GebruikGB_DotDisplay dotDisplay;
    public static GUIB_Final_Opdr3 plaatsen;
    public static void O_0_maakSchoonBeter()
    {
        int bit = 0x10;
        while (bit <= 0x34)
        {
            IO.writeShort(bit, 0x100);
            bit = bit + 0x2;
        }
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
    }

    public static void eindopdracht()
    {
        tekenTabjesBoven();
        tekenTabjesRechts();
        int tabBoven = 1;
        vulTabBoven(tabBoven);
        int tabRechts = 1;
        vulTabRechts(tabRechts);
        int teller = 0;
        int tab = 1;
        O_0_maakSchoonBeter();
        tabRechts1();
        
        while(IO.readShort(0x100) == 0 && IO.readShort(0x90) == 0 && IO.readShort(0x80) == 0){
            IO.delay(1);
        }
        int nieuweTab = checkKnopLinks(tabBoven);
        if(nieuweTab != tabBoven)
        {
            tabBoven = nieuweTab; 
        } 
        nieuweTab = checkKnopRechts(tabBoven);
        if(nieuweTab != tabBoven)
        {
            tabBoven = nieuweTab; 
        }
        int nieuweTabRechts = checkKnopRood(tabRechts);
        if(nieuweTabRechts != tabRechts)
        {
            tabRechts = nieuweTabRechts;
            tabBoven = 1;
        }
        O_0_maakSchoonBeter();
        tekenTabjesBoven();
        tekenTabjesRechts();
        vulTabBoven(tabBoven);
        vulTabRechts(tabRechts);
    }

    public static void tabRechts1()
    {
        if(tab == 1)
        {
            if(tabRechts == 1)
            {
                WeatherStation ws = new WeatherStation();
                RawMeasurement rawMeas = ws.getMostRecentMeasurement();
                Omrekening weerS = new Omrekening();
                short barometer = rawMeas.getBarometer();
                short tempIn = rawMeas.getInsideTemp();
                short tempOut = rawMeas.getOutsideTemp();
                short humIn = rawMeas.getInsideHum();
                short humOut = rawMeas.getOutsideHum();
                short windSnelheid = rawMeas.getWindSpeed();
                short rainRate = rawMeas.getRainRate();
                while(tabRechts == 1)
                {
                    if(tabBoven == 1)
                    {
                        double luchtdruk = weerS.luchtdruk(barometer);
                        String t = "Luchtdruk";
                        IO.writeShort(0x40, '\n');
                        plaatsen.plaatsDouble(luchtdruk);
                    }else{
                        if(tabBoven == 2)
                        {   
                            double tempBinnen = weerS.tempBinnen(tempIn);
                            double tempBuiten = weerS.tempBuiten(tempOut);
                            String t = "Temperatuur";
                            String b = "Binnen     Buiten";
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsTekst(b);
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsTekst(t);
                            plaatsen.plaatsDoubleLinks(tempBinnen);
                            plaatsen.plaatsDoubleRechts(tempBuiten);
                        }else{
                            if(tabBoven == 3)
                            {
                                double humInside = (double) humIn;
                                double humOutside = (double) humOut;
                                String t = "Luchtvochtigheid";
                                String b = "Binnen     Buiten";
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(b);
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(t);
                                plaatsen.plaatsDoubleLinks(humInside);
                                plaatsen.plaatsDoubleRechts(humOutside);
                            }else{
                                if(tabBoven == 4)
                                {
                                    double regenval = weerS.regenval(rainRate);
                                    String t = "Neerslag" ;
                                    IO.writeShort(0x40, '\n');
                                    plaatsen.plaatsTekst(t);
                                    plaatsen.plaatsDouble(rainRate);
                                }else{
                                    if(tabBoven == 5)
                                    {
                                        double windsnelheid = weerS.windSnelheid(windSnelheid);
                                        String t = "Windsnelheid" ;
                                        IO.writeShort(0x40, '\n');
                                        plaatsen.plaatsTekst(t);
                                        plaatsen.plaatsDouble(windsnelheid);
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
            }
            public static
                if(tabRechts == 2)
                {
                    WeatherStation ws = new WeatherStation();
                    RawMeasurement rawMeas = ws.getMostRecentMeasurement();
                    Omrekening weerS = new Omrekening();
                    short barometer = rawMeas.getBarometer();
                    short tempIn = rawMeas.getInsideTemp();
                    short tempOut = rawMeas.getOutsideTemp();
                    short humIn = rawMeas.getInsideHum();
                    short humOut = rawMeas.getOutsideHum();
                    short windSnelheid = rawMeas.getWindSpeed();
                    short rainRate = rawMeas.getRainRate();
                    while(tabRechts == 1)
                    {
                        if(tabBoven == 1)
                        {
                            double luchtdruk = weerS.luchtdruk(barometer);
                            String t = "Luchtdruk";
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsDouble(luchtdruk);
                        }else{
                            if(tabBoven == 2)
                            {   
                                double tempBinnen = weerS.tempBinnen(tempIn);
                                double tempBuiten = weerS.tempBuiten(tempOut);
                                String t = "Temperatuur";
                                String b = "Binnen     Buiten";
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(b);
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(t);
                                plaatsen.plaatsDoubleLinks(tempBinnen);
                                plaatsen.plaatsDoubleRechts(tempBuiten);
                            }else{
                                if(tabBoven == 3)
                                {
                                    double humInside = (double) humIn;
                                    double humOutside = (double) humOut;
                                    String t = "Luchtvochtigheid";
                                    String b = "Binnen     Buiten";
                                    IO.writeShort(0x40, '\n');
                                    plaatsen.plaatsTekst(b);
                                    IO.writeShort(0x40, '\n');
                                    plaatsen.plaatsTekst(t);
                                    plaatsen.plaatsDoubleLinks(humInside);
                                    plaatsen.plaatsDoubleRechts(humOutside);
                                }else{
                                    if(tabBoven == 4)
                                    {
                                        double regenval = weerS.regenval(rainRate);
                                        String t = "Neerslag" ;
                                        IO.writeShort(0x40, '\n');
                                        plaatsen.plaatsTekst(t);
                                        plaatsen.plaatsDouble(rainRate);
                                    }else{
                                        if(tabBoven == 5)
                                        {
                                            double windsnelheid = weerS.windSnelheid(windSnelheid);
                                            String t = "Windsnelheid" ;
                                            IO.writeShort(0x40, '\n');
                                            plaatsen.plaatsTekst(t);
                                            plaatsen.plaatsDouble(windsnelheid);
                                        }
                                        while(IO.readShort(0x100) == 0 && IO.readShort(0x90) == 0 && IO.readShort(0x80) == 0){
                                            IO.delay(1);
                                        }
                                        int nieuweTab = checkKnopLinks(tabBoven);
                                        if(nieuweTab != tabBoven)
                                        {
                                            tabBoven = nieuweTab; 
                                        } 
                                        nieuweTab = checkKnopRechts(tabBoven);
                                        if(nieuweTab != tabBoven)
                                        {
                                            tabBoven = nieuweTab; 
                                        }
                                        int nieuweTabRechts = checkKnopRood(tabRechts);
                                        if(nieuweTabRechts != tabRechts)
                                        {
                                            tabRechts = nieuweTabRechts;
                                            tabBoven = 1;
                                        }
                                        dotDisplay.O_2b_maakSchoonBeter();
                                        tekenTabjesBoven();
                                        tekenTabjesRechts();
                                        vulTabBoven(tabBoven);
                                        vulTabRechts(tabRechts);
                                    }
                                }
                            }
                        }
                    }
                }else{}
            }
        }
    }
    if(tabRechts == 3)
    {
        WeatherStation ws = new WeatherStation();
        RawMeasurement rawMeas = ws.getMostRecentMeasurement();
        Omrekening weerS = new Omrekening();
        short barometer = rawMeas.getBarometer();
        short tempIn = rawMeas.getInsideTemp();
        short tempOut = rawMeas.getOutsideTemp();
        short humIn = rawMeas.getInsideHum();
        short humOut = rawMeas.getOutsideHum();
        short windSnelheid = rawMeas.getWindSpeed();
        short rainRate = rawMeas.getRainRate();
        while(tabRechts == 1)
        {
            if(tabBoven == 1)
            {
                double luchtdruk = weerS.luchtdruk(barometer);
                String t = "Luchtdruk";
                IO.writeShort(0x40, '\n');
                plaatsen.plaatsDouble(luchtdruk);
            }else{
                if(tabBoven == 2)
                {   
                    double tempBinnen = weerS.tempBinnen(tempIn);
                    double tempBuiten = weerS.tempBuiten(tempOut);
                    String t = "Temperatuur";
                    String b = "Binnen     Buiten";
                    IO.writeShort(0x40, '\n');
                    plaatsen.plaatsTekst(b);
                    IO.writeShort(0x40, '\n');
                    plaatsen.plaatsTekst(t);
                    plaatsen.plaatsDoubleLinks(tempBinnen);
                    plaatsen.plaatsDoubleRechts(tempBuiten);
                }else{
                    if(tabBoven == 3)
                    {
                        double humInside = (double) humIn;
                        double humOutside = (double) humOut;
                        String t = "Luchtvochtigheid";
                        String b = "Binnen     Buiten";
                        IO.writeShort(0x40, '\n');
                        plaatsen.plaatsTekst(b);
                        IO.writeShort(0x40, '\n');
                        plaatsen.plaatsTekst(t);
                        plaatsen.plaatsDoubleLinks(humInside);
                        plaatsen.plaatsDoubleRechts(humOutside);
                    }else{
                        if(tabBoven == 4)
                        {
                            double regenval = weerS.regenval(rainRate);
                            String t = "Neerslag" ;
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsTekst(t);
                            plaatsen.plaatsDouble(rainRate);
                        }else{
                            if(tabBoven == 5)
                            {
                                double windsnelheid = weerS.windSnelheid(windSnelheid);
                                String t = "Windsnelheid" ;
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(t);
                                plaatsen.plaatsDouble(windsnelheid);
                            }
                            while(IO.readShort(0x100) == 0 && IO.readShort(0x90) == 0 && IO.readShort(0x80) == 0){
                                IO.delay(1);
                            }
                            int nieuweTab = checkKnopLinks(tabBoven);
                            if(nieuweTab != tabBoven)
                            {
                                tabBoven = nieuweTab; 
                            } 
                            nieuweTab = checkKnopRechts(tabBoven);
                            if(nieuweTab != tabBoven)
                            {
                                tabBoven = nieuweTab; 
                            }
                            int nieuweTabRechts = checkKnopRood(tabRechts);
                            if(nieuweTabRechts != tabRechts)
                            {
                                tabRechts = nieuweTabRechts;
                                tabBoven = 1;
                            }
                            dotDisplay.O_2b_maakSchoonBeter();
                            tekenTabjesBoven();
                            tekenTabjesRechts();
                            vulTabBoven(tabBoven);
                            vulTabRechts(tabRechts);
                        }
                    }
                }
            }
        }
    }else{
        if(tabRechts == 4)
        {
            WeatherStation ws = new WeatherStation();
            RawMeasurement rawMeas = ws.getMostRecentMeasurement();
            Omrekening weerS = new Omrekening();
            short barometer = rawMeas.getBarometer();
            short tempIn = rawMeas.getInsideTemp();
            short tempOut = rawMeas.getOutsideTemp();
            short humIn = rawMeas.getInsideHum();
            short humOut = rawMeas.getOutsideHum();
            short windSnelheid = rawMeas.getWindSpeed();
            short rainRate = rawMeas.getRainRate();
            while(tabRechts == 1)
            {
                if(tabBoven == 1)
                {
                    double luchtdruk = weerS.luchtdruk(barometer);
                    String t = "Luchtdruk";
                    IO.writeShort(0x40, '\n');
                    plaatsen.plaatsDouble(luchtdruk);
                }else{
                    if(tabBoven == 2)
                    {   
                        double tempBinnen = weerS.tempBinnen(tempIn);
                        double tempBuiten = weerS.tempBuiten(tempOut);
                        String t = "Temperatuur";
                        String b = "Binnen     Buiten";
                        IO.writeShort(0x40, '\n');
                        plaatsen.plaatsTekst(b);
                        IO.writeShort(0x40, '\n');
                        plaatsen.plaatsTekst(t);
                        plaatsen.plaatsDoubleLinks(tempBinnen);
                        plaatsen.plaatsDoubleRechts(tempBuiten);
                    }else{
                        if(tabBoven == 3)
                        {
                            double humInside = (double) humIn;
                            double humOutside = (double) humOut;
                            String t = "Luchtvochtigheid";
                            String b = "Binnen     Buiten";
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsTekst(b);
                            IO.writeShort(0x40, '\n');
                            plaatsen.plaatsTekst(t);
                            plaatsen.plaatsDoubleLinks(humInside);
                            plaatsen.plaatsDoubleRechts(humOutside);
                        }else{
                            if(tabBoven == 4)
                            {
                                double regenval = weerS.regenval(rainRate);
                                String t = "Neerslag" ;
                                IO.writeShort(0x40, '\n');
                                plaatsen.plaatsTekst(t);
                                plaatsen.plaatsDouble(rainRate);
                            }else{
                                if(tabBoven == 5)
                                {
                                    double windsnelheid = weerS.windSnelheid(windSnelheid);
                                    String t = "Windsnelheid" ;
                                    IO.writeShort(0x40, '\n');
                                    plaatsen.plaatsTekst(t);
                                    plaatsen.plaatsDouble(windsnelheid);
                                }
                                while(IO.readShort(0x100) == 0 && IO.readShort(0x90) == 0 && IO.readShort(0x80) == 0){
                                    IO.delay(1);
                                }
                                int nieuweTab = checkKnopLinks(tabBoven);
                                if(nieuweTab != tabBoven)
                                {
                                    tabBoven = nieuweTab; 
                                } 
                                nieuweTab = checkKnopRechts(tabBoven);
                                if(nieuweTab != tabBoven)
                                {
                                    tabBoven = nieuweTab; 
                                }
                                int nieuweTabRechts = checkKnopRood(tabRechts);
                                if(nieuweTabRechts != tabRechts)
                                {
                                    tabRechts = nieuweTabRechts;
                                    tabBoven = 1;
                                }
                                dotDisplay.O_2b_maakSchoonBeter();
                                tekenTabjesBoven();
                                tekenTabjesRechts();
                                vulTabBoven(tabBoven);
                                vulTabRechts(tabRechts);
                            }
                        }
                    }
                }
            }
        }
    }
}
}
}
}

public static int checkKnopLinks(int tabBoven)
{
if(IO.readShort(0x90) !=0)
{
tabBoven--;
if(tabBoven < 1)
{
tabBoven = 5;
}   

while(IO.readShort(0x90) == 1)
{
IO.delay(1);
}
}
return tabBoven;
}

public static int checkKnopRechts(int tabBoven)
{
if(IO.readShort(0x100) !=0)
{
tabBoven++;
if(tabBoven > 5)
{
tabBoven = 1;
}
while(IO.readShort(0x100) == 1)
{
IO.delay(1);
}
}
return tabBoven;
}

public static int checkKnopRood(int tab)
{
if(IO.readShort(0x80) !=0)
{
tab++;
if(tab > 4)
{
tab = 1;
}   
while(IO.readShort(0x80) ==1)
{
IO.delay(1);
}
}     
return tab;
}

/**
 *  Tekent de x-as op een bepaalde positie
 *  (0 <= positie < 32)
 */
public static void tekenTabjesBoven()
{
for(int x = 0; x <= 110; x++)
{
IO.writeShort(0x42, 1 << 12 | x << 5 | 5);
}
for(int x = 0; x <= 110; x = x+22)
{
for(int y = 0; y < 6; y++)
{
IO.writeShort(0x42, 1 << 12 | x << 5 | y);
}
}
}

/**
 *  Tekent de x-as op een bepaalde positie
 *  (0 <= positie < 32)
 */
public static void tekenTabjesRechts()
{
for(int y = 0; y <= 32; y++)
{
IO.writeShort(0x42, 1 << 12 | 119 << 5 |y);
}
for(int y = 0; y < 32; y = y + 8)
{
for(int x = 119; x<128;x++)
{
IO.writeShort(0x42, 1 << 12 | x << 5 | y);
}
}
}

public static void vulTabBoven(int tab)
{
int beginPos = (tab - 1) * 22;
int eindPos = tab * 22;
for(int x = beginPos; x < eindPos; x++)
{
for(int y = 0; y < 6; y++)
{
IO.writeShort(0x42, 1 << 12 | x << 5 | y);
}
}
int beginPos1 = (tab - 2) * 22+1;
for(int x = beginPos1; x < beginPos -1; x++)
{
for(int y = 0; y < 5; y++)
{
IO.writeShort(0x42, 0 << 12 | x << 5 | y);
}
}
}

public static void vulTabRechts(int tab)
{
int beginPos = (tab - 1) * 8;
int eindPos = tab * 8;
for(int y = beginPos; y < eindPos; y++)
{
for(int x = 119; x < 128 ; x++)
{
IO.writeShort(0x42, 1 << 12 | x << 5 | y);
}
}
}
}
