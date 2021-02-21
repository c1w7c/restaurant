package restaurant;

public class Carte {
	private String name;
	private int price;
	private int calorie;
	
	public Carte() {
		
	}
	
	public Carte(String name, int price, int calorie) {
		super();
		this.name = name;
		this.price = price;
		this.calorie = calorie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	
}
