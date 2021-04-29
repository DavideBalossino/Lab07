package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Guasti {
	Integer id;
	Nerc nerc;
	Integer personeAffette;
	LocalDateTime inizio;
	LocalDateTime fine;
	
	public Guasti(Integer id, Nerc nerc, Integer personeAffette, LocalDateTime inizio, LocalDateTime fine) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.personeAffette = personeAffette;
		this.inizio = inizio;
		this.fine = fine;
	}

	public Integer getId() {
		return id;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public Integer getPersoneAffette() {
		return personeAffette;
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guasti other = (Guasti) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Guasti [id=" + id + ", nerc=" + nerc + ", personeAffette=" + personeAffette + ", inizio=" + inizio
				+ ", fine=" + fine + "]";
	}
	
	public long oreDisservizio() {
		Duration tempo=Duration.between(inizio, fine);
		return tempo.getSeconds();
		//return tempo.toHours();
		//return tempo.toHoursPart();
	}
	
}
