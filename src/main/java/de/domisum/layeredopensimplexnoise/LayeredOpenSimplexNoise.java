package de.domisum.layeredopensimplexnoise;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import lombok.Getter;
import opensimplexnoise.OpenSimplexNoise;

import java.util.Arrays;

@APIUsage
public class LayeredOpenSimplexNoise
{

	// SETTINGS
	@Getter private final NoiseLayer[] layers;

	// TEMP
	private final OpenSimplexNoise[] noiseFields;


	// INITIALIZATION
	@APIUsage public LayeredOpenSimplexNoise(NoiseLayer... layers)
	{
		if(layers == null || layers.length == 0)
			throw new IllegalArgumentException("At least 1 layer is needed");

		this.layers = layers;
		this.noiseFields = new OpenSimplexNoise[layers.length];
		for(int i = 0; i < layers.length; i++)
			this.noiseFields[i] = new OpenSimplexNoise(layers[i].getSeed());
	}


	// OBJECT
	@Override public String toString()
	{
		return this.getClass().getSimpleName()+"{"+"layers="+Arrays.toString(this.layers)+'}';
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

		for(int i = 0; i < this.layers.length; i++)
		{
			NoiseLayer layer = this.layers[i];
			OpenSimplexNoise openSimplexNoise = this.noiseFields[i];

			double eval;
			if(dimensions == 2)
				eval = openSimplexNoise.eval(x/layer.getScale(), y/layer.getScale());
			else if(dimensions == 3)
				eval = openSimplexNoise.eval(x/layer.getScale(), y/layer.getScale(), z/layer.getScale());
			else
				eval = openSimplexNoise.eval(x/layer.getScale(), y/layer.getScale(), z/layer.getScale(), w/layer.getScale());

			sum += eval*layer.getAmplitude();
		}

		return sum;
	}

}
