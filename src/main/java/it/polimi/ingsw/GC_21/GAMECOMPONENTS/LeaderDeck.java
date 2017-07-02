package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.DoCraftAction;
import it.polimi.ingsw.GC_21.EFFECT.DontCheckMP;
import it.polimi.ingsw.GC_21.EFFECT.DontSpend3Coins;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.MultiplyEarning;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.SetFamilyMemberValue;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class LeaderDeck extends Deck {

	
	//DetailedRequirements.pdf didn't say to load leader cards by file, so we created them here... hope it doesn't really matter!
	public LeaderDeck(Game game) {
		super(game);
		this.cards = new ArrayList<Card>();
		ArrayList<FamilyMemberColor> forSigismondoMaletesta = new ArrayList<FamilyMemberColor>();
		forSigismondoMaletesta.add(FamilyMemberColor.Neutral);
		ArrayList<FamilyMemberColor> forLudovicoIlMoro = new ArrayList<FamilyMemberColor>();
		forLudovicoIlMoro.add(FamilyMemberColor.Black); 
		forLudovicoIlMoro.add(FamilyMemberColor.White); 
		forLudovicoIlMoro.add(FamilyMemberColor.Orange); 
		OncePerTurnLeaderCard francescoSforza = new OncePerTurnLeaderCard("01", "Francesco Sforza", 5, 0, 0, 0, new Possession(), false, new DoCraftAction(game, new Possession(), CraftType.Harvest, 1, 0, 0));
		PermanentLeaderCard filippoBrunelleschi = new PermanentLeaderCard("03", "Filippo Brunelleschi", 0, 0, 5, 0,new Possession(), false,  new DontSpend3Coins());
		PermanentLeaderCard sigismondoMaletesta = new PermanentLeaderCard("14", "Sigismondo Maletesta", 0, 0, 0, 0, new Possession(0, 0, 0, 0, 3, 7, 0),false, new PlacementInfluencer(3, null, new Possession(), forSigismondoMaletesta));
		OncePerTurnLeaderCard girolamoSavonarola = new OncePerTurnLeaderCard("05", "Girolamo Savonarola", 0, 0, 0, 0, new Possession(18,0,0,0,0,0,0), false, new Effect(new Possession(0, 0, 0, 0, 1, 0, 0), 0, game));
		OncePerTurnLeaderCard michelangeloBuonarroti = new OncePerTurnLeaderCard("08", "Michelangelo Buonarroti", 0, 0, 0, 0, new Possession(0, 0, 10, 0, 0, 0, 0), false, new Effect(new Possession(3, 0, 0, 0, 0, 0, 0), 0, game));
		OncePerTurnLeaderCard giovanniDalleBandeNere = new OncePerTurnLeaderCard("06", "Giovanni Dalle Bande Nere", 0, 0, 0, 0, new Possession(0, 0, 0, 0, 0, 12, 0), false, new Effect(new Possession(1, 1, 1, 0, 0, 0, 0), 0, game));
		OncePerTurnLeaderCard leonardoDaVinci = new OncePerTurnLeaderCard("10", "Leonardo Da Vinci", 0, 4, 0, 2, new Possession(), false, new DoCraftAction(game, new Possession(), CraftType.Production, 0, 0, 0));
		OncePerTurnLeaderCard sandroBotticelli = new OncePerTurnLeaderCard("07", "Sandro Botticelli", 0, 0, 0, 0, new Possession(0, 10, 0, 0, 0, 0, 0), false, new Effect(new Possession(0, 0, 0, 0, 0, 2, 1), 0, game));
		PermanentLeaderCard ludovicoIlMoro = new PermanentLeaderCard("16", "Ludovico Il Moro", 2, 2, 2, 2, new Possession(), false, new PlacementInfluencer(3, null, null, forLudovicoIlMoro));
		PermanentLeaderCard lucreziaBorgia = new PermanentLeaderCard("13", "Lucrezia Borgia", 0, 0, 0, 6, new Possession(), false, new PlacementInfluencer(2, null, null, forLudovicoIlMoro));
		OncePerTurnLeaderCard federicoDaMontefeltro = new OncePerTurnLeaderCard("04","Federico Da Montefeltro", 0, 0, 0, 5, new Possession(), false, new SetFamilyMemberValue(new Possession(), 0, game, 6));
		PermanentLeaderCard cesareBorgia = new PermanentLeaderCard("17", "Cesare Borgia", 0, 0, 3, 0, new Possession(12, 0, 0, 0, 2, 0, 0), false, new DontCheckMP());
		PermanentLeaderCard santaRita = new PermanentLeaderCard("18", "Santa Rita", 0, 0, 0, 0, new Possession(0, 0, 0, 0, 8, 0, 0), false, new MultiplyEarning(2));
		OncePerTurnLeaderCard cosimoDeMedici = new OncePerTurnLeaderCard("19", "Cosimo De Medici", 0, 2, 4, 0, new Possession(), false, new Effect(new Possession(0, 0, 0, 3, 0, 0, 1), 0, game));
		OncePerTurnLeaderCard bartolomeoColleoni = new OncePerTurnLeaderCard("20", "Bartolomeo Colleoni", 2, 0, 0, 4, new Possession(), false, new Effect(new Possession(0, 0, 0, 0, 0, 0, 4), 0, game));
		OncePerTurnLeaderCard ludovicoIIIGonzaga = new OncePerTurnLeaderCard("09", "Ludovico III Gonzaga", 0, 0, 0, 0, new Possession(0, 0, 0, 15, 0, 0, 0), false, new Effect(new Possession(), 1, game));
		PermanentLeaderCard picoDellaMirandola = new PermanentLeaderCard("11", "Pico della Mirandola", 4, 0, 2, 0, new Possession(), false, new PlacementInfluencer(0, null, new Possession(3, 0, 0, 0, 0, 0, 0)));
		cards.add(francescoSforza);		
		/*cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);
		cards.add(francescoSforza);*/

		cards.add(filippoBrunelleschi);
		cards.add(sigismondoMaletesta);
		cards.add(picoDellaMirandola);
		cards.add(bartolomeoColleoni);
		cards.add(cosimoDeMedici);
		cards.add(cesareBorgia);
		/*cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);
		cards.add(federicoDaMontefeltro);*/

		cards.add(lucreziaBorgia);
		cards.add(sandroBotticelli);
		cards.add(leonardoDaVinci);
		cards.add(giovanniDalleBandeNere);
		cards.add(michelangeloBuonarroti);
		cards.add(girolamoSavonarola);
		cards.add(santaRita);
		cards.add(ludovicoIlMoro);
		cards.add(ludovicoIIIGonzaga);
		this.shuffle();
	}
	
}