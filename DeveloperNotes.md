# Developer Notes

guide to architecture: https://github.com/googlesamples/android-architecture/tree/todo-mvp

Each component will at least have the following: 

- Activity - Initializes Fragment and Presenter, handles navigation from activity to activity and manages one or more fragments
- Contract - Interface that contains View interface and Presenter interface
- Fragment - Java class, implements Contract.View
- Presenter - Java class, implements Contract.Presenter
- [classname]_act.xml - this is the view of the activity, which creates the basic layout for the activity screen, creates structure where the fragment will then be places to display the content
- [classname]_frag.xml - this is the view of the fragment which is where all the content and event listeners will be

![mvp](C:\Users\lucer_000\Pictures\mvp.png)

Note: Instead of SQLite we are using MySQL



#### Injection

The presenter is the class that should be accessing the model (data). In our application we have a PostRepository which is a class that holds a list of posts that the user has. It is the model of our architecture. The presenter needs an instance of the PostRepository so that it can collaborate with the data. 

The presenter is dependent on the PostRepository, they are [coupled](https://softwareengineering.stackexchange.com/questions/244476/what-is-decoupling-and-what-development-areas-can-it-apply-to). We should aim to keep the coupling between our classes as loose as possible (decoupling) because we do not want the components of our system to be heavily dependent on each other or to know the details of the dependencies implementation. 

Traditionally each object is responsible for obtaining its own references to its dependencies. This leads to highly coupled classes.

With [dependency injection](https://stackoverflow.com/questions/130794/what-is-dependency-injection), any object is given their dependencies at run time rather than compile time and rather than an object instantiating its own dependencies, the object is handed the dependency. So that we can modify the PostRepository whenever we want.

In our application there is an Injection class that provides instances of the PostRepository. Every time an activity is started, the activity initialized the fragment and the presenter. The presenter constructor is 'injected' with an instance of the PostRepository so that it does not have to create its own instance and so that it has an instance at run time.