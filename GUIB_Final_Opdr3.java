
/**
 * Write a description of class GUIB_Final here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class GUIB_Final_Opdr3
{
    public static ArrayList<String> zin;

    /**
     *  Plaatst een cijfer 
     *  in een bepaald display
     */
    public static void O_1_plaatsenVanEenCijfer(int cijfer, int bit)
    {
        IO.writeShort(bit, cijfer);
    }

    /**
     *  Maakt de 7 segment-displays
     *  schoon
     */
    public static void O_2a_maakDisplaysSchoon()
    {
        IO.writeShort(0x10, 0x100);
        IO.writeShort(0x12, 0x100);
        IO.writeShort(0x14, 0x100);
        IO.writeShort(0x16, 0x100);
        IO.writeShort(0x18, 0x100);
        IO.writeShort(0x20, 0x100);
        IO.writeShort(0x22, 0x100);
        IO.writeShort(0x24, 0x100);
        IO.writeShort(0x30, 0x100);
        IO.writeShort(0x32, 0x100);
        IO.writeShort(0x34, 0x100);
    }

    /**
     *  Maakt alles efficiënter
     *  schoon  
     */
    public static void O_2b_maakSchoonBeter()
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

    /**
     *  Plaatst een Integer in de 7-segment displays
     */
    public static void schrijfInt(int getal)
    {
        int eentallen;
        int tientallen;
        int honderdtallen;
        int duizendtallen;
        int tienduizendtallen;  
        O_2a_maakDisplaysSchoon();
        if(getal > 9999)
        {
            tienduizendtallen = getal / 10000;
            IO.writeShort(0x18, tienduizendtallen);
            getal = getal - (tienduizendtallen * 10000);
            duizendtallen = getal / 1000;
            IO.writeShort(0x16, duizendtallen);
            getal = getal - (duizendtallen * 1000);
            honderdtallen = getal / 100;
            IO.writeShort(0x14, honderdtallen);
            getal = getal - (honderdtallen * 100);
            tientallen = getal / 10;
            IO.writeShort(0x12, tientallen);
            getal = getal - (tientallen * 10);
            eentallen = getal;
            IO.writeShort(0x10, eentallen);
        }
        else if(getal > 999)
        {
            duizendtallen = getal / 1000;
            IO.writeShort(0x16, duizendtallen);
            getal = getal - (duizendtallen * 1000);
            honderdtallen = getal / 100;
            IO.writeShort(0x14, honderdtallen);
            getal = getal - (honderdtallen * 100);
            tientallen = getal / 10;
            IO.writeShort(0x12, tientallen);
            getal = getal - (tientallen * 10);
            eentallen = getal;
            IO.writeShort(0x10, eentallen);
        }
        else if(getal > 99)
        {
            honderdtallen = getal / 100;
            IO.writeShort(0x14, honderdtallen);
            getal = getal - (honderdtallen * 100);
            tientallen = getal / 10;
            IO.writeShort(0x12, tientallen);
            getal = getal - (tientallen * 10);
            eentallen = getal;
            IO.writeShort(0x10, eentallen);
        }
        else if(getal > 9)
        {
            tientallen = getal / 10;
            IO.writeShort(0x12, tientallen);
            getal = getal - (tientallen * 10);
            eentallen = getal;
            IO.writeShort(0x10, eentallen);
        }
        else
        {
            eentallen = getal;
            IO.writeShort(0x10, eentallen);
        }
    }

    public static void plaatsDouble(double getal)
    {
        int eentallen;
        int tientallen;
        int honderdtallen;
        int duizendtallen;
        int tienduizendtallen;  
        
        double teSchrijven = getal * 10;
        int rekenGetal = (int) teSchrijven;

        if(rekenGetal > 9999)
        {
            tienduizendtallen = rekenGetal / 10000;
            IO.writeShort(0x18, tienduizendtallen);
            rekenGetal = rekenGetal - (tienduizendtallen * 10000);
            duizendtallen = rekenGetal / 1000;
            IO.writeShort(0x16, duizendtallen);
            rekenGetal = rekenGetal - (duizendtallen * 1000);
            honderdtallen = rekenGetal / 100;
            IO.writeShort(0x14, honderdtallen);
            rekenGetal = rekenGetal - (honderdtallen * 100);
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x12, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x12, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x12, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x12, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x12, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x12, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x12, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x12, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x12, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x12, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x10, eentallen);
        }
        else if(rekenGetal > 999)
        {
            duizendtallen = rekenGetal / 1000;
            IO.writeShort(0x16, duizendtallen);
            rekenGetal = rekenGetal - (duizendtallen * 1000);
            honderdtallen = rekenGetal / 100;
            IO.writeShort(0x14, honderdtallen);
            rekenGetal = rekenGetal - (honderdtallen * 100);
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x12, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x12, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x12, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x12, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x12, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x12, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x12, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x12, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x12, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x12, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x10, eentallen);
        }
        else if(rekenGetal > 99)
        {
            honderdtallen = rekenGetal / 100;
            IO.writeShort(0x14, honderdtallen);
            rekenGetal = rekenGetal - (honderdtallen * 100);
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x12, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x12, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x12, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x12, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x12, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x12, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x12, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x12, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x12, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x12, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x10, eentallen);
        }
        else if(rekenGetal > 9)
        {
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x18, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x16, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x16, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x16, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x16, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x16, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x16, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x16, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x16, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x16, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x10, eentallen);
        }
        else
        {
            eentallen = rekenGetal;
            IO.writeShort(0x10, eentallen);
        }
    }

    public static void plaatsTekst(String t)
    {
        int lengte = t.length();
        for(int index = 0; index < lengte; index++)
        {
            IO.writeShort(0x40, t.charAt(index));
            if(index == 20)
            {
                IO.writeShort(0x40, '\n');
            }
            if(index == 40)
            {
                IO.writeShort(0x40, '\n');
            }
        }

    }

    public static void plaatsDoubleLinks(double getal)
    {
        int eentallen;
        int tientallen;
        int honderdtallen;
        int duizendtallen;
        int tienduizendtallen;  
        double teSchrijven = getal * 10;
        int rekenGetal = (int) teSchrijven;
        if(rekenGetal > 99)
        {
            honderdtallen = rekenGetal / 100;
            IO.writeShort(0x24, honderdtallen);
            rekenGetal = rekenGetal - (honderdtallen * 100);
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x22, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x22, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x22, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x22, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x22, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x22, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x22, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x22, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x22, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x22, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x20, eentallen);
        }
        else if(rekenGetal > 9)
        {
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x22, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x22, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x22, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x22, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x22, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x22, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x22, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x22, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x22, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x22, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x20, eentallen);
        }
        else
        {
            eentallen = rekenGetal;
            IO.writeShort(0x20, eentallen);
        }   
    }
    
    public static void plaatsDoubleRechts(double getal)
    {
        int eentallen;
        int tientallen;
        int honderdtallen;
        int duizendtallen;
        int tienduizendtallen;  
        double teSchrijven = getal * 10;
        int rekenGetal = (int) teSchrijven;
        if(rekenGetal > 99)
        {
            honderdtallen = rekenGetal / 100;
            IO.writeShort(0x34, honderdtallen);
            rekenGetal = rekenGetal - (honderdtallen * 100);
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x32, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x32, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x32, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x32, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x32, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x32, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x32, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x32, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x32, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x32, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x30, eentallen);
        }
        else if(rekenGetal > 9)
        {
            tientallen = rekenGetal / 10;
            if(tientallen == 1)
            {
                IO.writeShort(0x32, 0x100|0x86);
            }
            else if(tientallen == 2)
            {
                IO.writeShort(0x32, 0x100|0xDB);
            }
            else if(tientallen == 3)
            {
                IO.writeShort(0x32, 0x100|0xCF);
            }
            else if(tientallen == 4)
            {
                IO.writeShort(0x32, 0x100|0xE6);
            }
            else if(tientallen == 5)
            {
                IO.writeShort(0x32, 0x100|0xED);
            }
            else if(tientallen == 6)
            {
                IO.writeShort(0x32, 0x100|0xFD);
            }
            else if(tientallen == 7)
            {
                IO.writeShort(0x32, 0x100|0x87);
            }
            else if(tientallen == 8)
            {
                IO.writeShort(0x32, 0x100|0xFF);
            }
            else if(tientallen == 9)
            {
                IO.writeShort(0x32, 0x100|0xEF);
            }
            else if(tientallen == 0)
            {
                IO.writeShort(0x32, 0x100|0xBF);
            }
            else{}
            rekenGetal = rekenGetal - (tientallen * 10);
            eentallen = rekenGetal;
            IO.writeShort(0x30, eentallen);
        }
        else
        {
            eentallen = rekenGetal;
            IO.writeShort(0x30, eentallen);
        }   
    }
}
