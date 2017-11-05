package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuominenNegatiivisellaTilavuudella(){
    	varasto = new Varasto(-5);
    	
    	assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiPositiivisellaTilavuudellaJaAlkusaldolla() {
    	varasto = new Varasto(5, 2);
    	
    	assertEquals(5.0, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(2.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNegatiivisellaTilavuudellaJaAlkusaldolla() {
    	varasto = new Varasto(-5, -2);
    	
    	assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiNegatiivisellaTilavuudellaJaPositiivisellaAlkusaldolla() {
    	varasto = new Varasto(-5, 2);

    	assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    	//Virhe koodissa. Pit�isi testata if(alkusaldo <= this.tilavuus)
    	assertEquals(-5.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiPositiivisellaTilavuudellaJaNegatiivisellaAlkusaldolla() {
    	varasto = new Varasto(5, -2);
    	
    	assertEquals(5.0, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiPienemmallaTilavuudellaKuinAlkusaldo() {
    	varasto = new Varasto(5, 6);
    	
    	assertEquals(5.0, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoEiKasvaJosLisataanNegatiivinen() {
    	varasto.lisaaVarastoon(-1);
    	
    	assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoksiTilavuusJosLisataanLiikaa() {
    	varasto.lisaaVarastoon(11);
    	
    	assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoPysyySamanaKunOtetaanNegatiivinen() {    	
    	assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    	
    	assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoksiNollaKunOtetaanEnemmanKuinSaldo() {
    	varasto.lisaaVarastoon(5);
    	
    	assertEquals(5.0, varasto.otaVarastosta(6), vertailuTarkkuus);
    	assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringOikein() {
    	varasto.lisaaVarastoon(3);
    	assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
}