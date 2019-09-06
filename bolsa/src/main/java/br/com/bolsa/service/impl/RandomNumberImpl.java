package br.com.bolsa.service.impl;

import java.util.Random;

import br.com.bolsa.service.RandomService;

public class RandomNumberImpl implements RandomService{

	@Override
	public Double randomValorAcao() {
		Random random = new Random();
		return 10 + (1 * random.nextDouble());
	}

}
