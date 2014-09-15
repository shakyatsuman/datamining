    JFLAGS = -g -cp .:. -d .
    JC = javac
	
    .SUFFIXES: .java .class
    .java.class:
		$(JC) $(JFLAGS) $*.java
    CLASSES=\
		ss0113ARFFFileData.java \
		ss0113NormalizationUtil.java \
		ss0113FileUtil.java \
		ss0113ARFFFileParser.java \
		ss0113Normalize.java
	
    default: classes
	
    classes:$(CLASSES:.java=.class)
	
    clean:
		$(RM) *.class