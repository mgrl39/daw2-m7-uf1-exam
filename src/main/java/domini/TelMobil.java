package domini;


import java.util.ArrayList;
import java.util.List;

public class TelMobil {
	private int id;
	private String marca;
	private String model;
	private int any;
	private boolean reacondicionat;
	private float preu;

	// Constructor dell TelMobil
	public TelMobil(int id, String marca, String model, int any, boolean reacondicionat, float preu) {
		this.id = id;
		this.marca = marca;
		this.model = model;
		this.any = any;
		this.reacondicionat = reacondicionat;
		this.preu = preu;
	}

	// Tots els getters demanats
	public int getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public String getModel() {
		return model;
	}

	public int getAny() {
		return any;
	}

	public boolean isReacondicionat() {
		return reacondicionat;
	}

	public float getPreu() {
		return preu;
	}

	// Tots els setters demanats
	public void setId(int id) {
		this.id = id;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setAny(int any) {
		this.any = any;
	}

	public void setReacondicionat(boolean reacondicionat) {
		this.reacondicionat = reacondicionat;
	}

	public void setPreu(float preu) {
		this.preu = preu;
	}

	// Metode toString
	@Override
	public String toString() {
		return "TelMobil{" +
			"id=" + id +
			", marca='" + marca + '\'' +
			", model='" + model + '\'' +
			", any=" + any +
			", reacondicionat=" + reacondicionat +
			", preu=" + preu +
			'}';
	}

	private static List<TelMobil> mobils = new ArrayList<>();

	public static void afegirMobil(TelMobil mobil) {
		mobils.add(mobil);
	}

	public static List<TelMobil> getList() {
		return mobils;
	}

}
