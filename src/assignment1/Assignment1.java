package assignment1;

/**
 * Class to implement Stable Matching algorithms
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Assignment1 {

    // Part1: Implement a Brute Force Solution
    public static ArrayList<Integer> stableMatchBruteForce(Preferences preferences) {
    	//index of student is matched with prof
    	//e.g. 4th element = 5 --> prof 4 matched with student 5
    	int n = preferences.getNumberOfProfessors();
    	Integer students[] = new Integer[n];
    	for (int i = 0; i < n; i++) {
    		students[i] = i;
    	}
    	ArrayList<ArrayList<Integer>> allPermutations = new ArrayList<ArrayList<Integer>>();
    	findPermutations(students, 0, n, allPermutations);
    	
    	ArrayList<ArrayList<Integer>> inverseProfPrefs = inverseLists(preferences.getProfessors_preference(), n);
    	ArrayList<ArrayList<Integer>> inverseStudPrefs = inverseLists(preferences.getStudents_preference(), n);
    	ArrayList<Integer> stableMatching = new ArrayList<Integer>();
    	boolean isStable = true;
    	for (ArrayList<Integer> l : allPermutations) {
    		for (int p = 0; p < n; p++) {
    			for (int s = 0; s < n; s++) {
    				if (inverseProfPrefs.get(p).get(s) < inverseProfPrefs.get(p).get(l.get(p))) {
    					if (inverseStudPrefs.get(s).get(p) < l.indexOf(s)) {
    						//unstable
    						isStable = false;
    						break;
    					}
    				}
    			}
    			if (!isStable) break;
    		}
    		if (isStable) {
    			stableMatching = new ArrayList<Integer>(l);
    			break;
    		} else {
    			isStable = true;
    		}
    	}
    	return stableMatching;
    }
    
    public static void findPermutations(Integer[] students, int start, int end, ArrayList<ArrayList<Integer>> r) {
    	if (start >= end) {
    		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(students));
    		r.add(a);
    	} else {
    		for (int i = start; i < end; i++) {
    			swap(students, start, i);
    			findPermutations(students, start+1, end, r);
    			swap(students, start, i);
    		}
    	}
    }
    
    public static void swap(Integer[] s, int i1, int i2) {
    	int temp = s[i1];
    	s[i1] = s[i2];
    	s[i2] = temp;
    }
    
    // Part2: Implement Gale-Shapley Algorithm
    public static ArrayList<Integer> stableMatchGaleShapley(Preferences preferences) {
    	//returned list
    	//index of student matched with prof
    	//e.g. 4th element is 5, means prof 4 is matched with student 5
    	//if s is matched to p, then stud[p] = s
    	int n = preferences.getNumberOfProfessors();
    	LinkedList<Integer> freeProf = new LinkedList<Integer>();
    	for (Integer i = 0; i < n; i++) {
    		freeProf.add(i);
    	}
    	Integer stud[] = new Integer[n];
    	Integer prof[] = new Integer[n];
    	int count[] = new int[n];
    	for (int i = 0; i < n; i++) {
    		stud[i] = -1; 
    		prof[i] = -1;
    		count[i] = 0;
    	}
    	ArrayList<ArrayList<Integer>> profPrefOfStud = preferences.getProfessors_preference();
    	ArrayList<ArrayList<Integer>> studPrefOfProf = preferences.getStudents_preference();
    	//studPrefOfProf contains profs listed in order 
    	//need an inverse array that has indexing based on prof id, 
    	//with rank being the value stored
    	ArrayList<ArrayList<Integer>> inverseStudList = inverseLists(studPrefOfProf, n);
    	
    	while (!freeProf.isEmpty()) {
    		Integer currentProf = freeProf.getFirst();
    		while (count[currentProf] < n) {
    			int prefStud = profPrefOfStud.get(currentProf).get(0) - 1;
    			count[currentProf]++;
    			profPrefOfStud.get(currentProf).remove(0);
    			if (prof[prefStud] == -1) {
    				prof[prefStud] = currentProf;
    				stud[currentProf] = prefStud;
    				freeProf.remove(currentProf);
    				break;
    			} else {
    				if (inverseStudList.get(prefStud).get(currentProf) 
    						< inverseStudList.get(prefStud).get(prof[prefStud])) {
    					freeProf.add(prof[prefStud]);
    					prof[prefStud] = currentProf;
    					stud[currentProf] = prefStud;
    					freeProf.remove(currentProf);
    					break;
    				}
    			}
    		}
    	}
    	
    	return new ArrayList<Integer>(Arrays.asList(stud));
    	//return null;
    }
    
    public static ArrayList<ArrayList<Integer>> inverseLists(ArrayList<ArrayList<Integer>> l, int n) {
    	ArrayList<ArrayList<Integer>> inv = new ArrayList<ArrayList<Integer>>();
    	for (ArrayList<Integer> a : l) {
    		Integer arr[] = new Integer[n];
    		for (int i = 0; i < n; i++) {
    			arr[a.get(i)-1] = i; 
    		}
    		inv.add(new ArrayList<Integer>(Arrays.asList(arr)));
    	}
    	return inv;
    }

    // Part3: Matching with Costs
    public static ArrayList<Cost> stableMatchCosts(Preferences preferences) {
    	//this returns an array where ith element = professor #i, 
    	//arr[i] = value = student
    	ArrayList<ArrayList<Integer>> inverseStudPrefs = inverseLists(preferences.getStudents_preference(), preferences.getNumberOfProfessors());
    	ArrayList<ArrayList<Integer>> inverseProfPrefs = inverseLists(preferences.getProfessors_preference(), preferences.getNumberOfProfessors());
    	ArrayList<Integer> matchings = stableMatchGaleShapley(preferences);
    	ArrayList<Cost> costs = new ArrayList<Cost>();
    	for (int i = 0; i < matchings.size(); i++) {
    		int profIndex = i;
    		int studIndex = matchings.get(i);
    		int costToProf = inverseProfPrefs.get(profIndex).get(studIndex);
    		int costToStud = inverseStudPrefs.get(studIndex).get(profIndex);
    		costs.add(new Cost(profIndex, studIndex, costToProf, costToStud));
    	}
    	return costs;
    }
    
    public static ArrayList<Cost> stableMatchCostsStudent(Preferences preferences) {
    	ArrayList<ArrayList<Integer>> profPrefs = preferences.getProfessors_preference();
    	ArrayList<ArrayList<Integer>> studPrefs = preferences.getStudents_preference();
    	preferences.setProfessors_preference(studPrefs);
    	preferences.setStudents_preference(profPrefs);
    	
    	return stableMatchCosts(preferences);
    }
}
