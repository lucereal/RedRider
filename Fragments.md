### Fragments

source: https://guides.codepath.com/android/creating-and-using-fragments

A fragment is a reusable class implementing a portion of an activity. They cannot run independently of activities.

- a fragment is a combination of an xml file and a java class
- fragments encapsulate views and logic so that it is easier to reuse within activities
- fragments are standalone components and can contain views events and logic
- with a fragment-oriented architecture, activities become navigational containers that are focused on navigation to other activities, presenting fragments, and passing data

Activities are navigation controllers responsible for:

- navigation to other activities
- presenting navigational components such as the navigation drawer
- hiding and showing fragments using the fragment manager
- receiving data from intents and passing data between fragments

Fragments are content controllers and contain most views, layouts, and event logic including:

A fragment has an xml file and a Java class that represents the fragment controller.



##### Embedding a Fragment in an Activity

Dynamically using java or statically using xml

The activity should extend from FragmentActivity or AppCompatActivity which adds support for the fragment manager

Statically - just embed the fragment in the activity's xml layout file

Dynamically - use FragmentManager and the FragmentTransaction class which allows you to add, remove, and replace fragments in the layout of your activity at runtime

- if the fragment should always be within the activity use xml to statically add the fragment but in more complex cases use the dynamic approach

##### Communicating with Fragments

Fragments should only communicate with their parent activity except in the case of nested fragments but besides parent fragment communicating with child fragment, fragments should not communicate with each other.

##### FragmentManager

Responsible for all runtime management of fragments including adding, removing, iding, showing, or otherwise navigating between fragments. 





### Note:

More specific details can be found in the source