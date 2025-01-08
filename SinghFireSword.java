/**
 * Child Class
 * inheritance: Line 7
 * overload: Lines 13, 17
 * creation and usage of objects: Line 17
 **/
public class SinghFireSword extends SinghBaseSword {

    // the SinghFireSword subclass adds one new field
    private String fireAttack;

    // the SinghFireSword subclass has two constructor
    public SinghFireSword( ) {
    	super();
        fireAttack = "";
    }
    public SinghFireSword(String startcolour, String startmetal, String starthilt, String startfireAttack) {
    	super(startcolour,startmetal,starthilt);
    	this.fireAttack = startfireAttack;
    }           
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public String getFireAttack() {
		return fireAttack;
	}
	public void setFireAttack(String fireAttack) {
		this.fireAttack = fireAttack;
	}
}
