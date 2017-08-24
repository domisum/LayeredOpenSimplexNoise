package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import lombok.Getter;
import lombok.ToString;
import opensimplexnoise.OpenSimplexNoise;

@APIUsage
@ToString
public class LayeredOpenSimplexNoise
{

	// SETTINGS
	@Getter private final NoiseLayers noiseLayers;

	// TEMP
	private final OpenSimplexNoise[] noiseFields;


	// INITIALIZATION
	@APIUsage public LayeredOpenSimplexNoise(NoiseLayer... noiseLayers)
	{
		this(new NoiseLayers(noiseLayers));
	}

	@APIUsage public LayeredOpenSimplexNoise(NoiseLayers noiseLayers)
	{
		this.noiseLayers = noiseLayers;
		this.noiseFields = new OpenSimplexNoise[noiseLayers.layers.size()];
		for(int i = 0; i < noiseLayers.layers.size(); i++)
			this.noiseFields[i] = new OpenSimplexNoise(noiseLayers.layers.get(i).seed);
	}


	// NOISE
	@APIUsage public double evaluate(double x, double y)
	{
		return evaluate(x, y, 0, 0, 2);
	}

	@APIUsage public double evaluate(double x, double y, double z)
	{
		return evaluate(x, y, z, 0, 3);
	}

	@APIUsage public double evaluate(double x, double y, double z, double w)
	{
		return evaluate(x, y, z, w, 4);
	}


	private double evaluate(double x, double y, double z, double w, int dimensions)
	{
		double sum = 0;

		for(int i = 0; i < this.noiseLayers.layers.size(); i++)
		{
			NoiseLayer layer = this.noiseLayers.layers.get(i);
			OpenSimplexNoise openSimplexNoise = this.noiseFields[i];

			double eval;
			if(dimensions == 2)
				eval = openSimplexNoise.eval(x/layer.scale, y/layer.scale);
			else if(dimensions == 3)
				eval = openSimplexNoise.eval(x/layer.scale, y/layer.scale, z/layer.scale);
			else
				eval = openSimplexNoise.eval(x/layer.scale, y/layer.scale, z/layer.scale, w/layer.scale);

			sum += eval*layer.amplitude;
		}

		return sum;
	}

}
