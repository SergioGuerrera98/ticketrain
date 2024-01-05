package com.corso.ticketrain.treno.utils;

import com.corso.ticketrain.checkstring.CheckString;
import com.corso.ticketrain.checkstring.ComparatoreString;
import com.corso.ticketrain.checkstring.HuntSzymanski;
import com.corso.ticketrain.checkstring.JaroWinkler;
import com.corso.ticketrain.checkstring.Levenshtein;
import com.corso.ticketrain.checkstring.WagnerFischer;

public class UtilsCheckString {
	
	public static ComparatoreString Check() {
		CheckString jaroWinkler = new JaroWinkler();
		CheckString levenshtein = new Levenshtein();
	    CheckString wagnerFischer = new WagnerFischer();
	    CheckString hunt = new HuntSzymanski();
	    jaroWinkler.setCheckString(levenshtein).setCheckString(wagnerFischer);
	    ComparatoreString comparatore = new ComparatoreString(jaroWinkler);
	    return comparatore;
	}

}
