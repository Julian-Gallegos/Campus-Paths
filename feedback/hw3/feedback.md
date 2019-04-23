### Total Score: 21/25

### Answers Score: 13/15
- Fibonacci: 3/5
`testBaseCase` fails for the same reason that
`testThrowsIllegalArgumentException` does.

- Ball: 1/1
- BallContainer: 4/4
- Box: 5/5

### Style Score: 8/10
When selecting a greeting in `RandomHello`, the best style would use the length
of the array to specify the maximum value for the random integer generation:
```
String nextGreeting = greetings[rand.nextInt(greetings.length)];
```
Notice how this benefits us later on if we wanted to change the number of
possible greetings in the array.


Your BallContainer add/remove methods are more complicated than they need to be.
Look at the documentation for Set.add and Set.remove and see whether you need to
explicitly handle cases the cases of adding something that already exists in the
set and removing something that doesn't exist in the set.


Your Ball sorting method assumes that there cannot be two distinct balls that
happen to have the same volume, which is not true.  There's also no obvious
advantage of this strategy over just storing the balls in a `List` in sorted
order.