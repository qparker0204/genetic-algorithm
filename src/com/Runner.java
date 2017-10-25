package com;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
	
	private int popCount;
	private char[] target;
	private float mutationRate;
	
	public static DNA[] population;
	public static Random rand = new Random();
	
	public Runner() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter target phrase: ");
		this.target = scan.nextLine().toCharArray();
		System.out.print("Enter initial population count: ");
		this.popCount = scan.nextInt();
		System.out.print("Enter mutation rate (number out of 100): ");
		this.mutationRate = scan.nextInt() / 100;
		scan.close();
	}
	
	public char[] getTarget() {
		return this.target;
	}
	
	public int getPopCount() {
		return this.popCount;
	}
	
	public float getMutationRate() {
		return this.mutationRate;
	}
	
	public void setup(int length) {
		for (int i = 0; i < population.length; i++) {
			population[i] = new DNA(length);
		}
	}
	public static void main(String[] args) {
		Runner runner = new Runner();
		char[] target = runner.getTarget();
		int targetLength = target.length;
		int popCount = runner.getPopCount();
		float mutationRate = runner.getMutationRate();
		
		population = new DNA[popCount];
		runner.setup(targetLength);
		
		int generations = 0;
		boolean done = false;
		while (!done) {
			generations ++;
			
			for (int i = 0; i < population.length; i++) {
				population[i].computeFitness(target);
			}
			
			List<DNA> matingPool = new ArrayList<DNA>();
			for (int i = 0; i < population.length; i++) {
				int n = (int)(population[i].getFitness() * 100);
				for (int j = 0; j < n; j++) {
					matingPool.add(population[i]);
				}
			}
			
			for (int i = 0; i < population.length; i++) {
				int a = rand.nextInt(matingPool.size());
				int b = rand.nextInt(matingPool.size());
				
				DNA parentA = matingPool.get(a);
				DNA parentB = matingPool.get(b);
				DNA child = parentA.crossover(parentB);
				child.mutate(mutationRate);
				
				population[i] = child;
			}
			
			for (int i = 0; i < population.length; i++) {
				System.out.println(population[i].getPhrase());
				if (population[i].getPhrase().equals(String.valueOf(target))) {
					done = true;
					break;
				}
			}
		}
		System.out.println("Generations: " + generations);
		
	}
}
