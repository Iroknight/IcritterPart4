package controller;

import model.ICritterDog;
import model.ICritterPenguin;
import model.ICritterSlug;
import model.ICritterWorld;
import model.Owner;

public class testController  {

	public static void main(String args[]) {

		ICritterWorld a = new ICritterWorld();
		Owner x = new Owner("bob", null);
		ICritterSlug y = new ICritterSlug("slg", x);
		ICritterSlug m = new ICritterSlug("sly", x);
		ICritterDog g = new ICritterDog("was", x);
		ICritterPenguin l = new ICritterPenguin("wamp", x);

		g.addInterest("pizza");
		
		a.addICritter(y);
		a.addICritter(m);
		a.addICritter(g);
		a.addICritter(l);
		a.runJamboree();
		
		x.buyFancyTreat(null);
		x.buyFancyTreat(null);
		x.buyFancyTreat(null);
		x.buyFancyTreat(null);
		x.buyFancyTreat(null);
		x.buyFancyTreat(null);
		
		System.out.println("Here");
		
		x.buyFancyTreat(null);
	}
}
