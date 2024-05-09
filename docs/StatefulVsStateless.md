# Stateful vs Stateless

These are the only classes that directly extend the `Widget` class,
They are used for different purposes.

## Stateless
More straight forward one, implements all the methods of `Widget`, defines some basic behaviour for all widgets, and leaves to user only to define child nodes.
Child nodes are returned by build method.

```scala
class MyApp(text:String) extends StatelessWidget{
  override def build(context:BuildContext):Widget={
    Center(
      child=Text(
        text,
        style=TextStyle(color=Colors.white),
        font=Fonts.pixels
      )
    )
  }
}
```

Once build method is run child widgets can't be changed, everything is constant.
Stateful widgets are useful for defining peaces of ui that don't change over time and dont have any functionality by themselves.
They can be customized with constant parameters in constructor but only when it is created.

## StatefulWidget

Stateful widgets have two attributes, `child:Widget` and `state:State`. Stateful widget can change their appearance after constructor is run.
All constant data that is supplied by parent widget is stored in widget class and all data that changes over time is stored in state.
To define a stateful widget two classes are needed:
```scala
class MyWidget extends StatefulWidget{
  override def createState(): State = new MyWidgetState()
}
class MyWidgetState extends State{

  var clicks:Int=0

  override def build(context: BuildContext): Widget = {
    Column(
      children = Array(
        Text(s"Clicks $clicks", style = TextStyle(color = Colors.white), font = Fonts.pixels),
        Button.filled(
          decoration = BoxDecoration(),
          onTap = () => setState{() => clicks += 1},
          child = Text("Add +", style = TextStyle(color = Colors.white), font = Fonts.pixels)
        )
      )
    )
  }
}
```
State class defines `build`, `init`, `dispose` and `setState` methods.
- `build` same as in StatelessWidget but this time it is defined in state so it can use all of the mutable data
- `init` runs once this widget first appears on screen, useful when initializing large objects (shaders, images, data fetched from server), data that we dont want to load 60 times per second
- `dispose` runs once this widget disappears from widget tree, useful for freeing data allocated in init method
- `setState` used when some mutable data changes and child widgets must be changed, calls build method after state changes 