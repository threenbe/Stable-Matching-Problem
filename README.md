# Stable-Matching-Problem
A variety of solutions for the stable matching problem with students and professors. Everyone has a fully ordered list of preferences for the other group.

### Part 1
A brute force solution in which all possible permutations of student/professor are generated until a stable matching is found.

### Part 2
A more efficient solution implementing the Gale-Shapley Algorithm. This solution returns a professors optimal matching. Runs in O(n<sup>2</sup>) time. 

### Part 3
This solution uses Gale-Shapley but also returns the "cost" of each matching, where the cost of a pair to someone is the difference in rank of his most preferred choice and rank of the person currently assigned to him. This solution can return both professors optimal and student optimal matchings.
