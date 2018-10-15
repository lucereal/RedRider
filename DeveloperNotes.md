# Developer Notes

guide to architecture: https://github.com/googlesamples/android-architecture/tree/todo-mvp

Each component will at least have the following: 

- Activity - Initializes Fragment and Presenter, handles navigation from activity to activity and manages one or more fragments
- Contract - Interface that contains View interface and Presenter interface
- Fragment - Java class, implements Contract.View
- Presenter - Java class, implements Contract.Presenter

- [classname]_act.xml - this is the view of the activity, which creates the basic layout for the activity screen, creates structure where the fragment will then be places to display the content
- [classname]_frag.xml - this is the view of the fragment which is where all the content and event listeners will be