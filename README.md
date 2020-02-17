This project was created with an intention to give an idea on String Pooling in Java.

These examples were created to give an overview on how java & jvm can reuse string pool when a new string is created.

Basically JVM divides it's memory into stack and heap. So while allocating memory for string objects, JVM uses immutability of string and a pattern like flyweight to efficiently use it's heap memory allocation. 