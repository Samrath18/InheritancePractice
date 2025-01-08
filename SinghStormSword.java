/**
 * Child of a Child Class
 * inheritance: Line 8
 * overload: Lines 14, 17
 * override: Line 30
 * creation and usage of objects: Lines 17
 **/
public class SinghStormSword extends SinghWaterSword {

    // the SinghStormSword subclass adds one new field
    private String stormAttack;

    // the SinghStormSword subclass has two constructor
    public SinghStormSword( ) {
    	super();
    }
    public SinghStormSword(String startcolour, String startmetal, String starthilt, String startWaterAttack, String startStormAttack) {
    	super(startcolour, startmetal, starthilt, startWaterAttack);
    	this.stormAttack = startStormAttack;
    }           
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public String getStormAttack() {
		return stormAttack;
	}
	public void setStormAttack(String stormAttack) {
		this.stormAttack = stormAttack;
	}
    public void OverrideExample() {
    	//This method has overridden a method in SinghWaterSword
    	
    	int i = 4;
    }

}
