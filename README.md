# Life of the Ants

In an ant colony, there are four different castes: workers, soldiers, drones, and there is one queen. The colony area is square (has a width), and the ants live and move within the borders of the colony.

Each ant acts in every timestep, according to a caste-specific rule.

The queen sits in the middle and does not move.
Workers normally make one step in one of the four directions, chosen randomly before each move.
Soldiers "patrol" close to their starting position; this means that in a four-cycle, they step one and turn to the left (towards North, East, South, or West) and then they start the cycle again.
Drones always try to make one step towards the Queen. When they get next to the queen, they have a chance that she is in the mood for mating. In this happy case, they say "HALLELUJAH", and stay there for 10 timesteps. After that they are kicked off in one of the four directions (chosen randomly), to the edge of the colony. If the queen isn't in the mood, drones say ":(", and are kicked away instantly.
The queen’s mating mood is determined using a cooldown timer (set to 50 to 100 timesteps) after a successful mating. When the timer runs out, she gets in the mood again.

## What are you going to learn?
Design an application using a class diagram.
Think in the OOP way.
Work a little with algorithms.

## Tasks
1. Class diagram
   - Plan and draw the class diagram for this simulation on draw.io. Create and upload a plan. After finishing the implementation, update the plan to show the end result, and upload it as well.
     - The class diagram plan is added to the repository with the first commit as diagram-plan.png. It displays all classes and methods mentioned in the story.
     - The final class diagram is uploaded as diagram-final.png. It mirrors the implemented simulation exactly.
2. Ant Colony
   - A colony is created with a width value setting its range, a width by width square. A queen is created at construction time and is positioned at the center of the colony. Colony also has a generateAnts method with three integer arguments that set the number of drones, workers, and soldiers to be created, respectively. It also has an update method which invokes each ants' act method, and a display method which displays the colony "map" on the console.
     - Upon construction, the value of width is set, and a queen is created at the center.
     - There is an update method that calls act on each ants.
     - There is a display method that prints all ants in the colony as a width by width map, placing the initials of the ants (Q, W, S, D) at their actual positions. If more then one ants share the same position, only one letter is displayed.
3. Simulation
   - Implement the main loop of the simulation: step forward one time unit and display the state of the colony every time when Enter is pressed. Hitting q and Enter exits the program.
     - The simulation creates a colony and populates it with all kinds of ants.
     - The simulation updates once and displays the state of the colony after hitting Enter.
     - The program stops one after hitting q and Enter.
4. Movement
   - Implement the basic movement behavior of ants. Ensure that if a step would cause an ant to leave the range of the colony, the ant does not move.
     - Workers move one step in a random direction.
     - Soldiers patrol by stepping one and turning to the left in every time step.
     - Drones move one step closer to the queen. If they reach the queen's position, they are kicked off in one of the four directions (chosen randomly), to the edge of the colony (so @width / 2 steps away.) Ignore mating for now.
     - The queen does not move at all.
     - Ants stay within the range of the colony.
5. Mating
   - Implement the mating behavior of drones and the queen.
     - The queen's mating mood is set by a counter which is decremented by one in each step. If it reaches zero, the next drone encounter ends up in mating. This resets the counter to a random value between 50 and 100.
     - The successful drone stays next to the queen for 10 steps after mating, before getting kicked away.

## Hints
- Keep in mind all four basic principles of OOP, and also clean code. All names should be meaningful and descriptive. When it's done right, most methods in the code look like simple but readable English sentences.
- Many implementation details are not ant-specific but belong to general 2D geometry, such as the meaning of coordinates, stepping into directions and changing the coordinates, or turning left or right. Try to abstract out and encapsulate these aspects to the to classes in the geometry subpackage, Position and Direction.
- Direction is an enumerated class with four instances (NORTH, EAST, SOUTH, and WEST). Enum classes can be extended with fields and methods as well. Use this feature to encapsulate everything that belongs to them.
- When displaying shapes on the console, a square looks like a 1:2 rectangle, following the shape of the character unit. You can hack the view (and strictly only the view) by adding one space after each displayed character, effectively stretching the view horizontally by a factor of two.

## Background Materials
[Java enums](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)