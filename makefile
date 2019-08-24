#makefile

AsciiAnim: AsciiCanvas.java AsciiAnim.java Node.java
	javac AsciiCanvas.java AsciiAnim.java Node.java

clean:
	rm -f *.class
	rm -f *.ser

run: AsciiAnim
	java AsciiAnim
