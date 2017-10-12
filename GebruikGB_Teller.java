
/**
 * Write a description of class GebruikGB_Teller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GebruikGB_Teller
{
    public static void cijferSchrijven(int cijfer, int bit)
    {
        IO.writeShort(bit, cijfer);
    }

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
    public static void O_2a_maakSchoon()
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
     *  Teller met leidende nullen
     *  gaat lopen
     */
    public static void O_3a_tellerMetNullen()
    {
        int getal = 0;
        int eentallen = 0;
        int tientallen = 0;
        int honderdtallen = 0;
        int duizendtallen = 0;
        int tienduizendtallen = 0;

        while(getal < 99999)
        {
            int rekenGetal = getal;
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
                IO.writeShort(0x12, tientallen);
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
                IO.writeShort(0x12, tientallen);
                rekenGetal = rekenGetal - (tientallen * 10);
                eentallen = rekenGetal;
                IO.writeShort(0x10, eentallen);
                IO.writeShort(0x18, 0);
            }
            else if(rekenGetal > 99)
            {
                IO.writeShort(0x18, 0);
                IO.writeShort(0x16, 0);
                honderdtallen = rekenGetal / 100;
                IO.writeShort(0x14, honderdtallen);
                rekenGetal = rekenGetal - (honderdtallen * 100);
                tientallen = rekenGetal / 10;
                IO.writeShort(0x12, tientallen);
                rekenGetal = rekenGetal - (tientallen * 10);
                eentallen = rekenGetal;
                IO.writeShort(0x10, eentallen);
            }
            else if(rekenGetal > 9)
            {
                IO.writeShort(0x18, 0);
                IO.writeShort(0x16, 0);
                IO.writeShort(0x14, 0);
                tientallen = rekenGetal / 10;
                IO.writeShort(0x12, tientallen);
                rekenGetal = rekenGetal - (tientallen * 10);
                eentallen = rekenGetal;
                IO.writeShort(0x10, eentallen);
            }
            else
            {
                IO.writeShort(0x18, 0);
                IO.writeShort(0x16, 0);
                IO.writeShort(0x14, 0);
                IO.writeShort(0x12, 0);
                eentallen = rekenGetal;
                IO.writeShort(0x10, eentallen);
            }
            getal++;
            IO.delay(50);
        }
    }

    /**
     *  Teller met leidende nullen
     *  Op een andere manier
     */
    public static void O_3b_tellerMetNullen_Anders()
    {
        int getal = 0;
        int eentallen = 1;
        int tientallen = 0;
        int honderdtallen = 0;
        int duizendtallen = 0;
        int tienduizendtallen = 0;
        IO.writeShort(0x10, eentallen);
        IO.writeShort(0x12, tientallen);
        IO.writeShort(0x14, honderdtallen);
        IO.writeShort(0x16, duizendtallen);
        IO.writeShort(0x18, tienduizendtallen);
        while(getal < 99999)
        {
            while(eentallen < 10)
            {
                IO.writeShort(0x10, eentallen);
                eentallen++;
                IO.delay(250);
            }
            eentallen = 0;
            tientallen++;
            IO.writeShort(0x12, tientallen);            
            if(tientallen > 9)
            {
                honderdtallen++;
                IO.writeShort(0x14, honderdtallen);
                tientallen = 0;
                IO.writeShort(0x12, tientallen);
            }else{}

            if(honderdtallen > 9)
            {
                duizendtallen++;
                IO.writeShort(0x16, duizendtallen);
                honderdtallen = 0;
                IO.writeShort(0x14, honderdtallen);
            }else{}

            if(duizendtallen > 9)
            {
                duizendtallen = 0;
                IO.writeShort(0x16, duizendtallen);
                tienduizendtallen++;
                IO.writeShort(0x18, tienduizendtallen);                
            }else{}
            getal = getal + 10;
            IO.delay(50);
        }
        IO.delay(50);
        IO.writeShort(0x10,0x100);
    }

    /**
     * Teller zonder leidende nullen
     */
    public static void O_4_tellerZonderNullen()
    {
        int getal = 0;
        int eentallen = 1;
        int tientallen = 0;
        int honderdtallen = 0;
        int duizendtallen = 0;
        int tienduizendtallen = 0;
        while(getal < 99999)
        {
            while(eentallen < 10)
            {
                IO.writeShort(0x10, eentallen);
                eentallen++;
                IO.delay(250);
            }
            eentallen = 0;
            tientallen++;
            IO.writeShort(0x12, tientallen);            
            if(tientallen > 9)
            {
                honderdtallen++;
                IO.writeShort(0x14, honderdtallen);
                tientallen = 0;
                IO.writeShort(0x12, tientallen);
            }else{}

            if(honderdtallen > 9)
            {
                duizendtallen++;
                IO.writeShort(0x16, duizendtallen);
                honderdtallen = 0;
                IO.writeShort(0x14, honderdtallen);
            }else{}

            if(duizendtallen > 9)
            {
                duizendtallen = 0;
                IO.writeShort(0x16, duizendtallen);
                tienduizendtallen++;
                IO.writeShort(0x18, tienduizendtallen);                
            }else{}
            getal = getal + 10;
            IO.delay(50);
        }
        IO.delay(50);
        IO.writeShort(0x10,0x100);
    }

}

