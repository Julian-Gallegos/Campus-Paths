### Total Score: 20/25

### Answers Score: 16/20
- Problem 1a: 6/7
    - AF for IntQueue2 needs to be clearer: (front + i) % length is the ith index
- Problem 1b: 1/1
- Problem 1c: 4/6
    - decode will expose if a the string array is a field because it's mutable
    - elements is exposed since iterator has a remove method
- Problem 2: 3/3
- Problem 3: 2/3
    - Black box testing is if you don't know how it is implemented and clear box the opposite. You are doing black box testing here
    - You need to test methods that have the highest priority and least dependencies in the impl test

### Following Directions Score: 4/5
    - Have a private field

The below scores are separate:

### Documentation Score: 2/3
- You did not specify how you would handle null cases

### Design Score: 2/3
- listNode and nodeCount can be merged into one method
- Some methods like listChildren, listNode should be returning a set / list instead instead of a String. Easier to test later

### Testing Score: 2/3
- Your test should be granular (ex. you should assert with nodeCount in constructor test if you have not tested that method)
- You should test for special cases (circular graph, representation exposure, disconected graph, ...)

#### Code Review Feedback

None.