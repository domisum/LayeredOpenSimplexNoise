package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import lombok.Getter;

public final class NoiseLayer
{

	@Getter private double scale;
	@Getter private double amplitude;
	@Getter private long seed;


	// -------
	// INITIALIZATION
	// -------
	@APIUsage
	public NoiseLayer(double scale, double amplitude, long seed)
	{
		if(scale <= 0)
			throw new IllegalArgumentException("The scale has to be positive");

		if(amplitude <= 0)
			throw new IllegalArgumentException("The amplitude has to be positive");

		this.scale = scale;
		this.amplitude = amplitude;
		this.seed = seed;
	}


	// -------
	// OBJECT
	// -------
	@Override
	public String toString()
	{
		return "NoiseLayer{"+"scale="+this.scale+", amplitude="+this.amplitude+", seed="+this.seed+'}';
	}

}
