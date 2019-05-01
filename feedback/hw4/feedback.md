### Total Score: 22/36

### Answers Score: 18/26
- Pseudocode (Problem 0): 6/8
  2 invariants for 1 loop
  Multiplication of two terms should be part of pseudocode, not comments
  No invariant on division loop

- Altered Rep Invs (Problems 1a, 2b, and 2c): 3/6
  hashCode, in 2b getExpt as well
  2c blank

- Mutability (Problem 1b): 1/2
  Documentation for method: lack of @modifies, @effects
- checkRep Usage (Problems 1c, 2a, and 3a): 2/6
  Doesn't account for non-compiler-enforced mutability (i.e. final lists)
  Doesn't account for all correct uses of checkRep considering ADT implementation

- RatPoly Design (Problem 3b): 4/4


### Code Style Score: 8/10

#### Specific Feedback

RatPoly 70-74 - can pull 71 out of statement to simplify

Invariant would be good in add, mul



#### General Feedback

More whitespace in long / complicated methods helps with readability

Get rid of TODOS and commented out code

Checkreps necessary in RatPolyStack, RatPoly
     