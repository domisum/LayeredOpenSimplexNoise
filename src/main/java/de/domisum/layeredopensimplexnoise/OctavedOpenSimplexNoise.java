package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.API;

import java.util.Random;

@API
public class OctavedOpenSimplexNoise extends LayeredOpenSimplexNoise
{

	// INITIALIZATION
	@API public OctavedOpenSimplexNoise(int octaves, double baseScale, double scaleMultiplier, double baseAmplitude,
			double amplitudeMultiplier, long seed)
	{
		super(generateNoiseLayers(octaves, baseScale, scaleMultiplier, baseAmplitude, amplitudeMultiplier,
				generateSeeds(seed, octaves)));
	}

	@API public OctavedOpenSimplexNoise(int octaves, double baseScale, double scaleMultiplier, double baseAmplitude,
			double amplitudeMultiplier, long... seeds)
	{
		super(generateNoiseLayers(octaves, baseScale, scaleMultiplier, baseAmplitude, amplitudeMultiplier, seeds));
	}


	private static NoiseLayers generateNoiseLayers(int octaves, double baseScale, double scaleMultiplier, double baseAmplitude,
			double amplitudeMultiplier, long... seeds)
	{
		if(octaves <= 0)
			throw new IllegalArgumentException("The number of octaves has to be positive");

		if(seeds.length != octaves)
			throw new IllegalArgumentException("The number of octaves does not match the number of seeds");

		if(baseScale <= 0 || scaleMultiplier <= 0)
			throw new IllegalArgumentException("baseScale and scaleMultiplier have to be positive");

		if(baseAmplitude <= 0 || amplitudeMultiplier <= 0)
			throw new IllegalArgumentException("baseAmplitude and amplitudeMultiplier have to be positive");


		double scale = baseScale;
		double amplitude = baseAmplitude;

		NoiseLayer[] layers = new NoiseLayer[octaves];
		for(int i = 0; i < octaves; i++)
		{
			layers[i] = new NoiseLayer(scale, amplitude, seeds[i]);

			scale *= scaleMultiplier;
			amplitude *= amplitudeMultiplier;
		}

		return new NoiseLayers(layers);
	}

	private static long[] generateSeeds(long baseSeed, int number)
	{
		Random random = new Random(baseSeed);

		long[] seeds = new long[number];
		for(int i = 0; i < number; i++)
			seeds[i] = random.nextLong();

		return seeds;
	}

}
