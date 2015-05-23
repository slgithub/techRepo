package com.design.strategy.flyrun;

public class CircusManger {

	public static void main(String[] arg){
		Animal[] animalOnStage= new Animal[3];
		animalOnStage[0]= new Dog("Luckky---the Dog----");
		animalOnStage[1]= new Bird("Twidder--Sweet Singer");
		animalOnStage[2]= new Penguine("Penguine of Maddaguskar");
		
		for(Animal ani : animalOnStage){
			ani.motionType();
		}
	}
}
