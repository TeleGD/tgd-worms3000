package general.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class FontUtils {

	public static final String FONT_DIRECTORY_NAME="fonts";
	public static final String FONT_PATH=FONT_DIRECTORY_NAME+File.separator;
	public static final boolean ENABLE_LOG = false;
	private static HashMap<String,TrueTypeFont> ttfMaps = new HashMap<>();
	/**
	 * Charger une police système : Arial, Kalinga, ...
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */
	public static TrueTypeFont loadSystemFont(String name, int type, int size) {
		if(ENABLE_LOG)System.out.println("FontUtils >> loadSystemFont >> name="+name+"  size="+size+ "  type="+type);

		Font fontTemp = new Font(FONT_PATH+name, type, size);

		return new TrueTypeFont(fontTemp, true);
	}

    public static  UnicodeFont loadSystemUnicodeFont(String name, int type, int size)  {
        if(ENABLE_LOG) System.out.println("FontUtils >> loadCustomFont >> name="+name+"  size="+size+ "  type="+type+" in path="+FONT_PATH);
        long time = System.currentTimeMillis();

        Font fontTemp = new Font(FONT_PATH+name, type, size);

        org.newdawn.slick.UnicodeFont uniFont = new org.newdawn.slick.UnicodeFont(fontTemp);
        uniFont.addAsciiGlyphs();
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));

        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        if(ENABLE_LOG)System.out.println("time system font = "+(System.currentTimeMillis()-time));

        return uniFont;
    }


	/**
	 * Charger une police personnalisé qui se trouve dans le répertoire font
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */

	public static TrueTypeFont loadCustomFont(String name, int type, int size) {
		if(ENABLE_LOG) System.out.println("FontUtils >> loadCustomFont >> name="+name+"  size="+size+ "  type="+type+" in path="+FONT_PATH);


        String tag = name+"_"+type+"_"+size;

        if(ttfMaps.containsKey(tag)) {
            return ttfMaps.get(tag);
        }


        long time = System.currentTimeMillis();

        Font fontTemp = null;
        try {
            fontTemp = Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(FONT_PATH+name));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }



        TrueTypeFont ttf = new TrueTypeFont(fontTemp.deriveFont(type, size), true);
        ttfMaps.put(tag,ttf);

        if(ENABLE_LOG)System.out.println("time truetype custom font = "+(System.currentTimeMillis()-time));

        return ttf ;
	}


    public static  org.newdawn.slick.UnicodeFont loadCustomUnicodeFont(String name, int type, int size)  {
        if(ENABLE_LOG) System.out.println("FontUtils >> loadCustomFont >> name="+name+"  size="+size+ "  type="+type+" in path="+FONT_PATH);
        long time = System.currentTimeMillis();


            Font fontTemp = null;
            try {
                fontTemp = Font.createFont(java.awt.Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(FONT_PATH+name));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }


        org.newdawn.slick.UnicodeFont uniFont = new org.newdawn.slick.UnicodeFont(fontTemp.deriveFont(type, size));
        uniFont.addAsciiGlyphs();
        //uniFont.addGlyphs(32,127);
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        if(ENABLE_LOG)System.out.println("time unicode font = "+(System.currentTimeMillis()-time));

        return uniFont;
    }






	/**
	 * Charger une police
	 * @param name :  nom de la police
	 * @param type : PLAIN, BOLD   -> Font.BOLD, Font.PLAIN
	 * @param size : taille de la police
	 * @return un objet TrueTypeFont
	 */
	public static TrueTypeFont loadFont(String name, int type, int size, boolean isSystemFont) {

		if(isSystemFont){
			return loadSystemFont(name, type, size);
		}else{
			return loadCustomFont(name, type, size);
		}
	}


    public static UnicodeFont loadUnicodeFont(String name, int type, int size, boolean isSystemFont) {

        if(isSystemFont){
            return loadSystemUnicodeFont(name, type, size);
        }else{
            return loadCustomUnicodeFont(name, type, size);
        }
    }

}
