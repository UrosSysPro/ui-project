# Programske Paradime - UI
## User Interface library ispired by Flutter, written in Scala


### Download and run
Clone the project and run all examples
```
git clone https://github.com/UrosSysPro/ui-project.git ui-project
cd ui-project
./gradlew core:run
```

### Getting started
1. [Create a Window and display a widget](docs/CreateWindowAndDisplayWidget.md)
2. [Stateful vs Stateless widget](docs/StatefulVsStateless.md)
3. [Basic layout widgets](docs/LayoutWidgets.md)
4. [Event listeners](docs/EventListeners.md)
5. [Material and Cupertino package](docs/Design.md)

<p style="float: left">
  <img src="./images/scala%20app.png">
</p>

```scala
package com.systemvi.examples.uitest.material

import com.systemvi.engine.ui.{ UIApplication, Widget, runApp}
import com.systemvi.engine.ui.utils.context.BuildContext
import com.systemvi.engine.ui.utils.data.{Colors}
import com.systemvi.engine.ui.widgets.material.{AppBar, FloatingActionButton, Scaffold}
import com.systemvi.engine.ui.widgets.{Center, EdgeInsets, Padding, State, StatefulWidget, Text, TextStyle}

object App{
  def main(): Unit = {
    runApp("MyApp",MyApp())
  }
}
object MyApp{
  def apply(): MyApp = new MyApp()
}
class MyApp() extends StatefulWidget{
  override def createState(): State = new MyAppState()
}
class MyAppState extends State{
  var clicks:Int=0
  override def build(context: BuildContext): Widget = {
    Scaffold(
      appBar = AppBar(
        leading = Padding(
          padding = EdgeInsets.symetric(horizontal = 20),
          child = Text("<",
            font = UIApplication.font,
            style = TextStyle(scale = 0.5f, color = Colors.white)
          )
        ),
        title = Text("AppBar",
          style = TextStyle(scale = 0.5f, color = Colors.white),
          font = UIApplication.font
        )
      ),
      body = Center(
        child = Text(s"Clicks: $clicks",
          font = UIApplication.font,
          style = TextStyle(color = Colors.white)
        )
      ),
      floatingActionButton = FloatingActionButton(
        onTap = () => setState(() => clicks += 1),
        child = Text("+", font = UIApplication.font)
      )
    )
  }
}

```
<p style="float: left">
  <img src="./images/flutter%20app.png">
</p>

```dart
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        useMaterial3: false,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int counter = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: Icon(Icons.chevron_left),
        title: Text("MyApp"),
      ),
      body:  Center(
          child:Text("Clicked: $counter",style: TextStyle(fontSize: 30),)
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: ()=>setState(()=>counter++),
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

```