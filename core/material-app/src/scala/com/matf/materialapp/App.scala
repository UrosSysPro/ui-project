package com.matf.materialapp

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.font.Fonts
import com.matf.ui.widgets.material.{AppBar, FloatingActionButton, Scaffold}
import com.matf.ui.widgets.{Center, State, StatefulWidget, Text, TextStyle, Transform}
import org.joml.Vector2f

class App extends StatefulWidget{
  override def createState(): State = new AppState()
}

class AppState extends State{
  var clicks = 0
  override def build(context: BuildContext): Widget = {
    Scaffold(
      appBar = AppBar(
        title = Transform.translate(
          offset = new Vector2f(20,0),
          child = Text("App Bar", style = TextStyle(), font = Fonts.pixels)
        )
      ),
      body = Center(
        child = Text(s"$clicks",style=TextStyle(),font=Fonts.pixels)
      ),
      floatingActionButton = FloatingActionButton(
        onTap = ()=>setState{() => clicks+=1},
        child = Transform.translate(
          offset = new Vector2f(2,0),
          child = Text("+", style = TextStyle(), font = Fonts.pixels)
        )
      )
    )
  }
}
