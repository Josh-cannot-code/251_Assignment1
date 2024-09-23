## Description:
You are given an abstract class `RBTree`, your job is to implement
a new class `MyRBTree` which extends `RBTree`. You must implement
two methods: `insert` and `isValidRBTree`. A template for 
`MyRBTree` has been provided, but you may add helper methods as 
you see fit, just make sure they are only visible to `MyRBTree`.



## Constraints:
- Initialize root to the provided `nil` property on `RBTree`;
use `nil` for all external nodes and for the parent of `root`.
- Use the provided `Color` enum for the `color` property on nodes.
- We will not insert a number that already exists in the tree.
- Your insert operation should be `O(log(N))`.