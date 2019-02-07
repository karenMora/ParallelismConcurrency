# ParallelismConcurrency


# Part I
Threads control using wait/notify. Producer/Consumer
1. Execute and check how the program works, Execute jVisualVM and inspect the CPU use of the corresponding process.
- What is the reason of this CPU consumption?
![Alt text](/img/Parte1JVisualVM1.png)
![Alt text](/img/Parte1JVisualVM2.png)
![Alt text](/img/Parte1JVisualVM3.png)

- What is the class responsable of that consumption?

---> La clase responsable es StartProduction

2. Write some code in order to solve the CPU's use problems , having in mind  that for now the production is slow and the consumption is fast, check that the CPU consumption has decreased.

![Alt text](/img/Parte1ConsumoCPU1.png)
![Alt text](/img/Parte1ConsumoCPU2.png)

3. Make a producer that produces as fast as it can, and the consumer consumes slow, keeping in mind that the producer knows a Stock limit (how many elements have to have at most in the queue) your code has to respect that limit.Check the Collection API used as queue in order to validate that the limit not be exceeded.Verify that when you set a small limit in the stock, there is not a high CPU consumption or errors. So that, altogether we are exploring the total of servers

# Part II

Keeping in mind the race condition concept and synchronization. Make a new version more efficient of the previous exercise.(The Black List search). In the current version, each thread is in charge of check the host in each subset of servers that is assigned to it. So that, altogether it explores the total of servers.

Considering this:

1. Make that the distributed search stops (Stop looking at the remaining blacklists) return the answer when the threads in its set have detected number of occurrences required to determine if a host is truswhorthy or not(BLACK_LIST_ALARM_COUNT)

2. The above, ensuring that there are no race conditions


# PART II

Synchronization and deadlocks

![Alt text](/img/ParteIIviñeta.png)

1. Check the "Highlander-simulator" program, you can find it in edu.eci.arsw.highlandersim package. This is a game in which:

* There is N players
* Each player knows the other N-1 remaining players
* Each player, permanently, attacks other inmortal. The firstone who attacks substract m  life points to the opponent, and increase in the same quantity his life points.
* The game could never have  an unique winner. The most likely thing is that in the end there are only two, fighting forever substracting and adding lifepoints

![Alt text](/img/PARTIIHighlander.png)

---> solo funciona el boton "Start"
![Alt text](/img/PARTIIHighlanderStart.png)

2. Check the code and identify how the indicated funcionality was implemented. Given the purpose of the game, an invariant should be that the sum of the life points of all the players always be the same.(Of course, in a moment of time when an operation of increase / reduction of time is not in process). For that case, for N players, what should be that value?

---> START: se crea el boton y el Action listener correspondiente  el cual recorre todos los inmortales  los "enciende", coloca los hilos a funcionar y tiene una bandera para saber que el juego ya empezo y no se pueda oprimir dos veces seguidas el boton start.

3. Run the application and verify how the "pause and check" option works, is the invariant satisfied?

--->

4. A first hypothesis for presenting the race condition for that function (pause and check) is that the program check the list whose values ​​are going to print, at the same time other threads modify the list's values. In order to solve that write the necessary code to effectively, before print the current results, pause the other threads. Also, implement the resume option.

--->

--->

5. Verify again the functionality clicking many times. Is the invariant satisfied?

--->


6. Identify possible critical regions in regards to the fight of the immortals.Implement a lock strategy to avoid the race conditions. Remember that if you need use two or more 'locks' simultaneously you can use nested sychronized blocks.
![Alt text](/img/PARTIIsyncLock.png)
--->

7. After implementing your strategy. Run the program and pay attention if it stops. In that case, use the jps and jstacks programs to identify why the program stops its execution.


8. Think a strategy in order to solve the problem identified(you can check again the 206 and 207 pages of the "Java Concurrency in practice").

--->

9. When you have solved the problem, check that the program continues working with consistency when executing 100,1000 or 10000 inmortals. If with large amount of immortals you breach the invariant again. You have to analyze it again (step 4).

10. An annoying element of the simulation, that is certain point thereof , there are few alive immortals  fighting failed with dead immortals.Is needed to delete the dead inmortals in the simulation when they are dying, for that:

Analizing how the simulation works. this could create a race condition? Implement the functionality , run the simulation and observe what problem is presented when there are many inmortals. Write some conclutions in the Answers.txt file

Solve the problem identified above without use Sinchronization, cause the sequentiallity of the process would make extremely slow the simulation.

--->

11. Finally, implement the stop option
--->












---------------------------------------CREATED BY KAREN MORA---------------------------------------
