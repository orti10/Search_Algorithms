# Search_Algorithms

Ortal Hanoch

# Consistent Heuristic
If c(n,m) is the actual shortest path cost between n to m,
then, heuristic function h(x) is admissible to all n, m.
If h(m)>=h(n) ,then c(n,m) always positive so, h(n)<=c(n,m)+h(m).
If h(m)<=h(n) then, the "_" moved in direction of goal because h is admissible.
For every "_" we moves entered c(n,m) and h(n)-h(m)<=c(n,m) as required.

The Manhattan distance between two points is a consistent heuristic :
Consider specifically the heuristic where h(u,v)= |ux-vx|+|uy-vy| is such that for any Node n with successor s, h(a,n)<=c(a,n,s)+h(a,s) where c(a,n,s) is the true path cost.
As we know, the Manhattan distance is the shortest path between two points.

# Heuristic function
Manhattan distance = The sum of the horizontal and vertical distances between Node start to goal.
Definition: distance from u to v, when u=ux,uy and v=vx,vy
h(u,v)= |ux-vx|+|uy-vy|


# Admissible
Manhattan distance is an admissible heuristic in this case because every "_" will have to be moved at least the number of spots in between itself and the goal position.
So, h(n)<=s(n) while s(n) is the exact lowest cost from Node n to goal as required.


![](https://raw.githubusercontent.com/asdoctor612568/8Puzzle-Solver-BFS-A-Star-DFS/356e9ed0bf6bebdd4ca4976d42bfab3bf7871e60/whenSolveClicked.gif)
