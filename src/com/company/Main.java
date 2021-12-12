package com.company;

import java.util.Random;

public class Main {

    public static int BossHealth = 700;
    public static int BossDamage = 50;
    public static String BossDefence = " ";

    public static int[] HeroesHealth = {270, 260, 250, 240,500, 200, 250,200};
    public static int[] HeroesDamage = {15, 20, 25, 0,5, 20, 25,20};
    public static String[] HeroesAttackType = {"Physical", "Magical", "Kinetic", "Healer","Golem","Lucky","Berserk","Thor"};
    public static int roundNumber = 1;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinish()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(HeroesAttackType.length);
        BossDefence = HeroesAttackType[randomIndex];
        System.out.println("Boss chose " + BossDefence);
    }

    public static void round() {
        roundNumber++;
        changeBossDefence();
        BossHits();
        HeroesHit();
        medic();
        golem();
        lucky();
        berserk();
        thor();
        printStatistics();
    }
        public static void HeroesHit () {
            for (int i = 0; i < HeroesDamage.length; i++) {
                if (HeroesHealth[i] > 0 && BossHealth > 0) {
                    if (HeroesAttackType[i] == BossDefence) {
                        Random random = new Random();
                        int coefficient = random.nextInt(9) + 2; // 2,3,4,5,,7,8,9,10
                        if (BossHealth - HeroesDamage[i] * coefficient < 0) {
                            BossHealth = 0;
                        } else {
                            BossHealth = BossHealth - HeroesDamage[i] * coefficient;
                        }
                        System.out.println("Critical Damage " + HeroesDamage[i] * coefficient);
                    } else {
                        if (BossHealth - HeroesDamage[i] < 0) {
                            BossHealth = 0;
                        } else {
                            BossHealth = BossHealth - HeroesDamage[i];
                        }
                    }
                }
            }


        }

        public static void BossHits () {
            for (int i = 0; i < HeroesHealth.length; i++) {
                if (HeroesHealth[i] > 0 && BossHealth > 0) {
                    if (HeroesHealth[i] - BossDamage < 0) {
                        HeroesHealth[i] = 0;
                    } else {
                        HeroesHealth[i] = HeroesHealth[i] - BossDamage;
                    }
                }
            }
        }

        public static Boolean isGameFinish () {
            if (BossHealth <= 0) {
                System.out.println("Heroes Won !");
                return true;
            }
        /*if (HeroesHealth[0] <= 0 && HeroesHealth[1] <= 0 && HeroesHealth[2] <= 0) {
            System.out.println("Boss Won! ");
            return true;
        }
        return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i < HeroesHealth.length; i++) {
                if (HeroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss Won! ");
            }
            return allHeroesDead;
        }
        public static void medic(){
            for (int i = 0; i < HeroesHealth.length; i++) {
                if (i == 3 ){continue;}
                if (HeroesHealth[i] >0 && HeroesHealth[i] <100 && HeroesHealth[3] >0){
                    HeroesHealth[i] += 80;
                    System.out.println("медик захилил " + HeroesAttackType[i]);
                    break;
                }

            }

        }
        public static void golem (){
        int livingGeroi = 0;
        int bossDamage = BossDamage /5;
            for (int i = 0; i < HeroesHealth.length; i++) {
                if (i == 4){
                    continue;}
                else if (HeroesHealth[4] >0 && HeroesHealth[i] >0){
                    livingGeroi ++;
                    HeroesHealth[i] += bossDamage;


                }
            }
            HeroesHealth[4] -= bossDamage * livingGeroi;
            System.out.println("глолем кампетровал урон " + bossDamage * livingGeroi);
        }
        public static void lucky (){
        Random random = new Random();
        boolean povezlo = random.nextBoolean();
        if (HeroesHealth[5]>0 && povezlo){
            HeroesHealth[5] += BossDamage;
            System.out.println("уклонился от удара " + povezlo);
        }
        }
        public static void berserk (){
            for (int i = 0; i < HeroesHealth.length; i++) {
                if (HeroesHealth[6]>0 ){
                    HeroesHealth[6]+= BossDamage -25;
                    BossHealth -= BossDamage -25;
                    System.out.println("berserk поглатил половину урона ");
                    break;
                }

            }
        }
        public static void thor (){
        Random random = new Random();
        boolean stan = random.nextBoolean();
        if (HeroesHealth[7]>0){
            if (stan) {
                BossDamage = 0;
                System.out.println("застанил на 1 раунд " + stan);
            }else {
                BossDamage=50;
            }
        }
        }

        public static void printStatistics () {
            System.out.println(roundNumber + " ROUND---------------");
            System.out.println("Boss Health: " + BossHealth + " (" + BossDamage + ")");
            for (int i = 0; i < HeroesHealth.length; i++) {
                System.out.println(HeroesAttackType[i] + " health: "
                        + HeroesHealth[i] + " (" + HeroesDamage[i] + ")");
            }
            System.out.println("---------------------");
        }

    }




