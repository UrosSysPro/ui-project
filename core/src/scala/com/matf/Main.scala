package com.matf

import com.matf.ui.Scene
import com.matf.ui.widgets.cupertino.Switch
import com.matf.ui.widgets.{Container, Row, SizedBox}
import com.systemvi.engine.application.Game
import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{CrossAxisAlignment, MainAxisAlignment}
import com.matf.ui.widgets.{State, StatefulWidget}
import com.systemvi.engine.utils.Utils
import com.systemvi.engine.utils.Utils.Buffer
import com.systemvi.engine.window.Window

object Main extends Game(3,3,60,800,600,"Test"){
  var scene:Scene=null

  override def setup(window: Window): Unit = {
    scene=Scene(
      window = window,
      root=new App()
    )
    setInputProcessor(scene)
  }

  override def loop(delta: Float): Unit = {
    Utils.clear(0,0,0,1,Buffer.COLOR_BUFFER)
    scene.animate(delta)
    scene.resize(scene.width,scene.height)
    scene.draw()
  }

  def main(args:Array[String]):Unit={
    run()
  }
}

class App extends StatefulWidget{
  override def createState(): State = new AppState()
}
class AppState extends State{
  var selected=true
  override def build(context: BuildContext): Widget ={
    Container(
      child=SizedBox(
        child=Row(
          crossAxisAlignment=CrossAxisAlignment.center,
          mainAxisAlignment=MainAxisAlignment.center,
          children=Array(
            Switch(selected,onChange = value=>setState{ ()=>
              println(value)
              selected=value
            })
          )
        )
      )
    )
  }
}