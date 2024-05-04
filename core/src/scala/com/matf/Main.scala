package com.matf

import com.matf.ui.Scene
import com.systemvi.engine.application.Game
import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.Colors
import com.matf.ui.widgets.cupertino.Switch
import com.matf.ui.widgets.material.Button
import com.matf.ui.widgets.{Column, Container, Row, Stack, State, StatefulWidget, Text}
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
    var SwitchArray = new Array[Widget](10)
    for(i <- SwitchArray.indices)
      SwitchArray(i) = Switch(value = false)

    var RowArray = new Array[Widget](10)
    for(i <- RowArray.indices)
      RowArray(i) =  Row(children = SwitchArray)

    Column(
      children = RowArray
    )

  }
}