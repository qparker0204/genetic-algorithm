package com;

import java.util.Random;

public class DNA {
	private char[] genes;
	private Random rand = new Random();
	private float fitness;
	
	public DNA(int length) {
		genes = new char[length];
		
		for (int i = 0; i < genes.length; i++) {
			genes[i] = (char)(rand.nextInt(128-32) + 32);
		}
	}
	
	public float getFitness() {
		return this.fitness;
	}
	
	public int length() {
		return this.genes.length;
	}
	
	public char getGene(int index) {
		return this.genes[index];
	}
	
	public void setGene(int index, char gene) {
		this.genes[index] = gene;
	}
	
	public void computeFitness(char[] target) {
		int score = 0;
		for (int i = 0; i < target.length; i++) {
			if (genes[i] == target[i]) {
				score ++;
			}
		}
		this.fitness = (float)score/target.length;
	}
	
	public DNA crossover(DNA parent) {
		DNA child = new DNA(parent.length());
		
		int midpoint = rand.nextInt(parent.length());
		
		for (int i = 0; i < parent.length(); i++) {
			if (i > midpoint) {
				child.setGene(i, this.genes[i]);
			} else {
				child.setGene(i, parent.getGene(i));
			}
		}
		
		return child;
	}
	
	public void mutate(float mutationRate) {
		for (int i = 0; i < this.genes.length; i++) {
			if (rand.nextFloat() < mutationRate) {
				this.genes[i] = (char)(rand.nextInt(128-32) + 32);
//				System.out.println("HERE");
			}
		}
	}
	
	public String getPhrase() {
		return new String(this.genes);
	}
}
