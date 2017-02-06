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
		super(generateLayers(octaves, baseScale, scaleMultiplier, baseFrequency, frequencyMultiplier,
				generateSeeds(seed, octaves)));
	}

	public OctavedOpenSimplexNoise(int octaves, double baseScale, double scaleMultiplier, double baseFrequency,
			double frequencyMultiplier, long... seeds)
	{
		super(generateLayers(octaves, baseScale, scaleMultiplier, baseFrequency, frequencyMultiplier, seeds));
	}


	private static NoiseLayer[] generateLayers(int octaves, double baseScale, double scaleMultiplier, double baseFrequency,
			double frequencyMultiplier, long... seeds)
	{
		if(seeds.length != octaves)
			throw new IllegalArgumentException("The number of octaves does not match the number of seeds");

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
