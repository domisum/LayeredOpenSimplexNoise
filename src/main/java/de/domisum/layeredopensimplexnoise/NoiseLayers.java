package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.API;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NoiseLayers
{

	public final List<NoiseLayer> layers;


	// INIT
	@API public NoiseLayers(NoiseLayer... layers)
	{
		this(Arrays.asList(layers));
	}

	@API public NoiseLayers(List<NoiseLayer> layers)
	{
		if(layers == null || layers.isEmpty())
			throw new IllegalArgumentException("At least 1 layer is needed");

		this.layers = Collections.unmodifiableList(layers);
	}


	// GETTERS
	@API public NoiseLayers getRandomSeedsCopy(Random random)
	{
		List<NoiseLayer> newLayers = new ArrayList<>();
		for(NoiseLayer nl : this.layers)
			newLayers.add(new NoiseLayer(nl.scale, nl.amplitude, random.nextLong()));

		return new NoiseLayers(newLayers);
	}

}
