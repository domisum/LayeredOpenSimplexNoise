package de.domisum.layeredopensimplexnoise;

import lombok.Getter;

public final class NoiseLayer
{

	@Getter private double scale;
	@Getter private double frequency;
	@Getter private long seed;


	// -------
	// INITIALIZATION
	// -------
	public NoiseLayer(double scale, double frequency, long seed)
	{
		if(scale <= 0)
			throw new IllegalArgumentException("The scale has to be positive");

		if(frequency <= 0)
			throw new IllegalArgumentException("The frequency has to be positive");

		this.scale = scale;
		this.frequency = frequency;
		this.seed = seed;
	}


	// -------
	// OBJECT
	// -------
	@Override
	public String toString()
	{
		return "NoiseLayer{"+"scale="+this.scale+", frequency="+this.frequency+", seed="+this.seed+'}';
	}

}
