/**
 * Child Class
 * inheritance: Line 8
 * overload: Lines 14, 17, 20
 * override: Line 30
 * creation and usage of objects: Lines 17, 20
 **/
public class SinghWaterSword extends SinghBaseSword {

    // the SinghWaterSword subclass adds one new field
    private String waterAttack;

    // the SinghFireSword subclass has two constructor
    public SinghWaterSword( ) {
    	super();
    }
    public SinghWaterSword(String startcolour, String startmetal, String starthilt) {
    	super(startcolour, startmetal, starthilt);
    }
    public SinghWaterSword(String startcolour, String startmetal, String starthilt, String startwaterAttack) {
    	super(startcolour, startmetal, starthilt);
    	this.waterAttack = startwaterAttack;
    }
	public String getWaterAttack() {
		return waterAttack;
	}
	public void setWaterAttack(String waterAttack) {
		this.waterAttack = waterAttack;
	}   
    public void OverrideExample() {
    	//This method will be overridden in SinghStormSword
    	
    	int i = 0;
    }
}
