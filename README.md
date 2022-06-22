# 2dMazeGame
Small game like project to test Dijkstra's algorithm

The cpu checks for the shortest path and uses it. You can try to alter the map
in the resources directory for testing. You can also create your own map but then you
will need to alter the input in the ManageFileInput class. 

To create your own map you simply specify where the obstacles are and annotethem with 'x'. 
Free passage kan be annoteded with any char. 

Example of a valid file:

3,3 // Size of the map.
1,2 // Sets starting pos.
0,0 // Sets goal pos.
ooo
oxx
ooo

Note that start and end positions needs to be reachable or the program
will rule them as unusable.
