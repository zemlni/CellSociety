Discussion (eab91, dsh38)
==========
A write-up of the decisions I (eab91) made with my partner (dsh38) in lab.
#### Code Smell Refactoring
I refactored two pieces of my project's code for this lab:

1\. First, I realized that each of our game subclasses had several lines of duplicated code in their constructors.
```
setName("Wator"); 
...
parseXML();
```
I noticed two problems with this code. First, the `setName()` method was completely unnecessary, as its sole purpose was to pass the name of the game to `parseXML()`. As a consequence, I completely removed the `setName()` construction. Then, I chose to call `parseXML()` directly from my abstract class, as each subclass was calling it at the same point, with the same arguments. I'm satisfied with this refactor: I got rid of lots of code in my game subclasses, and removed the need for several variables and methods.

2\. In my control panel, I realized I was creating two TextFields and their corresponding labels with nearly identical blocks of code. The solution was relatively simple: I created common `createField()` and `addLabelToField()` methods that both methods called:

```
	private TextField createField(String value) {
		TextField field = new TextField(value);
		field.setPrefWidth(55);
		return field;
	}

	private Node addLabelToField(TextField field, String property) {
		HBox box = new HBox(HORIZONTAL_SPACING);
		Label label = new Label(resources.getString(property));
		box.getChildren().addAll(label, field);
		box.setAlignment(Pos.CENTER_LEFT);
		return box;
	}
```

This refactoring made my code shorter, and removed duplication. Happy about it!

#### Checklist Refactoring
I refactored two checklist-type problems:

1\. The tool caught the following magic value:

```
if (name.substring(name.lastIndexOf('.') + 1).equals("xml"))
```

To make this passage less confusing, I decided to create an additional method:

```
	private boolean isXMLFile(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.') + 1).equals("xml");
	}
```

I think this reduces the impression that this is a magic value. It also makes the code re-usable.

2\. I also spotted the following problem:

```
if (rand < Double.parseDouble(getParameter("percentRed")))
```

While I agree that the `"percentRed"` value is a bit confusing, I think the true problem is the `Double.parseDouble()` construction. The former is unavoidable, while the second definitely is. I chose to make a method, `getDoubleParameter()`, that removed the problematic construction. Because we have to make use of `"percentRed"`, I wasn't able to completely remove it. 
