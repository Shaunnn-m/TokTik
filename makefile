# TokTik program makefile
# Thabang Mokoena
# 15 April 2023


JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
CLASSES= AccountNode.class BST.class TokTik.class Main.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)


default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
run: $(CLASS_FILES)
	java -cp bin Main
docs:
	javadoc -d doc/ src/*.java
	

