package baseClass;

public enum APIRescourses {

	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");

	private String rescource;
	
	APIRescourses(String Rescource) {
		this.rescource = Rescource;
	}
	
	public String getRescource() {
        
		return rescource;
	}

	
}
