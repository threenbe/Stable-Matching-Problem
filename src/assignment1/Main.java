package assignment1;

import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		Preferences preferences = createPrefs();
		ArrayList<Integer> answer = Assignment1.stableMatchBruteForce(preferences);
		System.out.println(answer.toString());
		/*ArrayList<Integer> answer = Assignment1.stableMatchGaleShapley(preferences);
		System.out.println(answer.toString());*/
		/*ArrayList<Cost> answer2 = Assignment1.stableMatchCosts(preferences);
        for (Cost c : answer2) {
            System.out.println("[" + c.getIndexOfProfessor() + "," + c.getIndexOfStudent() + "," 
                        + c.getCostToProfessor() + "," + c.getCostToStuent() + "]");
        }*/
        /*ArrayList<Cost> answer2 = Assignment1.stableMatchCostsStudent(preferences);
        for (Cost c : answer2) {
            System.out.println("[" + c.getIndexOfProfessor() + "," + c.getIndexOfStudent() + "," 
                        + c.getCostToProfessor() + "," + c.getCostToStuent() + "]");
        }*/
	}


	public static Preferences createPrefs() {
		ArrayList<ArrayList<Integer>> profs = new ArrayList<ArrayList<Integer>>(); 
		ArrayList<Integer> prof1 = new ArrayList<Integer>(); 
		prof1.add(3);
		prof1.add(4);
		prof1.add(2);
		prof1.add(1);
		prof1.add(6);
		prof1.add(7);
		prof1.add(5);
		profs.add(prof1);
		
		ArrayList<Integer> prof2 = new ArrayList<Integer>(); 
		prof2.add(6);
		prof2.add(4);
		prof2.add(2);
		prof2.add(3);
		prof2.add(5);
		prof2.add(1);
		prof2.add(7);
		profs.add(prof2);
		
		ArrayList<Integer> prof3 = new ArrayList<Integer>(); 
		prof3.add(6);
		prof3.add(3);
		prof3.add(5);
		prof3.add(7);
		prof3.add(2);
		prof3.add(4);
		prof3.add(1);
		profs.add(prof3);
		
		ArrayList<Integer> prof4 = new ArrayList<Integer>(); 
		prof4.add(1);
		prof4.add(6);
		prof4.add(3);
		prof4.add(2);
		prof4.add(4);
		prof4.add(7);
		prof4.add(5);
		profs.add(prof4);
		
		ArrayList<Integer> prof5 = new ArrayList<Integer>(); 
		prof5.add(1);
		prof5.add(6);
		prof5.add(5);
		prof5.add(3);
		prof5.add(4);
		prof5.add(7);
		prof5.add(2);
		profs.add(prof5);
		
		ArrayList<Integer> prof6 = new ArrayList<Integer>(); 
		prof6.add(1);
		prof6.add(7);
		prof6.add(3);
		prof6.add(4);
		prof6.add(5);
		prof6.add(6);
		prof6.add(2);
		profs.add(prof6);
		
		ArrayList<Integer> prof7 = new ArrayList<Integer>(); 
		prof7.add(5);
		prof7.add(6);
		prof7.add(2);
		prof7.add(4);
		prof7.add(3);
		prof7.add(7);
		prof7.add(1);
		profs.add(prof7);
		
		ArrayList<ArrayList<Integer>> studs = new ArrayList<ArrayList<Integer>>(); 
		ArrayList<Integer> stud1 = new ArrayList<Integer>(); 
		stud1.add(4);
		stud1.add(5);
		stud1.add(3);
		stud1.add(7);
		stud1.add(2);
		stud1.add(6);
		stud1.add(1);
		studs.add(stud1);
		
		ArrayList<Integer> stud2 = new ArrayList<Integer>(); 
		stud2.add(5);
		stud2.add(6);
		stud2.add(4);
		stud2.add(7);
		stud2.add(3);
		stud2.add(2);
		stud2.add(1);
		studs.add(stud2);
		
		ArrayList<Integer> stud3 = new ArrayList<Integer>(); 
		stud3.add(1);
		stud3.add(6);
		stud3.add(5);
		stud3.add(4);
		stud3.add(3);
		stud3.add(7);
		stud3.add(2);
		studs.add(stud3);
		
		ArrayList<Integer> stud4 = new ArrayList<Integer>(); 
		stud4.add(3);
		stud4.add(5);
		stud4.add(6);
		stud4.add(7);
		stud4.add(2);
		stud4.add(4);
		stud4.add(1);
		studs.add(stud4);
		
		ArrayList<Integer> stud5 = new ArrayList<Integer>(); 
		stud5.add(1);
		stud5.add(7);
		stud5.add(6);
		stud5.add(4);
		stud5.add(3);
		stud5.add(5);
		stud5.add(2);
		studs.add(stud5);
		
		ArrayList<Integer> stud6 = new ArrayList<Integer>(); 
		stud6.add(6);
		stud6.add(3);
		stud6.add(7);
		stud6.add(5);
		stud6.add(2);
		stud6.add(4);
		stud6.add(1);
		studs.add(stud6);
		
		ArrayList<Integer> stud7 = new ArrayList<Integer>(); 
		stud7.add(1);
		stud7.add(7);
		stud7.add(4);
		stud7.add(2);
		stud7.add(6);
		stud7.add(5);
		stud7.add(3);
		studs.add(stud7);
		
		return new Preferences(7, 7, profs, studs);
	}
}
