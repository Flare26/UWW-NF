package Lab19;


public final class Team {

	private String name;
	private String city;
	private Province province;
	private Country country;
	private final int yearFounded;

	public Team(String name, String city, Province province, Country country, int yearFounded) {
		this.name = name;
		this.city = city;
		this.province = province;
		this.country = country;
		this.yearFounded = yearFounded;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	@Override
	public String toString() {
		return String.format("%s (est. %d)", name, yearFounded);
	}
}
