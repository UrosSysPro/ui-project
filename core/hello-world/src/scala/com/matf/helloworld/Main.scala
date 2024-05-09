package com.matf.helloworld

import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{BoxDecoration, Colors}
import com.matf.ui.utils.font.Fonts
import com.matf.ui.widgets.material.Button
import com.matf.ui.{Widget, runApp}
import com.matf.ui.widgets.{Center, Column, State, StatefulWidget, StatelessWidget, Text, TextStyle}
import com.systemvi.engine.utils.Utils

object Main{
  Utils.assetsFolder="../"
  def main(args:Array[String]): Unit = {
    runApp("MyApp",new MyApp())
  }
}

class MyApp extends StatelessWidget{
  override def build(context:BuildContext):Widget={
    Center(
      child=Text(
        "hello world",
        style=TextStyle(color=Colors.white),
        font=Fonts.pixels
      )
    )
  }
}
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