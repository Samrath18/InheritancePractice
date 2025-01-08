/**
 * Parent Class
 * overload: Lines 17, 19
 * creation and usage of objects: Line 19
 **/

public class SinghBaseSword {

    // the SinghBaseSword class has three fields
	private String name;
	private String element;
	private String colour;
	private String metal;
	private String hilt;
        	
    // the SinghBaseSword class has two constructor
    public SinghBaseSword( ) {
    }
    public SinghBaseSword(String startcolour, String startmetal, String starthilt) {
        this.colour  = startcolour;
        this.metal = startmetal;
        this.hilt = starthilt;
    }
	public static void main(String[] args) {		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;
	}
	public String getHilt() {
		return hilt;
	}
	public void setHilt(String hilt) {
		this.hilt = hilt;
	}

	
    
 
}
