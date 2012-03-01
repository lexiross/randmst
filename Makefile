
JC = javac
FLAGS = -g -d bin
SRC = src/AdjListNode.java src/Graph.java src/Heap.java src/MinHeap.java src/AdjListGraph.java src/randmst.java

default: all

all:
	$(JC) $(FLAGS) $(SRC)

clean:
	$(RM) bin/*.class
