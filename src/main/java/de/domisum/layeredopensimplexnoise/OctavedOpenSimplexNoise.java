package de.domisum.layeredopensimplexnoise;

import java.util.Random;

public class OctavedOpenSimplexNoise extends LayeredOpenSimplexNoise
{

	// -------
	// INITIALIZATION
	// -------
	public OctavedOpenSimplexNoise(int octaves, double baseScale, double scaleMultiplier, double baseFrequency,
			double frequencyMultiplier, long seed)
	{
		super(generateNoiseLayers(octaves, baseScale, scaleMultiplier, baseFrequency, frequencyMultiplier,
				generateSeeds(seed, octaves)));
	}

	public OctavedOpenSimplexNoise(int octaves, double baseScale, double scaleMultiplier, double baseFrequency,
			double frequencyMultiplier, long... seeds)
	{
		super(generateNoiseLayers(octaves, baseScale, scaleMultiplier, baseFrequency, frequencyMultiplier, seeds));
	}


	private static NoiseLayer[] generateNoiseLayers(int octaves, double baseScale, double scaleMultiplier, double baseFrequency,
			double frequencyMultiplier, long... seeds)
	{
		if(octaves <= 0)
			throw new IllegalArgumentException("The number of octaves has to be positive");

		if(seeds.length != octaves)
			throw new IllegalArgumentException("The number of octaves does not match the number of seeds");

		if(baseScale <= 0 || scaleMultiplier <= 0)
			throw new IllegalArgumentException("baseScale and scaleMultiplier have to be positive");

		if(baseFrequency <= 0 || frequencyMultiplier <= 0)
			throw new IllegalArgumentException("baseFrequency and baseFrequency have to be positive");


		double scale = baseScale;
		double frequency = baseFrequency;

		NoiseLayer[] layers = new NoiseLayer[octaves];
		for(int i = 0; i < octaves; i++)
		{
			layers[i] = new NoiseLayer(scale, frequency, seeds[i]);

			scale *= scaleMultiplier;
			frequency *= frequencyMultiplier;
		}

		return layers;
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
