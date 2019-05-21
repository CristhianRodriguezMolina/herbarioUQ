package co.gov.jsasociados;

public class DTOFamiliaMaxEspecies {

	private Familia familia;
	private int cantEspecies;
	
	public DTOFamiliaMaxEspecies(Familia f, Integer c)
	{
		familia = f;
		cantEspecies = c;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public int getCantEspecies() {
		return cantEspecies;
	}

	public void setCantEspecies(int cantEspecies) {
		this.cantEspecies = cantEspecies;
	}
	
	
}
