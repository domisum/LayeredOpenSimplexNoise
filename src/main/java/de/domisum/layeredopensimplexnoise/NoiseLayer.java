package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.API;
import lombok.ToString;

/**
 * scale: high value -> noise field changes slowly while moving; low value -> noise field changes fast while moving
 * amplitude: the amplitude of the output value (how high and how low it can get)
 * seed: value used to initialize the pseudorandom number generator
 */
@ToString
public final class NoiseLayer
{

	public final double scale;
	public final double amplitude;
	public final long seed;


	// INITIALIZATION
	@API public NoiseLayer(double scale, double amplitude, long seed)
	{
		if(scale <= 0)
			throw new IllegalArgumentException("The scale has to be positive");

		if(amplitude <= 0)
			throw new IllegalArgumentException("The amplitude has to be positive");

		this.scale = scale;
		this.amplitude = amplitude;
		this.seed = seed;
	}

}
