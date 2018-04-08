# BlueStoneUtils
BlueStoneUtils (BSU) is a library that used to make your life easier with Java. It adds utilities for Serializations, Async, Timers, Exceptions and Reflection.

## Serialization
The Serialization class (blue.bluestone.utils.Serialization) allows you to save and load serializabled objects easily. For example, let's assume that we have a banana object (which implements from Serializable) and we want to save it to our file (i.e. banana.apple).

    Serialization.saveObject("banana.apple" /* <- That's the file that we want to save the object to. */, banana /* <- That's the object we want to save into the file. */);
EzPz.

Now, our angry doctor wants to use the banana object again. How can we read it? Let's take a look.

    Serialization.loadObject("banana.apple" /* The file we want to read it from */, () -> {  
       // We're welcome to put some code here. It will only run if we cannot load the object.  
       // At the end of the code we have to return a new object that Serialization#loadObject will have to return.  
       return new BananaObject();  
    });
Yeah, I guess it's pretty easy.

## Synchronize (Async and Timers)
Haven't you always wanted to synchronize your code to work perfectly? That's how you're gonna do it.

    Synchronize.async(() -> {  
       // BIP BOP BANANA. IM A ROBOT BIP BOP
       // The code that will be written here is going to run as an async.
    }/*, delay (in miliseconds) that the Synchronize#async will wait before it runs your code. */);

## Exceptions
That's just a tool that allows you to catch error exceptions. It also allows you to run a code multiple times until it stops throwing an exception.
For example:

    Force.forceRun(() -> {  
       // Yeah yeah I'm throwing an exception I think  
    }, () -> {  
       // Oh! I've caught it! Here my catch code's gonna be written!  
    }/*, Maybe I want to set maximum times of trying this code... It might be 666. Yeah, 666 is fine I guess.*/);
    
## Reflection
Oh, That's the interesting thingy. I hope that the man who sits behind the screen and reads this shitty text knows what reflection is. I'm not going to explain it :)

We have 2 (and half) kinds of classes which used to make your reflection easier. FieldUtils, ReflectionUtils and the half - Parameter. What that's all about? Let's read!

### FieldUtils
"Oh man.... That's gonna hurt" - Someone, Somewhere. 
Yeah, reflection is not as fun as rainbow and gays, but we're gonna do it. First of all we will meet our friend - FieldUtils! :claps: :claps:

FieldsUtils allows us to set and get the value of **declared** fields via their name. It does it like that I think:

    // HINT: Remember? The banana is our object!  
    // HINT3: The class of the banana object is BananaObject! Such a strange(r things) name .-.  
    if (FieldUtils.hasDeclaredField(banana, "color")) { // If we want to kill a banana, we have to check if it has the right fields!  
      String color = FieldUtils.getDeclaredField(banana, "color"); // We wanna know what color our banana has  
      FieldUtils.setDeclaredField(banana, "color", color.equals("yellow") ? "green" : "yellow"); // we set a new color! woohoo!  
      FieldUtils.setDeclaredField(BananaObject.class, null, "taste", "yummyyyy mmm"); // taste is a static field so it's yummy  
    }

### ReflectionUtils
Oh, You think that the last one was hard? Yeah... it's gonna kill you. But today is your lucky day! I will show you only one thing that ReflectionUtils can do, but you're always welcome to take a look at the source code of this class. 
ReflectionUtils has a really special method - ReflectionUtils#getFullDeclaredObject. 

In reflection you can't just use dots to call methods and shit. But getFullDeclaredObject allows you to do it. What do I mean? Look at that...

    ReflectionUtils.getFullDeclaredObject(banana, "apple.getTaste(**).colorOfTaste" /* '*' is a parameter */, 
     new Parameter("Let's guess that you have to enter a string to that method"),  
     new Parameter(boolean.class, true /* You can specify the type of the parameter. */));
So, the code gets the field apple from the banana object, than it calls to getTaste(String, Boolean) which is found in apple and thannnn it returns the field colorOfTaste which is found in the object that getTaste returns.

**I;m bored. I'm going to sleep. Good night my lovely gays <3**
